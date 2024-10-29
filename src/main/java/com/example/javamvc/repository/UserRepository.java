package com.example.javamvc.repository;

import com.example.javamvc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 유저 조회
    Optional<User> findByEmail(String email);

}
