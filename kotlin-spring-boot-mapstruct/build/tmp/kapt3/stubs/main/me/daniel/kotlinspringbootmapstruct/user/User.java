package me.daniel.kotlinspringbootmapstruct.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000f\b\u0017\u0018\u00002\u00020\u0001:\u00018B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00048\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\bR\u001b\u0010\u0013\u001a\u00020\u00048WX\u0097\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0014\u0010\u0006R\u001e\u0010\u0017\u001a\u00020\u00188\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001d\u001a\u00020\u001e8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010#\u001a\u00020$8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u001e\u0010)\u001a\u00020*8\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001e\u0010/\u001a\u00020\u00188\u0016@\u0016X\u0097\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001a\"\u0004\b1\u0010\u001cR!\u00102\u001a\b\u0012\u0004\u0012\u00020\u00180\n8WX\u0097\u0084\u0002\u00a2\u0006\f\n\u0004\b4\u0010\u0016\u001a\u0004\b3\u0010\rR\u001b\u00105\u001a\u00020\u00048WX\u0097\u0084\u0002\u00a2\u0006\f\n\u0004\b7\u0010\u0016\u001a\u0004\b6\u0010\u0006\u00a8\u00069"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/User;", "", "()V", "_createdAt", "Ljava/time/LocalDateTime;", "get_createdAt", "()Ljava/time/LocalDateTime;", "set_createdAt", "(Ljava/time/LocalDateTime;)V", "_roles", "", "Lme/daniel/kotlinspringbootmapstruct/user/UserRole;", "get_roles", "()Ljava/util/List;", "set_roles", "(Ljava/util/List;)V", "_updatedAt", "get_updatedAt", "set_updatedAt", "createdAt", "getCreatedAt", "createdAt$delegate", "Lkotlin/Lazy;", "email", "", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "foreigner", "", "getForeigner", "()Z", "setForeigner", "(Z)V", "gender", "Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;", "getGender", "()Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;", "setGender", "(Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;)V", "id", "", "getId", "()J", "setId", "(J)V", "name", "getName", "setName", "roles", "getRoles", "roles$delegate", "updatedAt", "getUpdatedAt", "updatedAt$delegate", "UserGender", "kotlin-spring-boot-mapstruct"})
@javax.persistence.Entity(name = "T_USER")
public class User {
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Id()
    private long id;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Column(name = "name", length = 20)
    private java.lang.String name;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Column(name = "email", length = 100)
    private java.lang.String email;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Column(name = "gender", length = 10)
    private me.daniel.kotlinspringbootmapstruct.user.User.UserGender gender;
    @org.jetbrains.annotations.NotNull()
    @org.hibernate.annotations.BatchSize(size = 30)
    @javax.persistence.OneToMany(mappedBy = "user", cascade = {javax.persistence.CascadeType.ALL}, fetch = javax.persistence.FetchType.LAZY)
    private java.util.List<me.daniel.kotlinspringbootmapstruct.user.UserRole> _roles;
    @javax.persistence.Column(name = "foreigner", length = 10)
    private boolean foreigner;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Column(name = "createdAt", columnDefinition = "DATETIME")
    @org.springframework.data.annotation.CreatedDate()
    private java.time.LocalDateTime _createdAt;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Column(name = "updatedAt", columnDefinition = "DATETIME")
    @org.springframework.data.annotation.LastModifiedDate()
    private java.time.LocalDateTime _updatedAt;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Transient()
    private final kotlin.Lazy roles$delegate = null;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Transient()
    private final kotlin.Lazy createdAt$delegate = null;
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Transient()
    private final kotlin.Lazy updatedAt$delegate = null;
    
    public long getId() {
        return 0L;
    }
    
    public void setId(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    public void setName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getEmail() {
        return null;
    }
    
    public void setEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public me.daniel.kotlinspringbootmapstruct.user.User.UserGender getGender() {
        return null;
    }
    
    public void setGender(@org.jetbrains.annotations.NotNull()
    me.daniel.kotlinspringbootmapstruct.user.User.UserGender p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.util.List<me.daniel.kotlinspringbootmapstruct.user.UserRole> get_roles() {
        return null;
    }
    
    public void set_roles(@org.jetbrains.annotations.NotNull()
    java.util.List<me.daniel.kotlinspringbootmapstruct.user.UserRole> p0) {
    }
    
    public boolean getForeigner() {
        return false;
    }
    
    public void setForeigner(boolean p0) {
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
    
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Transient()
    public java.util.List<java.lang.String> getRoles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Transient()
    public java.time.LocalDateTime getCreatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.persistence.Transient()
    public java.time.LocalDateTime getUpdatedAt() {
        return null;
    }
    
    public User() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004\u00a8\u0006\u0005"}, d2 = {"Lme/daniel/kotlinspringbootmapstruct/user/User$UserGender;", "", "(Ljava/lang/String;I)V", "MALE", "FEMALE", "kotlin-spring-boot-mapstruct"})
    public static enum UserGender {
        /*public static final*/ MALE /* = new MALE() */,
        /*public static final*/ FEMALE /* = new FEMALE() */;
        
        UserGender() {
        }
    }
}