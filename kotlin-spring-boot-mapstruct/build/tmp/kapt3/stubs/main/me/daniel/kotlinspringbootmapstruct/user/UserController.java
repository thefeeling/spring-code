package me.daniel.kotlinspringbootmapstruct.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0001\u0010\r\u001a\u00020\u000eH\u0017J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b2\b\b\u0003\u0010\u0011\u001a\u00020\u0012H\u0017J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\b\b\u0001\u0010\u0015\u001a\u00020\u0016H\u0017R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/UserController;", "", "userRepository", "Lme/daniel/kotlinspringbootmapstruct/user/UserRepository;", "userMapper", "Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$UserMapper;", "(Lme/daniel/kotlinspringbootmapstruct/user/UserRepository;Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$UserMapper;)V", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "create", "Lorg/springframework/http/ResponseEntity;", "Lme/daniel/kotlinspringbootmapstruct/user/User;", "dto", "Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$CreateDTO;", "get", "Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$ResponseDTO;", "id", "", "page", "Lorg/springframework/data/domain/Page;", "pageable", "Lorg/springframework/data/domain/Pageable;", "kotlin-spring-boot-mapstruct"})
@org.springframework.web.bind.annotation.RequestMapping(value = {"/users"})
@org.springframework.web.bind.annotation.RestController()
public class UserController {
    private final org.slf4j.Logger logger = null;
    private final me.daniel.kotlinspringbootmapstruct.user.UserRepository userRepository = null;
    private final me.daniel.kotlinspringbootmapstruct.user.UserDTO.UserMapper userMapper = null;
    
    @org.jetbrains.annotations.NotNull()
    @org.springframework.web.bind.annotation.PostMapping()
    public org.springframework.http.ResponseEntity<me.daniel.kotlinspringbootmapstruct.user.User> create(@org.jetbrains.annotations.NotNull()
    @org.springframework.web.bind.annotation.RequestBody()
    me.daniel.kotlinspringbootmapstruct.user.UserDTO.CreateDTO dto) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.springframework.web.bind.annotation.GetMapping()
    public org.springframework.data.domain.Page<me.daniel.kotlinspringbootmapstruct.user.UserDTO.ResponseDTO> page(@org.jetbrains.annotations.NotNull()
    @org.springframework.data.web.PageableDefault(page = 0, size = 10)
    org.springframework.data.domain.Pageable pageable) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.springframework.web.bind.annotation.GetMapping(value = {"/{id}"})
    public org.springframework.http.ResponseEntity<me.daniel.kotlinspringbootmapstruct.user.UserDTO.ResponseDTO> get(@org.springframework.web.bind.annotation.PathVariable()
    long id) {
        return null;
    }
    
    public UserController(@org.jetbrains.annotations.NotNull()
    me.daniel.kotlinspringbootmapstruct.user.UserRepository userRepository, @org.jetbrains.annotations.NotNull()
    me.daniel.kotlinspringbootmapstruct.user.UserDTO.UserMapper userMapper) {
        super();
    }
}