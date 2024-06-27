package org.example.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.email.service.EmailService;
import org.example.global.jwt.JwtProvider;
import org.example.global.jwt.JwtUtil;
import org.example.global.rq.Rq;
import org.example.global.rs.RsData;
import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;
import org.example.token.controller.JwtController;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.TokenCollector;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.ALL_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/member", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class MemberController {

    private final JwtController jwtController;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

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
        @Pattern(regexp = "(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$")
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
        @NotNull(message = "비밀번호는 필수입니다.")
        public String password;
    }

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    public RsData<LoginResponse> Login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse resp) {
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

            return RsData.of("S-2", "로그인이 완료되었습니다.", new LoginResponse(accessToken, refreshToken, booleanMember));
        } else if (checkedLogin && booleanMember == 0) {
            return RsData.of("S-43", "승인처리 진행중", null);
        } else {
            return RsData.of("S-44", "로그인 실패", null);
        }
    }

    //    내 정보 불러오는 구문
    @AllArgsConstructor
    @Getter
    public static class MyPageResponse {
        private final Member member;
    }

    @GetMapping(value = "/my-page", consumes = ALL_VALUE)
    public RsData<MyPageResponse> mypage(HttpServletRequest request) {
        String token = jwtController.extractTokenFromHeader(request);
        String username = jwtProvider.getUsername(token);

        if (username == null) {
            // 사용자가 인증되지 않은 경우 처리
            return RsData.of("S-41", "사용자가 인증되지 않았습니다.", null);
        }

        Member member = memberService.findByUsername(username).orElse(null);

        return RsData.of(
                "S-5",
                "내 정보 조회성공",
                new MyPageResponse(member)
        );
    }

    //    내 정보 수정하는 구문
    @AllArgsConstructor
    @Getter
    public static class ModifyResponse {
        private final Member member;
        private final String newAccessToken;
        private final String newRefreshToken;
    }

    @Data
    public static class ModifyRequest {
        private String name;
        private String username;
        private String password;
        private String phoneNumber;
    }

    @PostMapping(value = "/modify", consumes = ALL_VALUE)
    public RsData<ModifyResponse> modify(@Valid @RequestBody ModifyRequest modifyRequest, HttpServletResponse resp, HttpServletRequest request) {
        String token = jwtController.extractTokenFromHeader(request);
        String username = jwtProvider.getUsername(token);
        Member member = memberService.modify(username, modifyRequest.getName(), modifyRequest.getPhoneNumber(), modifyRequest.getPassword());

        String accessToken = jwtUtil.genAccessToken(username);
        String refreshToken = jwtUtil.genRefreshToken(username);

        resp.addHeader("Authorization", "Bearer " + accessToken);
        resp.addHeader("Refresh-Token", refreshToken);

        return RsData.of("S-3", "성공", new ModifyResponse(member, accessToken, refreshToken));
    }

    //    회원 리스트를 불러오는 구문
    @AllArgsConstructor
    @Getter
    public static class MemberListResponse {
        private List<Member> memberList;
    }

    @GetMapping(value = "/list", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public RsData<MemberListResponse> franchiseList() {
        List<Member> memberList = memberService.memberList();

        return RsData.of("S-4", "리스트 조회 성공", new MemberListResponse(memberList));
    }

    //    회원 승인처리하는 구문
    @Data
    public static class MemberApproveRequest {
        private String username;
    }

    @PutMapping(value = "/approve", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public RsData<?> memberApprove(@Valid @RequestBody MemberApproveRequest memberApproveRequest) {

        Optional<Member> member = this.memberService.findByUsername(memberApproveRequest.getUsername());
        Member member1 = member.orElse(null);

        if (member1 != null && member1.getIsEnabled() == 0) {
            member1.setIsEnabled(1);

            memberRepository.save(member1);
        } else if (member1 != null && member1.getIsEnabled() == 1) {
            member1.setIsEnabled(0);

            memberRepository.save(member1);
        }

        return RsData.of("S-6", "승인 완료", null);
    }

    //    회원 아이디 찾는 구문
    @Data
    public static class FindIdRequest {
        private String name;
        private String email;
    }

    @AllArgsConstructor
    @Getter
    public static class FindIdResponse {
        private final String username;
    }

    @PostMapping(value = "/findId", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public RsData<FindIdResponse> findUsername(@Valid @RequestBody FindIdRequest findIdRequest) {
        Member member = memberService.findByNameAndEmail(findIdRequest.getName(), findIdRequest.getEmail());

        if (member.getUsername() != null) {
            return RsData.of("S-7", "아이디를 찾았습니다.", new FindIdResponse(member.getUsername()));
        } else {
            return RsData.of("S-42", "해당 회원이 존재하지 않습니다.", null);
        }
    }

    //    회원 비밀번호 찾는 구문
    @Data
    public static class FindPasswordRequest {
        private String name;
        private String email;
        private String username;
    }

    @PostMapping(value = "/findPassword", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public RsData<?> findPassword(@Valid @RequestBody FindPasswordRequest findPasswordRequest) {
        Member member = memberService.findByNameAndEmailAndUsername(findPasswordRequest.getName(), findPasswordRequest.getEmail(), findPasswordRequest.getUsername());

        if (member != null) {
            String password = emailService.PWSearch(findPasswordRequest.getEmail());
            memberService.PasswordModify(member, password);
            return RsData.of("S-8", "임시 비밀번호를 발송하였습니다.", null);
        } else {
            return RsData.of("S-45", "해당 회원이 존재하지 않습니다.", null);
        }
    }
}