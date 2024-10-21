package com.example.javamvc.entity;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import repository.UserRepository;
import service.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User existingUser;
    private String email;
    private Long id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        email = "test@naver.com";
        id = 1L;
        existingUser = User.builder()
                .id(id)
                .name("test")
                .email(email)
                .password("test")
                .phoneNumber("010-1234-5678")
                .role("USER")
                .build();
    }


    @Test
    @DisplayName("유저 저장 테스트")
    public void saveUser() {
        // given
        User saveParams = User.builder()
                .name("test")
                .email("test@naver.com")
                .password("test")
                .phoneNumber("010-1234-5678")
                .role("USER")
                .build();

        // when
        when(userRepository.save(saveParams)).thenReturn(saveParams);
        User savedUser = userService.saveUser(saveParams);

        // then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNull(); // id 는 null , 실제 db에 저장 X
        assertThat(saveParams.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("유저 조회(이메일) 테스트")
    public void findUserByEmail() {
        // when
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
        User foundUser = userService.findUserByEmail(email).orElse(null);

        //then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("유저 조회(ID) 테스트")
    public void findUserById() {
        //when
        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        User foundUser = userService.findUserById(id).orElse(null);

        // then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(id);
        assertThat(foundUser.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("유저 정보 수정 테스트")
    public void updateUser() {
        // given
        User updatedUserInfo = User.builder()
                .name("update")
                .email("updateTest@naver.com")
                .password("update")
                .phoneNumber("010-5678-1234")
                .role("USER")
                .build();

        // when
        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));

        existingUser.setName(updatedUserInfo.getName());
        existingUser.setEmail(updatedUserInfo.getEmail());
        existingUser.setPassword(updatedUserInfo.getPassword());
        existingUser.setPhoneNumber(updatedUserInfo.getPhoneNumber());
        existingUser.setRole(updatedUserInfo.getRole());

        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User updatedUser = userService.updateUser(id, updatedUserInfo);

        // then
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(id);
        assertThat(updatedUser.getName()).isEqualTo("update");
        assertThat(updatedUser.getEmail()).isEqualTo("updateTest@naver.com");
        assertThat(updatedUser.getPassword()).isEqualTo("update");
        assertThat(updatedUser.getPhoneNumber()).isEqualTo("010-5678-1234");
        assertThat(updatedUser.getRole()).isEqualTo("USER");

    }




}
