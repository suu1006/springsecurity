//package config;
//
//import entity.JwtToken;
//import lombok.Value;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import org.springframework.security.core.GrantedAuthority;
//import java.security.Key;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import java.security.Key;
//import java.util.Date;
//import org.springframework.stereotype.Component;
//
//
//@Component
//@Slf4j
//public class JwtTokenProvider {
//
//    private final Key key;
//
//    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public JWToken generateToken(Long userId) {
//        // 권한 가져오기
//
//        long now = (new Date()).getTime();
//
//        // Access Token 생성
//        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
//
//        String accessToken = Jwts.builder()
//                .setSubject(String.valueOf(userId))
////                .claim("auth", authorities)
//                .setExpiration(accessTokenExpiresIn)
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//
//        // Refresh Token 생성
//        String refreshToken = Jwts.builder()
//                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//
//        return JWToken.builder()
//                .grantType(GRANT_TYPE)
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
//}
