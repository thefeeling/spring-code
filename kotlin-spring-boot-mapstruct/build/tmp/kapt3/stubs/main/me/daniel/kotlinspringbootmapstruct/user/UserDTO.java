package me.daniel.kotlinspringbootmapstruct.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\b"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/UserDTO;", "", "()V", "CreateDTO", "ResponseDTO", "SourceDTO", "TargetDTO", "UserMapper", "kotlin-spring-boot-mapstruct"})
public final class UserDTO {
    
    public UserDTO() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$CreateDTO;", "", "name", "", "email", "gender", "Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;", "roles", "", "Lme/daniel/kotlinspringbootmapstruct/user/UserRole$Role;", "foreigner", "", "(Ljava/lang/String;Ljava/lang/String;Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;Ljava/util/List;Z)V", "getEmail", "()Ljava/lang/String;", "getForeigner", "()Z", "getGender", "()Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;", "getName", "getRoles", "()Ljava/util/List;", "kotlin-spring-boot-mapstruct"})
    public static final class CreateDTO {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String email = null;
        @org.jetbrains.annotations.NotNull()
        private final me.daniel.kotlinspringbootmapstruct.user.User.UserGender gender = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<me.daniel.kotlinspringbootmapstruct.user.UserRole.Role> roles = null;
        private final boolean foreigner = false;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getEmail() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final me.daniel.kotlinspringbootmapstruct.user.User.UserGender getGender() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<me.daniel.kotlinspringbootmapstruct.user.UserRole.Role> getRoles() {
            return null;
        }
        
        public final boolean getForeigner() {
            return false;
        }
        
        public CreateDTO(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String email, @org.jetbrains.annotations.NotNull()
        me.daniel.kotlinspringbootmapstruct.user.User.UserGender gender, @org.jetbrains.annotations.NotNull()
        java.util.List<? extends me.daniel.kotlinspringbootmapstruct.user.UserRole.Role> roles, boolean foreigner) {
            super();
        }
        
        public CreateDTO() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\u0018\u00002\u00020\u0001B_\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0002\u0010\u0010R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0016\"\u0004\b&\u0010\u0018R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0014\u00a8\u0006-"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$ResponseDTO;", "", "id", "", "name", "", "email", "gender", "Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;", "roles", "", "foreigner", "", "createdAt", "Ljava/time/LocalDateTime;", "updatedAt", "(JLjava/lang/String;Ljava/lang/String;Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;Ljava/util/List;ZLjava/time/LocalDateTime;Ljava/time/LocalDateTime;)V", "getCreatedAt", "()Ljava/time/LocalDateTime;", "setCreatedAt", "(Ljava/time/LocalDateTime;)V", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "getForeigner", "()Z", "setForeigner", "(Z)V", "getGender", "()Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;", "setGender", "(Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;)V", "getId", "()J", "setId", "(J)V", "getName", "setName", "getRoles", "()Ljava/util/List;", "setRoles", "(Ljava/util/List;)V", "getUpdatedAt", "setUpdatedAt", "kotlin-spring-boot-mapstruct"})
    public static final class ResponseDTO {
        private long id;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String name;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String email;
        @org.jetbrains.annotations.NotNull()
        private me.daniel.kotlinspringbootmapstruct.user.User.UserGender gender;
        @org.jetbrains.annotations.NotNull()
        private java.util.List<java.lang.String> roles;
        private boolean foreigner;
        @org.jetbrains.annotations.Nullable()
        private java.time.LocalDateTime createdAt;
        @org.jetbrains.annotations.Nullable()
        private java.time.LocalDateTime updatedAt;
        
        public final long getId() {
            return 0L;
        }
        
        public final void setId(long p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        public final void setName(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getEmail() {
            return null;
        }
        
        public final void setEmail(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final me.daniel.kotlinspringbootmapstruct.user.User.UserGender getGender() {
            return null;
        }
        
        public final void setGender(@org.jetbrains.annotations.NotNull()
        me.daniel.kotlinspringbootmapstruct.user.User.UserGender p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> getRoles() {
            return null;
        }
        
        public final void setRoles(@org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> p0) {
        }
        
        public final boolean getForeigner() {
            return false;
        }
        
        public final void setForeigner(boolean p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.time.LocalDateTime getCreatedAt() {
            return null;
        }
        
        public final void setCreatedAt(@org.jetbrains.annotations.Nullable()
        java.time.LocalDateTime p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.time.LocalDateTime getUpdatedAt() {
            return null;
        }
        
        public final void setUpdatedAt(@org.jetbrains.annotations.Nullable()
        java.time.LocalDateTime p0) {
        }
        
        public ResponseDTO(long id, @org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        java.lang.String email, @org.jetbrains.annotations.NotNull()
        me.daniel.kotlinspringbootmapstruct.user.User.UserGender gender, @org.jetbrains.annotations.NotNull()
        java.util.List<java.lang.String> roles, boolean foreigner, @org.jetbrains.annotations.Nullable()
        java.time.LocalDateTime createdAt, @org.jetbrains.annotations.Nullable()
        java.time.LocalDateTime updatedAt) {
            super();
        }
        
        public ResponseDTO() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$SourceDTO;", "", "name", "", "age", "", "(Ljava/lang/String;I)V", "getAge", "()I", "getName", "()Ljava/lang/String;", "kotlin-spring-boot-mapstruct"})
    public static final class SourceDTO {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        private final int age = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        public final int getAge() {
            return 0;
        }
        
        public SourceDTO(@org.jetbrains.annotations.NotNull()
        java.lang.String name, int age) {
            super();
        }
        
        public SourceDTO() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$TargetDTO;", "", "name", "", "age", "", "(Ljava/lang/String;I)V", "getAge", "()I", "getName", "()Ljava/lang/String;", "kotlin-spring-boot-mapstruct"})
    public static final class TargetDTO {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String name = null;
        private final int age = 0;
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getName() {
            return null;
        }
        
        public final int getAge() {
            return 0;
        }
        
        public TargetDTO(@org.jetbrains.annotations.NotNull()
        java.lang.String name, int age) {
            super();
        }
        
        public TargetDTO() {
            super();
        }
    }
    
    @org.mapstruct.Mapper(componentModel = "spring", unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE)
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0017\u00a8\u0006\u000e"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$UserMapper;", "", "fromUser", "Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$ResponseDTO;", "user", "Lme/daniel/kotlinspringbootmapstruct/user/User;", "toUser", "req", "Lme/daniel/kotlinspringbootmapstruct/user/UserDTO$CreateDTO;", "toUserRole", "", "Lme/daniel/kotlinspringbootmapstruct/user/UserRole;", "roles", "Lme/daniel/kotlinspringbootmapstruct/user/UserRole$Role;", "kotlin-spring-boot-mapstruct"})
    public static abstract interface UserMapper {
        
        @org.jetbrains.annotations.NotNull()
        @org.mapstruct.Mapping(source = "roles", target = "_roles", qualifiedByName = {"toUserRole"})
        public abstract me.daniel.kotlinspringbootmapstruct.user.User toUser(@org.jetbrains.annotations.NotNull()
        me.daniel.kotlinspringbootmapstruct.user.UserDTO.CreateDTO req);
        
        @org.jetbrains.annotations.NotNull()
        public abstract me.daniel.kotlinspringbootmapstruct.user.UserDTO.ResponseDTO fromUser(@org.jetbrains.annotations.NotNull()
        me.daniel.kotlinspringbootmapstruct.user.User user);
        
        @org.jetbrains.annotations.NotNull()
        @org.mapstruct.Named(value = "toUserRole")
        public abstract java.util.List<me.daniel.kotlinspringbootmapstruct.user.UserRole> toUserRole(@org.jetbrains.annotations.NotNull()
        java.util.List<? extends me.daniel.kotlinspringbootmapstruct.user.UserRole.Role> roles);
        
        @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 3)
        public static final class DefaultImpls {
            
            @org.jetbrains.annotations.NotNull()
            @org.mapstruct.Named(value = "toUserRole")
            public static java.util.List<me.daniel.kotlinspringbootmapstruct.user.UserRole> toUserRole(me.daniel.kotlinspringbootmapstruct.user.UserDTO.UserMapper $this, @org.jetbrains.annotations.NotNull()
            java.util.List<? extends me.daniel.kotlinspringbootmapstruct.user.UserRole.Role> roles) {
                return null;
            }
        }
    }
}