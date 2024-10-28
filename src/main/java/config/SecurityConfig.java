package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // 변경된 방식으로 CSRF 비활성화
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션을 사용하지 않음
//                .formLogin(form -> form.disable())  // 폼 기반 로그인 비활성화
//                .httpBasic(httpBasic -> httpBasic.disable())  // HTTP 기본 인증 비활성화
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/login").permitAll()  // "/api/login" 경로는 인증 없이 접근 허용
//                )
//                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // JWT 필터 추가
//        return http.build();
//    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http	.csrf(csrf -> csrf.disable())  // 변경된 방식으로 CSRF 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션을 사용하지 않음
                .formLogin(form -> form.disable())  // 폼 기반 로그인 비활성화
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/signup", "/", "/login").permitAll()
                        .anyRequest().authenticated())
                // 폼 로그인은 현재 사용하지 않음
//				.formLogin(formLogin -> formLogin
//						.loginPage("/login")
//						.defaultSuccessUrl("/home"))
                .logout((logout) -> logout // 로그아웃에 대한 정보
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
                .sessionManagement(session -> session // 세션 생성 및 사용여부에 대한 정책 설정
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
