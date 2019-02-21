package me.daniel.kotlinspringbootquerydsl.controller

import me.daniel.kotlinspringbootquerydsl.dto.PersonDto
import me.daniel.kotlinspringbootquerydsl.service.PersonService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [PersonController::class])
class PersonControllerTest {

    @Autowired
    private lateinit var ctx: WebApplicationContext
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var service: PersonService

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(ctx)
                .build()
    }

    @Test
    fun `목록을 요청할 수 있어야 한다`() {
        // Given
        given(service.getCustomDtoPage(
            pageable = PageRequest.of(0, 10)
        )).willReturn(PageImpl(listOf()))

        // When
        val resultActions = this.mockMvc
            .perform(get("/persons/custom"))
            .andDo(print())
        // Then
        resultActions
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("totalElements").isNumber)
            .andExpect(jsonPath("totalElements").value(0))
            .andExpect(jsonPath("content").exists())
            .andExpect(jsonPath("content").isArray)
    }

    @Test
    fun `페이징을 할 수 있어야 한다`() {
        // Given
        given(
            service.getCustomDtoPage(
                pageable = PageRequest.of(0, 20)
            )
        ).willReturn(PageImpl(listOf(
            PersonDto.pageDto().apply {
                name = "kschoi"
                city = "city"
                street = "street"
                zipCode = "010101"
            }
        )))

        // When
        val resultActions = this.mockMvc
            .perform(
                get("/persons/custom")
                    .param("page", "0")
                    .param("size", "20")
            )
            .andDo(print())
        // Then
        resultActions
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("totalElements").isNumber)
            .andExpect(jsonPath("totalElements").value(1))
            .andExpect(jsonPath("content").exists())
            .andExpect(jsonPath("content").isArray)
    }

}