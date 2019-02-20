package me.daniel.kotlinspringbootquerydsl.controller

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import me.daniel.kotlinspringbootquerydsl.dto.PersonDto
import me.daniel.kotlinspringbootquerydsl.repository.PersonRepository
import me.daniel.kotlinspringbootquerydsl.service.PersonService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.BDDMockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [PersonController::class])
//@OverrideAutoConfiguration(enabled = false)
class PersonControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

//    @InjectMocks
    @MockBean
    private lateinit var service: PersonService

    @Before
    fun setUp() {

    }


//    @MockBean
//    private lateinit var repository: PersonRepository




    @Test
    fun `목록을_요청할_수_있어야 한다`() {
        // Given
        val list = listOf(
            PersonDto.pageDto().apply {
                name = "kschoi"
                city = "city"
                street = "street"
                zipCode = "010101"
            }
        )
//        `when`(service.getCustomDtoPage(
//            predicate = BooleanBuilder().value!!,
//            pageable = PageRequest.of(0,10)
//        )).thenReturn(PageImpl(list))
        given(
            service.getCustomDtoPage(
                pageable = PageRequest.of(0, 10)
//                ArgumentMatchers.any(Predicate::class.java),
//                ArgumentMatchers.any(Pageable::class.java)
            )
        ).willReturn(PageImpl(list))


        // When
        val resultActions = this.mockMvc
            .perform(get("/persons/custom"))
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