package controller;

import entity.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.LoginService;
import service.UserService;

import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/api/login")
    public ResponseEntity<JwtToken> loginSuccess(@RequestBody Map<String, String> loginForm) {
        System.out.println("loginForm username:" + loginForm.get("username"));
        System.out.println("loginForm password:" + loginForm.get("password"));
        JwtToken token = loginService.login(loginForm.get("username"), loginForm.get("password"));
        ResponseEntity.ok(token);
        return ResponseEntity.ok(token);
    }





}
