package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    private String grantType; // jwt 인증 타입
    private String accessToken;
    private String refreshToken;
}
