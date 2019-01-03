package me.daniel.batch.repository;

import me.daniel.batch.domain.User;
import me.daniel.batch.domain.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUpdatedDateBeforeAndStatusEquals(LocalDateTime localDateTime, UserStatus status);
    Page<User> findByUpdatedDateBeforeAndStatusEquals(LocalDateTime localDateTime, UserStatus status, Pageable pageable);


}
