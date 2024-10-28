package config;

import entity.JwtToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import java.security.Key;
import java.security.Key;
import java.security.SignatureException;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtTokenProvider {
    private final Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) { // 비밀키
        log.info("secretKey : {}", secretKey);
        byte[] secretByteKey = Base64.getDecoder().decode(secretKey); // 디코딩해서 byte 배열로 변환
        this.key = Keys.hmacShaKeyFor(secretByteKey); // Jwt 서명 시 사용
    }

    // Access Token, Refresh Token 생성
    public JwtToken generateToken(Authentication authentication) { // 토큰 생성
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 30)) // 30분
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        //Refresh Token 생성 - 36시간동안 유효
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 36))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // access token 복호화
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities); // 주체와 권한 정보를 포함한 인증 객체 생성
    }

    // 토큰 정보 검증 - 유효성 확인
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); // jwt토큰의 검증과 파싱을 모두 수행
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e); // 토큰 서명 잘못됨.
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e); // 토큰 만료
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);  // 지원하지 않는 JWT 토큰
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e); // 토큰이 비어있거나 잘못된 형식
        }
        return false;
    }

    // accessToken
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
