package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName("이기훈" + i);
            user.setAge(30);
            user.setAddress("이수");
            user.setCountSex(5 * i);
            user.setGender("M");
            user.setMoSol(true);
            userRepository.save(user);
        }
    }
}
