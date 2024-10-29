package com.example.javamvc.entity;

import com.example.javamvc.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.javamvc.repository.UserRepository;
import com.example.javamvc.service.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MemberRepositoryTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("유저 저장 테스트")
    public void saveUser() {
        // given
        User user = new User();
        user.setEmail("test@naver.com");
        user.setPassword("1234");

        // when
        when(userRepository.save(user)).thenReturn(user);

        //then
        assertThat(userService.saveUser(user)).isEqualTo(user);
    }

    @Test
    @DisplayName("유저 조회 테스트")
    public void findUserByEmail() {
        // given
        User user = new User();
        user.setEmail("test@naver.com");
        user.setPassword("1234");

        // when
        when(userRepository.findByEmail("test@naver.com")).thenReturn(Optional.of(user));

        // then
        assertThat(userService.findUserByEmail("test@naver.com")).isEqualTo(user);

    }
}
