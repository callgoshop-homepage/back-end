package org.example.member.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
        @NotNull(message = "비밀번호는 필수.")
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
}
