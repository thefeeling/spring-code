

# Excel, CSV Sample


### AbstractXlsView
- 스프링 프레임워크 4.2부터 org.springframework.web.servlet.view.document.AbstractXlsView는 Excel 뷰 클래스를 제공함.
- Apache POI를 기반으로 하며, 오래된 AbstractExcelView 클래스를 대체하는 특수 하위 클래스(AbstXlsxView 및 AbstractXlsxStreamingView)를 사용


### 예시
```kotlin
@SpringBootApplication
@Controller
class KotlinSpringPdfExcelExampleApplication {

    companion object {
        val HEADER = "Content-Disposition" to "attachment; filename=\"stat.xls\""
    }

    @GetMapping(params = ["format=xls"])
    @RequestMapping("/stat.xls")
    fun list(): ModelAndView {
        val list = mutableListOf<Person>()
        for (i in 1..100) list.add(Person("kschoi$i", i))
        // xlsView 컴포넌트를 찾아서 처리.
        return ModelAndView("xlsView")
            .addObject("rows", list)
    }

    @RequestMapping("/stat")
    @ResponseBody
    fun listT(response: HttpServletResponse): MutableList<Person> {
        response.setHeader(HEADER.first, HEADER.second)
        val list = mutableListOf<Person>()
        for (i in 1..100) list.add(Person("kschoi$i", i))
        return list
    }


}


@Component("xlsView")
class XlsView : AbstractXlsView() {

    companion object {
        val HEADER = "Content-Disposition" to "attachment; filename=\"stat.xls\""
    }


    override fun buildExcelDocument(
        model: MutableMap<String, Any>,
        workbook: Workbook,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        // 컴포넌트 내부에서 HTTP RESPONSE 헤더 값을 조정
        response.setHeader(HEADER.first, HEADER.second)
        val sheet = workbook.createSheet("sheet")
        (model["rows"] as MutableList<*>).forEachIndexed { i, it ->
            val person: Person = it as Person
            val row = sheet.createRow(i)

            row.createCell(0).setCellValue(person.name)
            row.createCell(1).run {
                setCellType(CellType.NUMERIC)
                setCellValue(person.age.toDouble())
            }

        }
    }

}


```

### 설정 관련
- WebMvc 설정 클래스를 이용하여 빈을 선언하여 사용 가능한듯 => 근데 설정 클래스는 왜 자꾸 바뀌는거야..........
  - WebMvcConfigurerAdapter.class Deprecated.......
- 부트의 application.properties를 이용하여 클라이언트가 소비하는 데이터의 형식을 지정할 수 있음
### application.properties
```properties
spring.mvc.contentnegotiation.favor-parameter=true
spring.mvc.contentnegotiation.favor-path-extension=true
spring.mvc.contentnegotiation.media-types.xls=application/vnd.ms-excel
```


# Link
- https://www.codejava.net/frameworks/spring/spring-mvc-with-csv-file-download-example
- http://javacan.tistory.com/entry/spring-boot-20-excel-download
- https://www.codejava.net/frameworks/spring/spring-web-mvc-with-pdf-view-example-using-itext-5x