package me.daniel.kotlinspringbootmapstruct.user

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
@RequestMapping("/users")
class UserController(
    private val userRepository: UserRepository,
    private val userMapper: UserDTO.UserMapper
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun create(
        @RequestBody dto: UserDTO.CreateDTO
    ): ResponseEntity<User> {
        val user = userRepository.save(userMapper.toUser(dto))
        return ResponseEntity.created(URI.create("/users/${user.id}")).build()
    }

    @GetMapping
    fun page(
        @PageableDefault(page = 0, size = 10)
        pageable: Pageable
    ): Page<UserDTO.ResponseDTO> {
        return userRepository.findAll(pageable).map {
            userMapper.fromUser(it)
        }
    }

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Long = 0L
    ): ResponseEntity<UserDTO.ResponseDTO> {
        val user = userRepository.findByIdOrNull(id)
        return if (user == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build<UserDTO.ResponseDTO>()
        } else ResponseEntity.ok(userMapper.fromUser(user))
    }

}