package me.daniel.kotlinspringbootquerydsl.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@ExtendWith(value = [RestDocumentationExtension::class, SpringExtension::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class PersonControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @DisplayName("단건을 조회할 수 있어야 한다.")
    fun documentTest() {
        this.mockMvc.perform(
            get("/persons/{id}", 1L)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andDo(print())
            .andDo(document("person-findOne",
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id").description("Member's id")
                ),
                responseFields(
                    fieldWithPath("address.city")
                        .type(JsonFieldType.STRING)
                        .description("The Member's email address"),
                    fieldWithPath("address.street")
                        .type(JsonFieldType.STRING)
                        .description("The Member's address city"),
                    fieldWithPath("address.zipCode")
                        .type(JsonFieldType.STRING)
                        .description("The Member's address city"),
                    fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("The Member's address street")
                )
            ))
    }


    @Test
    @DisplayName("목록을_요청할_수_있어야 한다")
    fun shouldRequestPage() {
        this.mockMvc.perform(
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