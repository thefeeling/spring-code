package me.daniel.kotlinspringbootmapstruct.user

import org.mapstruct.*
import java.time.LocalDateTime

class UserDTO {
    class CreateDTO(
        val name: String = "",
        val email: String = "",
        val gender: User.UserGender = User.UserGender.MALE,
        val roles: List<UserRole.Role> = listOf(UserRole.Role.GUEST),
        val foreigner: Boolean = false
    )

    class ResponseDTO(
        var id: Long = 0L,
        var name: String = "",
        var email: String = "",
        var gender: User.UserGender = User.UserGender.MALE,
        var roles: MutableList<String> = mutableListOf(),
        var foreigner: Boolean = false,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
    )

    @Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE
    )
    interface UserMapper {
        @Mapping(source = "roles", target = "_roles", qualifiedByName = ["toUserRole"])
        fun toUser(req: CreateDTO): User
        fun fromUser(user: User): ResponseDTO

        @Named("toUserRole")
        fun toUserRole(roles: List<UserRole.Role>) = roles.map {
            UserRole().apply {
                this.role = it
            }
        }
    }

}