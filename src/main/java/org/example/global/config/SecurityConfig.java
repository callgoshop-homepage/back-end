package org.example.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 아래의 모든 설정은 /api/** 경로에만 적용
                .authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers("/**").permitAll() // 로그인은 누구나 가능
                                .anyRequest().authenticated() // 나머지는 인증된 사용자만 가능
                        // 나머지는 인증된 사용자만 가능

                )
                .cors(cors -> cors.configure(http)) // 타 도메인에서 API 호출 가능
                .csrf(csrf -> csrf.disable()) // CSRF 토큰 끄기
                .httpBasic(httpBasic -> httpBasic.disable()) // httpBaic 로그인 방식 끄기
                .formLogin(formLogin -> formLogin.disable()) // 폼 로그인 방식 끄기
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(STATELESS)
                )
      ;

        return http.build();
    }

//    시큐리티 인증
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}