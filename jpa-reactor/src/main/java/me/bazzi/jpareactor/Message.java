package me.bazzi.jpareactor;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String contents;
    @Enumerated(EnumType.STRING)
    private ReadYn readYn;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public enum ReadYn {
        READ, UN_READ
    }

    @Builder
    public Message(String userId, String contents) {
        Assert.notNull(userId, "required userId");
        Assert.notNull(contents, "required contents");
        this.userId = userId;
        this.readYn = ReadYn.READ;
        this.contents = contents;
    }
}
