package com.sparta.springsecondys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.sparta.springsecondys.repository")
@SpringBootApplication
public class SpringSecondYsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecondYsApplication.class, args);
    }

}
