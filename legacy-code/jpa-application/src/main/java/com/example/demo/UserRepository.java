package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Daniel on 2017. 7. 8..
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByName(String name);
    User findByNameAndAge(String name, int age);
}