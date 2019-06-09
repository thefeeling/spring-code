# @JsonView와 @JsonFilter를 사용하여 Partial response 구현하기
## 개요
JSON HTTP API를 구현할 때, 페이징만 구현하는 경우를 쉽게 볼 수 있는데, 여기서 한 걸음 더 나아가 조금 더 나은 퍼포먼스를 위하여 클라이언트가 필요한 필드만 골라서 받을 수 있도록 구현하는 것이 필요할 수 있다. 예를 들어 아래와 같은 상황을 예를 들어 볼 수 있다.

- HTTP Reqeust
```
HTTP/1.1 GET /books/1?fields=isbn,title
```

- HTTP Response
```javascript
{
    "isbn": "978-3-16-148410-0",
    "title": "Book Title"
}
```

책 데이터를 조회하는 JSON API가 있다고 했을 때, 헤당 API에서 제공하는 정보 중 일부 정보만을 클라이언트에서 필요한 경우에 fields 쿼리스트링에 필요한 키 값을 콤마 베이스로 나열하여 요청하는 예시이다. 클라이언트가 필요한 응답만을 제공하는 동시에 조금 더 나은 네트워크 레벨에서의 퍼포먼스를 가져갈 수 있다. 이런 부분적인 응답 값을 반환하는 것을 `Partial Response`라고 부르며 REST와 관련된 문서에서 심심찮게 발견할 수 있는 내용이다.

- [GOOGLE-REST-Improve performance](https://developers.google.com/drive/api/v3/performance)
- [조대협님 블로그, REST API 디자인 가이드](https://bcho.tistory.com/914)
- [MS, API 디자인](https://docs.microsoft.com/ko-kr/azure/architecture/best-practices/api-design)


구현하는 언어와 환경에 따라 이를 구현하는 방법에는 차이가 존재하는데, 일반적으로 Spring Framework, Boot 환경에서는 JSON 데이터에 대한 처리를 GSON 혹은 Jackson 라이브러리가 담당하게 되는데 그 중에서도 Jackson을 사용하는 환경에서 간단하게 partial response를 구현하는 예제를 작성해보려고 한다.

## 개발 환경
- Kotlin 1.3.31
- Spring Boot 2.1.5 RELEASE
  - WEB


## 1. @JsonView를 사용한 예제
@JsonView 어노테이션을 활용하면 계층적인 부분 렌더링이 가능하다. @JsonView에 대한 계층 정의 예와 유저에 대한 예제 엔티티는 아래와 같다.

```kotlin
interface Views {
    interface List
    interface Get: List
}

class User(
    @JsonView(Views.List::class)
    val id: UUID = UUID.randomUUID(),
    @JsonView(Views.List::class)
    val email: String,
    @JsonView(Views.List::class)
    val name: String,
    @JsonView(Views.Get::class)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @JsonView(Views.Get::class)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
```

위와 같이 선언할 경우, `@JsonView(Dto.Views.List::class)`로 데이터를 처리하게 되면 모든 데이터를 유저 엔티티에서 `id`, `email`, `name`만을 Jackson 라이브러리가 serialize하게 된다. 반대로, `@JsonView(Dto.Views.Get::class)`으로 지정해놓게 되면 유저 엔티티의 모든 데이터가 serialize된다.

Spring Mvc에서는 요청 컨트롤러 매핑 메서드에 @JsonView를 명시해주면 해당 뷰로 Serailize 할 수 있도록 지원하고 있다.

```kotlin
@RestController
@RequestMapping
class BookController {
    private companion object {
        val user = Dto.User(
            name = "Park",
            email = "park@gmail.com"
        )
    }

    @GetMapping("/get")
    @JsonView(Dto.Views.Get::class)
    fun jsonViewGet() = user

    @GetMapping("/list")
    @JsonView(Dto.Views.List::class)
    fun jsonViewList() = listOf(user)
}
```

@JsonView를 통하여 제대로 결과가 반환되는지 테스트 코드를 간단하게 작성해보면 아래와 같다. 테스트코드는 스프링 부트의 통합테스트 환경을 그대로 사용했으며, jsonPath 라이브러리를 사용하여 해당 키 값이 제대로 존재하는지 그리고 타입이 정확한지 유무까지 테스트를 해봤다.

### 테스트코드
```kotlin
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MvcJacksonviewApplicationTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `JsonView_Get_Test`() {
        mockMvc.perform(
            get("/get")
        )
        .andDo(print())
        .andExpect(status().isOk)
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("id").exists())
        .andExpect(jsonPath("id").isString)
        .andExpect(jsonPath("email").exists())
        .andExpect(jsonPath("email").isString)
        .andExpect(jsonPath("name").exists())
        .andExpect(jsonPath("name").isString)
        .andExpect(jsonPath("createdAt").exists())
        .andExpect(jsonPath("createdAt").isString)
        .andExpect(jsonPath("updatedAt").exists())
        .andExpect(jsonPath("updatedAt").isString)
    }

    @Test
    fun `JsonView_List_Test`() {
        mockMvc.perform(
            get("/list")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("[0].id").exists())
            .andExpect(jsonPath("[0].id").isString)
            .andExpect(jsonPath("[0].email").exists())
            .andExpect(jsonPath("[0].email").isString)
            .andExpect(jsonPath("[0].name").exists())
            .andExpect(jsonPath("[0].name").isString)
            .andExpect(jsonPath("[0].createdAt").doesNotExist())
            .andExpect(jsonPath("[0].updatedAt").doesNotExist())
    }

    @Test
    fun `JsonFilter_single`() {
        mockMvc.perform(
            get("/json-filter")
                .param("fields", "isbn")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("isbn").exists())
            .andExpect(jsonPath("isbn").isString)
            .andExpect(jsonPath("title").doesNotExist())
            .andExpect(jsonPath("content").doesNotExist())
            .andExpect(jsonPath("createdAt").doesNotExist())
            .andExpect(jsonPath("updatedAt").doesNotExist())
    }
}
```

## 2. @JsonFilter를 사용하는 예제
위에서 본 @JsonView의 경우, 딱 지정해놓은 계층 구조 혹은 뷰가 아닐 경우 필드에 대한 선택 자체가 불가능하다. 결국 서버 어플리케이션에서 정해놓은 구조로만 부분 뷰를 응답 받을 수 있는 구조인데, 실제 요청에 부합하는 응답 값만 전달하기 위해서는 Jackson의 @JsonFilter 어노테이션과 Spring MVC의 MappingJacksonValue를 활용하면 해당 구현이 가능하다.

```kotlin
@JsonFilter("bookFilter")
class Book (
    val isbn: String,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

class GetReq {
    var fields: List<String> = emptyList()
}
```

```kotlin
@RestController
@RequestMapping
class BookController {
    private companion object {
        val book: Dto.Book = Dto.Book(
            isbn = UUID.randomUUID().toString(),
            title = "Title",
            content = "Content"
        )
    }

    @GetMapping("/json-filter")
    fun jsonFilter(reqDto: GetReq) = MappingJacksonValue(book).apply {
        filters = SimpleFilterProvider().also {
            it.addFilter("bookFilter",
                if (reqDto.fields.isNotEmpty()) SimpleBeanPropertyFilter.filterOutAllExcept(reqDto.fields.toSet())
                else SimpleBeanPropertyFilter.serializeAll()
            )
        }
    }
}
```

### 테스트코드
```kotlin
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MvcJacksonviewApplicationTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `JsonFilter_single`() {
        mockMvc.perform(
            get("/json-filter")
                .param("fields", "isbn")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("isbn").exists())
            .andExpect(jsonPath("isbn").isString)
            .andExpect(jsonPath("title").doesNotExist())
            .andExpect(jsonPath("content").doesNotExist())
            .andExpect(jsonPath("createdAt").doesNotExist())
            .andExpect(jsonPath("updatedAt").doesNotExist())
    }

    @Test
    fun `JsonFilter_comma_separator`() {
        mockMvc.perform(
            get("/json-filter")
                .param("fields", "isbn,title")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("isbn").exists())
            .andExpect(jsonPath("isbn").isString)
            .andExpect(jsonPath("title").exists())
            .andExpect(jsonPath("title").isString)
            .andExpect(jsonPath("content").doesNotExist())
            .andExpect(jsonPath("createdAt").doesNotExist())
            .andExpect(jsonPath("updatedAt").doesNotExist())
    }
}
```

간단하게 @JsonView와 @JsonFilter를 사용하여 partial response에 대한 구현을 해볼 수 있었는데, 여기서 조금 더 나아가 복잡한 조건이나 구조에서의 구현이 필요할 경우 [Squiggly Filter ](https://github.com/bohnman/squiggly-java)와 같은 구현체를 사용하는 것도 좋은 선택이 될 수 있을거 같다.

> 예제는 [링크](https://github.com/thefeeling/spring-code/tree/develop/spring-mvc-jacksonview)를 통하여 확인 가능합니다.

## 참고
- [spring-mvc – Spring MVC 컨트롤러에서 JsonView의 동적 선택](https://codeday.me/ko/qa/20190512/527260.html)
- [Jackson JSON Views](https://www.baeldung.com/jackson-json-view-annotation)