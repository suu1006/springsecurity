package com.example.javamvc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    private String grantType; // jwt 인증 타입 Bearer 방식 사용 예정
    private String accessToken;
    private String refreshToken;
}
