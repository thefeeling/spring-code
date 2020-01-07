# Webflux + JPA 강제로 끼워 맞춰보기

### Why?
- JDBC를 Reactive하게 사용할 수 있는 안정적인 방법은 아직 존재하지 않음
  - 물론 R2DBC와 같은 구현체가 존재하지만, 아직 1.0.0 RELEASE가 나오지 않은 상황
- 스프링 공식 문서를 참고해보면, 굳이 Reactive 모델에 JDBC와 같은 Block API를 사용하는 것보단 기존 Spring MVC를 사용하는 것이 차라리 나을 수 있다고 언급이 되어 있는 부분도 있음

### 그럼에도 불구하고 왜?
- 웹플럭스 그리고 리엑티브에 대한 학습

### 그렇다면 굳이 끼워 맞추기를 했을 때 그나마 나은 프렉티스는?
- 동기 구간에 대한 `Scheduler`를 지정하여 사용하자
    - 리액터의 `Schedulers.boundedElastic` 혹은 `Schedulers.elastic`과 같은 스케줄러를 사용
    ```java
    repository.findById(id).subscribeOn(Schedulers.boundedElastic());
    ```
    - 스케줄러의 스레드 개수는 DB 커넥션풀 사이즈와 동일하게 맞춤
    ```java
    @Configuration
    public class SchedulerConfiguration {
        private final Integer connectionPoolSize;
    
        public SchedulerConfiguration(@Value("${spring.datasource.hikari.maximum-pool-size}") Integer connectionPoolSize) {
            this.connectionPoolSize = connectionPoolSize;
        }
    
        @Bean
        @Primary
        public Scheduler boundedElasticScheduler() {
            return Schedulers.newBoundedElastic(
                    connectionPoolSize,
                    connectionPoolSize / 2,
                    "boundedElasticScheduler"
            );
        }
    }  
    ```
### 아직 이해하지 못한 내용은?
  - 스케줄링하는 큐에 대한 사이즈를 어떻게 지정하는게 좋을까?
  - 적절한 DB 트랜잭션 방법은? 
  
### 참고링크
- [Spring webflux and reading from database](https://stackoverflow.com/questions/42299455/spring-webflux-and-reading-from-database)
- [Project Reactor](https://kwonnam.pe.kr/wiki/reactive_programming/reactor)
- [리액티브하게 리팩토링하기 - JDBC 마이그레이션 해부](http://blog.lespinside.com/refactoring-to-react/)
