# mapStruct
## Summary & Why?
- 타입 세이프하게 bean 매핑을 도와주는 어노테이션 프로세서
- 보통 JPA 기반의 어플리케이션을 개발하게 되면 만나게 되는 DTO 변환 작업은 대부분이 반복적인 작업이 대부분
- ModelMapper와 같은 매핑 구현체가 존재하지만, ModelMapper의 경우 리플렉션 기반으로 동작하기도 하고 퍼포먼스에 대한 이슈가 많은편
- 도메인 객체를 풍부하게 사용하면서, 반환 데이터가 달라지게 될 경우 이를 적절하고 큰 힘을 들이지 않고 매핑할 수 있도록 도와주는 것이 바로 mapStruct
- *mapStruct 역시 메뉴얼에서 확인할 수 있듯이, 편하게 사용하려면 어느정도의 학습 기간을 거쳐야 편하게 사용할 수 있는듯하다.*

## Simple Usage
### 개발 환경
- Kotlin 1.3.31
- Spring BOOT 2.1.5 RELEASE(WEB, DATA-JPA)
- H2


```kotlin
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.31"
    id("org.springframework.boot") version "2.1.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("org.jetbrains.kotlin.kapt") version kotlinVersion
}

group = "me.daniel"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // map-struct
    // map-struct Annotation Processor
    implementation("org.mapstruct:mapstruct:1.3.0.Final")
    kapt("org.mapstruct:mapstruct-processor:1.3.0.Final")
    kaptTest("org.mapstruct:mapstruct-processor:1.3.0.Final")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
```

```kotlin
@Mapper
interface UserMapper {
    fun toUser(req: CreateDTO): User
    fun fromUser(user: User): ResponseDTO
}
```
- kapt를 활용하여 매퍼 클래스에 대응하는 Java 구현체를 생성해주는데, 도메인 객체와 DTO 객체간의 1:1 매핑을 지원하며, 1:1 매핑이 원활하게 되기 위해서는 Source 쪽의 타입 선언와 Target쪽의 타입 선언이 똑같아야 자연스럽게 매핑을 처리할 수 있다.


```kotlin
@Mapper
public interface AddressMapper {

    @Mapping(source = "person.description", target = "description")
    @Mapping(source = "address.houseNo", target = "houseNumber")
    DeliveryAddressDto personAndAddressToDeliveryAddressDto(Person person, Address address);
}
```

- 1:1 매핑이 아니라고 추가적인 매핑을 진행해야 하는 경우, 위와 같이 매핑에 대한 룰을 지정해주면 된다.

```kotlin
@Mapper(componentModel = "spring")
interface UserMapper {
    @Mapping(source = "roles", target = "_roles", qualifiedByName = ["toUserRole"])
    fun toUser(req: CreateDTO): User
    fun fromUser(user: User): ResponseDTO

    @Named("toUserRole")
    fun toUserRole(roles: List<UserRole.Role>) = roles.map {
        UserRole().apply {
            this.role = it
        }
    }
}
```
- 추가적인 매핑말고도 조금 복잡한 매핑 룰이 필요한 경우 인터페이스의 `default` 메서드를 선언하여 이를 활용할 수 있다.(코틀린에서는 바로 method를 선언해두면 이게 default 메서드 역할을 수행해준다.) 이 때 사용하는 어노테이션이 바로 `@Named` 어노테이션으로 선언해놓은 값을 `qualifiedByName`에 매핑해주면 된다.

- 생성한 매퍼에 대해 컴포넌트 모델을 지정 할 수 있다.
  - default: 컴포넌트 모델을 생성하지 않으며, `Mappers#getMapper(Class)`로 선언해두었던 매퍼 인터페이스의 타입으로 객체를 가져올 수 있다.
  - spring, jsr330: 컴포넌트 모델을 스프링 어플리케이션 컨텍스트에서 컴포넌트 스캔을 통하여 DI 받을 수 있다. 실제 선언해둔 코드 일부를 살펴보면 아래와 같다.
  ![mapstruct-img](https://i.imgur.com/PrFkBbM.png)
  - cdi

## Tip & 사용 시 주의점
- `source`측과 `target`측의 클래스 선언에서 기본 생성자가 반드시 선언되어 있어야 한다.
- Kotlin, SpringBoot, Data-JPA와 함께 사용하게 될 경우, `kotlin-jpa` 플러그인을 활용하여 no-argument-consturctor에 대해 코드를 생성해주는 방식으로 기본 셋팅을 해놓고 진행하는데, 기본 생성자에 대해 바이트코드 레벨에서 Generation을 해주기 때문에 **결국 기본 생성자를 명시적으로 선언해줘야 하는 불편함이 존재하는거 같다.**
```kotlin
@Entity(name = "T_USER")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @Column(name = "name", length = 20)
    var name: String = ""

    @Column(name = "email", length = 100)
    var email: String = ""

    @Column(name = "gender", length = 10)
    var gender: UserGender = UserGender.MALE

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @BatchSize(size = 30)
    var _roles: MutableList<UserRole> = mutableListOf()

    @Column(name = "foreigner", length = 10)
    var foreigner: Boolean = false

    @CreatedDate
    @Column(name = "createdAt", columnDefinition = "DATETIME")
    var _createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @Column(name = "updatedAt", columnDefinition = "DATETIME")
    var _updatedAt: LocalDateTime = LocalDateTime.now()

    enum class UserGender {
        MALE, FEMALE
    }

    constructor(name: String = "", email: String = "") {
        this.name = name
        this.email = email
    }

}
```
> 간단한 엔티티 코드 예제인데, 위 코드와 같이 별도의 생성자 선언을 안해두고 사용했을 경우 기본 생성자만 포함이 된다. 만약 별도의 생성자 선언이 필요한 경우 코드 가장 하단과 같이 필요한 선언을 해두면 되며 Kotlin의 Argument Default Value를 적극적으로 활용하여 필수 인자값과 그렇지 않은 값을 분리하는 전략을 취하는 것이 좋을거 같다.

- 컬럼과 매핑되는 프로퍼티가 아닌 값을 매핑하게 될 경우가 생길 수 있는데, 이럴 때 사용하면 좋은 것이 Lazy Delegate이다. 관계 매핑에 사용되는 것이 아닌 해당 도메인 객체의 상태를 유연하게 표현할 수 있으며 바인딩한 프로퍼티를 바로 mapStruct 바인딩으로 넘길 수 있어서 실제 예제 코드를 작성해보니 편리함을 느낄 수 있었다.
```kotlin
class User {
    ...생략...

    @get:Transient
    @delegate:Transient
    val roles: MutableList<String> by lazy {
        this._roles.map { it.role.name }.toMutableList()
    }

    @get:Transient
    @delegate:Transient
    val createdAt: LocalDateTime by lazy {
        this._createdAt
    }

    @get:Transient
    @delegate:Transient
    val updatedAt: LocalDateTime by lazy {
        this._updatedAt
    }
}

```

- Generation 해주는 코드는 기본적으로 세터 기반의 데이터 바인딩을 해주고 있는 것을 확인 할수 있는데,이를 빌더 기반으로 변경하는 것도 가능하다고 한다. 빌더 구현체로는 유명한 Lombok, AutoValue 등이 사용 가능하다고 메뉴얼에 명시되어 있다.
  - [참고 링크](http://mapstruct.org/documentation/stable/reference/html/#mapping-with-builders)
  - 하지만, 코틀린 기반으로 사용하게 될 경우 보통 lombok과 같은 빌더 구현을 대체 해주는 구현이 없어서 당분간은 세터 기반의 주입을 사용해야 될듯

> 예제 코드는 [링크](http://mapstruct.org/documentation/stable/reference/html/#mapping-with-builders)를 통하여 확인 할 수 있습니다.