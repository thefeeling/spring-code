package me.daniel.kotlinspringpdfexcelexample

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Workbook
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.document.AbstractXlsView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


data class Person(
    val name: String,
    val age: Int
)


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

fun main(args: Array<String>) {
    runApplication<KotlinSpringPdfExcelExampleApplication>(*args)
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

