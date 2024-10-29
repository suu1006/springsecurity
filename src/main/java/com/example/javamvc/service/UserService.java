package com.example.javamvc.service;

import com.example.javamvc.domain.User;
import com.example.javamvc.exception.UserNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.javamvc.repository.UserRepository;

import java.util.Collections;

@Service
public class UserService {

    public UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 유저 저장
     * @param user
     * @return
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 유저 조회 (이메일)
     *
     * @param email
     * @return
     */
    public User findUserByEmail(String email) {
        // return userRepository.findByEmail(email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }


    /**
     * 유저 정보 수정
     * @param id, userDetails
     */
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found : " + id));

        BeanUtils.copyProperties(userDetails, user, "id"); // id 제외, 나머지 복사

        return userRepository.save(user);
    }

    /**
     * 유저 삭제
     * @param id
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }






}
