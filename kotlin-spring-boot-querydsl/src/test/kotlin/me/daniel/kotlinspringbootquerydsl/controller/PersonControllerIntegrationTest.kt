package me.daniel.kotlinspringbootquerydsl.controller

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerIntegrationTest {

    @Autowired
    private lateinit var ctx: WebApplicationContext

    private lateinit var mockMvcTest: MockMvc

    @Before
    fun setUp() {
        mockMvcTest = MockMvcBuilders.webAppContextSetup(ctx).build()
    }

    @Test
    fun `목록을_요청할_수_있어야 한다`() {
        this.mockMvcTest.perform(
            get("/persons")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("pageable.offset").exists())
            .andExpect(jsonPath("pageable.offset").isNumber)
            .andExpect(jsonPath("pageable.offset").value(0))
            .andExpect(jsonPath("pageable.pageSize").exists())
            .andExpect(jsonPath("pageable.pageSize").isNumber)
            .andExpect(jsonPath("pageable.pageSize").value(20))
            .andExpect(jsonPath("size").exists())
            .andExpect(jsonPath("size").isNumber)
            .andExpect(jsonPath("size").value(20))
            .andExpect(jsonPath("totalElements").exists())
            .andExpect(jsonPath("totalElements").isNumber)
            .andExpect(jsonPath("totalPages").exists())
            .andExpect(jsonPath("totalPages").isNumber)
            .andExpect(jsonPath("content").exists())
            .andExpect(jsonPath("content").isArray)
    }
}