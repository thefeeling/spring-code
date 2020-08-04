# JPA Insert 성능 올려보기
JPA를 사용하다보면, 불필요하게 발생하는 쿼리를 종종 볼수 있다. 물론 도메인의 상황이 select 쿼리가 반드시 필요한 상황이라면 문제가 되지 않겠지만, 경우에 따라서 insert 쿼리만 발생하도록 하는 것이 최선일 경우도 있다. 실제 업무에서 불필요하게 발생하는 select 쿼리로 인해 DBA분들에게 문의를 받기로 해서 이에 관련된 부분을 해결(?)하고자 이것저것 찾아본 내용을 정리하려고 한다.

### Persistable 인터페이스 구현
Data JPA 문서를 살펴보면 `Persistable`이라는 인터페이스를 언급한 부분이 있다. 인터페이스 코드를 살펴보면 간단하게 엔티티의 상태를 표현할 수 있으며, 이에 따라 실제 JpaRepository의 구현 클래스인 `SimpleJpaRepository`에서 isNew 상태를 판단하여 불필요한 Select를 줄이고 바로 Insert문을 실행할 수 있게 된다. 간단한 엔티티 구현 코드를 아래와 같다.

```kotlin
package me.daniel.enhancedperformancejpainsert.user_access_log

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.domain.Persistable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "USER_ACCESS_LOGS")
class UserAccessLog protected constructor() : Persistable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id = 0L

    var userId: String = ""

    @CreationTimestamp
    lateinit var createdAt: LocalDateTime

    @UpdateTimestamp
    lateinit var updatedAt: LocalDateTime

    companion object {
        fun create(userId: String) = UserAccessLog().apply {
            this.userId = userId
        }
    }

    @Transient
    private var isNew = true

    override fun isNew() = isNew

    @PrePersist
    @PostLoad
    internal fun markNewState() {
        this.isNew = false
    }

    override fun getId(): String? {
        TODO("Not yet implemented")
    }
}
```

`isNew`의 상태는 `@Transient` 어노테이션을 사용하여 영속화 대상에서 배제했으며, 실제 JPA의 영속화 관련 이벤트를 잡아 @PrePersist와 @PostLoad 단계에서 상태를 다시 false로 바꾸도록 했다. 이렇게 되면 `repository.save()`를 하는 시점에는 엔티티의 isNew상태가 true일 경우 바로 insert 쿼리만 발생하게 된다. 물론 update를 처리하는 경우도 있을 수 있으니 이에 대한 처리를 위해 @PostLoad를 붙여서 해당 케이스를 피했다.

### Batch Insert 구현 추가해보기
사용자들의 로그를 만약 RDBMS를 통하여 적재하는 경우를 생각해보자. 로그는 대부분 수정성격의 트랜잭션이 아닌 단순 적재만 하는 케이스일 경우가 농후하다. Persistable 인터페이스를 구현하여 insert만 발생하도록 하는 것도 차선책이 될수는 있지만, 이런 작업에 매번 트랜잭션을 발생시켜 커넥션을 낭비하는 건 매우 비효율적이므로 이에 대한 처리를 해줄 수 있는 코드를 추가해보도록 하겠다. 여러 사용자들의 로그를 집계하여 저장해야하기 때문에 기존 SimpleJpaRepository의 saveAll 구현이 아닌 별도의 커스텀 쿼리 메소드를 만들어서 처리할 것이다.

```kotlin
// (1)
@SpringBootApplication
@EnableJpaRepositories(
    repositoryBaseClass = BatchRepositoryImpl::class 
)
class EnhancedPerformanceJpaInsertApplication

// (2)
@NoRepositoryBean 
interface BatchRepository<T, ID> : JpaRepository<T, ID> {
    fun <S : T> saveInBatch(entities: Iterable<S>)
}

// (3)
@Transactional(propagation = Propagation.REQUIRES_NEW)
class BatchRepositoryImpl<T, ID : Serializable?>(
        private val entityInformation: JpaEntityInformation<T, ID>,
        private val entityManager: EntityManager
) : SimpleJpaRepository<T, ID>(entityInformation, entityManager), BatchRepository<T, ID> {

    @Value("\${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private var batchSize: Int? = 30

    private val logger = LoggerFactory.getLogger(BatchRepository::class.java)

    override fun <S : T> saveInBatch(entities: Iterable<S>) {
        val entityTransaction = entityManager
                .entityManagerFactory
                .createEntityManager()
                .transaction
        try {
            entityTransaction.begin()
            for ((i, entity) in entities.withIndex()) {
                if (i % (batchSize ?: 30) == 0 && i > 0) {
                    logger.info("Flushing the EntityManager containing $batchSize entities ...")
                    entityTransaction.commit()
                    entityTransaction.begin()
                    entityManager.clear()
                }
                entityManager.persist(entity)
            }
            logger.info("Flushing the remaining entities ...")
            entityTransaction.commit()
        } catch (e: RuntimeException) {
            if (entityTransaction.isActive) {
                entityTransaction.rollback()
            }
            throw e
        } finally {
            entityManager.close()
        }
    }
}

```

(1) `@EnableJpaRepositories`을 선언하고 repositoryBaseClass에 확장하려는 인터페이스 클래스를 지정해준다.
(2) 확장하려는 인터페이스 스펙을 지정해준다
(3) 확장하려는 인터페이스 구현을 추가해준다. 

신규로 확장하는 레파지토리에는 saveInBatch에 대한 구현이 포함되어 있다. saveAll을 사용해도 되겠지만, saveAll의 경우 SimpleJpaRepository의 기본 구현을 따르기 때문에 다량의 insert가 발생할 경우 이에 대한 청크를 지정해주기 곤란하고, 트랜잭션에 대한 플러쉬/커밋 시점에 대한 지정이 어렵다. batchSize는 프로퍼티를 통하여 참조하고 해당 길이 기준으로 청크 처리하여 영속성 컨텍스트를 플러시/커밋 처리한다.

### 로그 처리 프로세서 추가해보기
불특정 다수의 사용자들의 로그를 수집하고 이를 batchSize만큼 모아서 데이터베이스에 플러시해야 하는 요구사항을 처리하기 위해 리액티브 스트림`Reactor`을 사용하려고 한다. 처리 프로세서는 스프링 빈(@Component)로 선언할것이며, 코드는 아래와 같다.

```kotlin
@Component
class UserAccessLogProcessor(
    private val repository: UserAccessLogRepository
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val emitterProcessor = EmitterProcessor.create<UserAccessLog>()
    private val flusSink = emitterProcessor.sink()
    private lateinit var disposable: Disposable

    fun send(item: UserAccessLog) {
        flusSink.next(item)
    }

    @PostConstruct
    protected fun init() {
        disposable = emitterProcessor
                .bufferTimeout(30, Duration.ofSeconds(5))
                .delaySequence(Duration.ofMillis(100))
                .limitRate(2)
                .parallel(20)
                .runOn(Schedulers.boundedElastic())
                .doOnNext {
                    logger.info("items = {}", it)
                }
                .flatMap {
                    Mono.fromCallable { repository.saveInBatch(it) }
                            .subscribeOn(Schedulers.boundedElastic())
                }
                .doOnError {
                    logger.error("Error", it)
                }
                .subscribe()
    }

    @PreDestroy
    protected fun destroy() {
        disposable.dispose()
    }

}
```

- EmitterProcessor를 활용하여 불특정 다수(멀티 스레드)에서 들어오는 이벤트를 수집하여 처리
- 이벤트에 대한 구독은 스프링 빈의 이벤트 라이프사이클을 활용했다.
- 배치 처리를 해야 하므로 이를 위해 bufferTimeout을 지정하여 개수를 배치 사이즈만큼 모아서 이벤트를 방출
- 만약 다량의 이벤트가 동시에 들어올 경우, 이를 처리하는 다운스트림의 연산자에서 문제가 생길 수 있기 때문에 이에 대한 적절한 배압 처리를 위해 limitRate와 delaySequence를 지정해줬다.(필요하다면 배압 정책을 지정해줘도 괜찮다)
- 데이터베이스에 대한 Blocking 연산을 회피하기 위해 데이터베이스 트랜잭션 부분은 별도의 스레드에서 처리하도록 분리
  * 적절한 배압 정책이나 딜레이 정책이 없다면 디비 커넥션 고갈이나 스레드 고갈 문제를 겪을 수 밖에 없다. 논블록킹 연산을 지원하는 데이터베이스라면 모르겠지만, 지금은 JDBC 베이스의 JPA를 사용하기 때문에 이에 대한 스레드 분리는 필수적이다.


#### 고려헤야 할 부분 & 개선해야 할 부분은?
- 결국 메모리에 이벤트 스트림을 모아서 처리하는 로직이기 때문에, 유실에 대한 부분은 반드시 고려해야 된다. 백업 전략으로는 디스크를 통하여 이벤트 데이터를 저장하는 방법이 있을 수도 있고 아니면 별도의 구현체(큐 혹은 NoSQL)를 사용하는 방법이 있을 수 있다.