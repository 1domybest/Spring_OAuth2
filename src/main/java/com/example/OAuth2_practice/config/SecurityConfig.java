package com.example.OAuth2_practice.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration // 설정파일이다
@EnableWebSecurity // 시큐리티 파일이다
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors((cors) -> cors
                .configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();

                        // 허용할 Origin 설정
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173")); // React 클라이언트 URL

                        // 허용할 HTTP 메서드
                        configuration.setAllowedMethods(Collections.singletonList("*")); // 모든 HTTP 메서드 허용

                        // 인증 정보 포함 허용
                        configuration.setAllowCredentials(true);

                        // 허용할 요청 헤더
                        configuration.setAllowedHeaders(Collections.singletonList("*")); // 모든 요청 헤더 허용

                        // 프론트 쪽에 노출할 응답 헤더
                        configuration.setExposedHeaders(Collections.singletonList("Authorization")); // Authorization 헤더 노출

                        // CORS 검사 결과 캐싱 시간 설정
                        configuration.setMaxAge(3600L); // 1시간(3600초)

                        return configuration; // 올바른 CORS 설정 반환
                    }
                })
        );

        http
                .csrf((csrf) -> csrf.disable());

        http
                .formLogin((login) -> login.disable());

        http
                .httpBasic((basic) -> basic.disable());

        // Oauth2 등록
        // 나중에 이곳에 로그인 성공시에 들어갈 필터가 들어갈예정
        http
                .oauth2Login(Customizer.withDefaults());


        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/oauth2/**", "/login/**", "/snsLogin/**").permitAll() // Oauth2
                        .anyRequest().authenticated());

        return http.build();
    }

}
