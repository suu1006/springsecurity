package controller;

import entity.User;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    // 유저 저장
    @PostMapping(value = "/save")
    public ResponseEntity<User> userSave(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    // 이메일로 유저 조회
//    @PostMapping(value = "/get/email")
//    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
//        return userService.findUserByEmail(email)
//                .map(ResponseEntity::ok)
//                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
//    }

    // 유저 정보 수정
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    // 유저 삭제
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }




}
