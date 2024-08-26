package com.sparta.springsecondys.repository;

import com.sparta.springsecondys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
