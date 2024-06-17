package org.example.token.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.global.jwt.JwtProvider;
import org.example.global.jwt.JwtUtil;
import org.example.global.rs.RsData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/token", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class JwtController {
    private final JwtProvider jwtProvider;
    private final JwtUtil jwtUtil;

    @AllArgsConstructor
    @Getter
    public class newAccessRequest {
        private String newAccessToken;
    }

    @PostMapping(value = "/access-token", consumes = APPLICATION_JSON_VALUE)
    public RsData<newAccessRequest> refresh(HttpServletRequest request, HttpServletResponse resp) {
        String token = extractTokenFromHeader(request);

        String username = jwtProvider.getUsername(token);
        String newAccessToken = jwtUtil.genAccessToken(username);

        if (newAccessToken == null) {
            return RsData.of("Invalid refresh request", null);
        }
        // 새로운 토큰을 응답 헤더에 추가
        resp.addHeader("Authorization", "Bearer " + newAccessToken);

        return RsData.of("S-11", "새로운 Access 토큰이 발급되었습니다.", new newAccessRequest(newAccessToken));
    }

    // 토큰을 통해 유저 아이디를 가져오는 구문
    @PostMapping(value = "/verify-token", consumes = APPLICATION_JSON_VALUE)
    public String verifyToken(HttpServletRequest request) {
        String token = extractTokenFromHeader(request);
        String username = jwtProvider.getUsername(token);
        return username;
    }

    public String extractTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer " 다음의 문자열이 토큰이므로 잘라냄
        }
        return null; // 유효한 토큰이 없는 경우
    }
}
