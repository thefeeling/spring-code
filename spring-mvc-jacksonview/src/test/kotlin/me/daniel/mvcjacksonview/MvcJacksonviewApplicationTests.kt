package me.daniel.mvcjacksonview

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

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
