package me.daniel.kotlinspringbootmapstruct.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\u00048\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\u00020\r8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u00020\u00138\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R \u0010\u0018\u001a\u0004\u0018\u00010\u00198\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u00a8\u0006\u001f"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/UserRole;", "", "()V", "_createdAt", "Ljava/time/LocalDateTime;", "get_createdAt", "()Ljava/time/LocalDateTime;", "set_createdAt", "(Ljava/time/LocalDateTime;)V", "_updatedAt", "get_updatedAt", "set_updatedAt", "id", "", "getId", "()J", "setId", "(J)V", "role", "Lme/daniel/kotlinspringbootmapstruct/user/UserRole$Role;", "getRole", "()Lme/daniel/kotlinspringbootmapstruct/user/UserRole$Role;", "setRole", "(Lme/daniel/kotlinspringbootmapstruct/user/UserRole$Role;)V", "user", "Lme/daniel/kotlinspringbootmapstruct/user/User;", "getUser", "()Lme/daniel/kotlinspringbootmapstruct/user/User;", "setUser", "(Lme/daniel/kotlinspringbootmapstruct/user/User;)V", "Role", "kotlin-spring-boot-mapstruct"})
@javax.persistence.Entity(name = "T_USER_ROLES")
public class UserRole {
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Id()
    private long id;
    @org.jetbrains.annotations.Nullable()
    @com.fasterxml.jackson.annotation.JsonBackReference()
    @javax.persistence.JoinColumn(name = "id", insertable = false, updatable = false)
    @javax.persistence.ManyToOne(fetch = javax.persistence.FetchType.EAGER)
    private me.daniel.kotlinspringbootmapstruct.user.User user;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Column(name = "role", length = 10)
    private me.daniel.kotlinspringbootmapstruct.user.UserRole.Role role;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Column(name = "createdAt", columnDefinition = "DATETIME")
    @org.springframework.data.annotation.CreatedDate()
    private java.time.LocalDateTime _createdAt;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Column(name = "updatedAt", columnDefinition = "DATETIME")
    @org.springframework.data.annotation.LastModifiedDate()
    private java.time.LocalDateTime _updatedAt;
    
    public long getId() {
        return 0L;
    }
    
    public void setId(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public me.daniel.kotlinspringbootmapstruct.user.User getUser() {
        return null;
    }
    
    public void setUser(@org.jetbrains.annotations.Nullable()
    me.daniel.kotlinspringbootmapstruct.user.User p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public me.daniel.kotlinspringbootmapstruct.user.UserRole.Role getRole() {
        return null;
    }
    
    public void setRole(@org.jetbrains.annotations.NotNull()
    me.daniel.kotlinspringbootmapstruct.user.UserRole.Role p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.time.LocalDateTime get_createdAt() {
        return null;
    }
    
    public void set_createdAt(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.time.LocalDateTime get_updatedAt() {
        return null;
    }
    
    public void set_updatedAt(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime p0) {
    }
    
    public UserRole() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/UserRole$Role;", "", "(Ljava/lang/String;I)V", "GUEST", "ADMIN", "kotlin-spring-boot-mapstruct"})
    public static enum Role {
        /*public static final*/ GUEST /* = new GUEST() */,
        /*public static final*/ ADMIN /* = new ADMIN() */;
        
        Role() {
        }
    }
}