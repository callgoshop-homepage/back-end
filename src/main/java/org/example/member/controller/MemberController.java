package org.example.member.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.global.jwt.JwtUtil;
import org.example.global.rq.Rq;
import org.example.global.rs.RsData;
import org.example.member.entity.Member;
import org.example.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/member", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;
    private final Rq rq;

    //    회원가입 하는 구문
    @Data
    public static class JoinMemberRequest {
        //        이메일
        @NotNull(message = "이메일은 필수.")
        public String email;
        //        아이디
        @NotNull(message = "아이디는 필수.")
        public String username;
        //        비밀번호
        @NotNull(message = "비밀번호는 6자리 이상 25자리 이하이며, 영어, 숫자, 특수문자를 포함해야 합니다.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?]).{6,25}$")
        public String password;
        //        이름
        public String name;
        //        핸드폰 번호
        @NotNull(message = "핸드폰 번호는 필수.")
        public String phoneNumber;
    }

    @AllArgsConstructor
    @Getter
    public static class JoinMemberResponse {
        private final Member member;
    }

    @PostMapping(value = "/join", consumes = APPLICATION_JSON_VALUE)
    public RsData<JoinMemberResponse> joinMember(@Valid @RequestBody JoinMemberRequest joinMemberRequest) {
        Member member = memberService.joinMember(joinMemberRequest.getEmail(), joinMemberRequest.getUsername(), joinMemberRequest.getPassword(), joinMemberRequest.getName(), joinMemberRequest.getPhoneNumber());

        return RsData.of("S-1", "회원 가입 성공", new JoinMemberResponse(member));
    }

    //    로그인 하는 구문
    @AllArgsConstructor
    @Getter
    public static class LoginResponse {
        private String accessToken;
        private String refreshToken;
        private int booleanMember;
    }

    @Data
    public static class LoginRequest {
        //        아이디
        @NotNull(message = "아이디는 필수입니다.")
        public String username;
        @NotNull(message = "비밀번호는 6자리 이상 25자리 이하이며, 영어, 숫자, 특수문자를 포함해야 합니다.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?]).{6,25}$")
        public String password;
    }

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    public RsData<LoginResponse> Login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse resp) throws Exception {
        boolean checkedLogin = memberService.memberCheck(loginRequest.getUsername(), loginRequest.getPassword());
        Member member = memberService.booleanMember(loginRequest.getUsername());
        int booleanMember = member.getIsEnabled();

        if (checkedLogin && booleanMember == 1) {
            String accessToken = jwtUtil.genAccessToken(loginRequest.getUsername());
            String refreshToken = jwtUtil.genRefreshToken(loginRequest.getUsername());

            resp.addHeader("Authorization", "Bearer " + accessToken);
            resp.addHeader("Refresh-Token", refreshToken);

            System.out.println(accessToken);
            System.out.println(refreshToken);

            return RsData.of("S-2", "토큰이 생성되었습니다.", new LoginResponse(accessToken, refreshToken, booleanMember));
        } else {
            return RsData.of("일치하지 않음", null);
        }
    }
}