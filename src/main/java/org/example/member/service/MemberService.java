package org.example.member.service;

import lombok.RequiredArgsConstructor;
import org.example.member.dto.MemberDto;
import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //    Id로 유저 조회
    public Optional<Member> findById(Long id) {
        return this.memberRepository.findById(id);
    }

    //    유저 아이디를 통해 member를 찾는 구문 (Dto값을 반환)
    public Optional<Member> findByUsername(String username) {
        Member member = memberRepository.findByUsername(username).orElse(null);
        return Optional.ofNullable(member);
    }

    //    유저를 확인하는 구문 (entity값을 반환)
    public boolean memberCheck(String username, String password) {
        Optional<Member> member = memberRepository.findByUsername(username);
        Member checkMember = member.orElse(null);

        if (checkMember != null) {
            return passwordEncoder.matches(password, checkMember.getPassword());
        } else {
            return false;
        }
    }

    //    승인 요청됐는지 확인하는 구문
    public Member booleanMember(String username) {
        Member member = memberRepository.findByUsername(username).orElse(null);

        return member;
    }

    //    회원가입 하는 구문
    public Member joinMember(String email, String username, String password, String name, String phoneNumber) {
        Member member = Member.builder()
                .email(email)
                .username(username)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .build();

        this.memberRepository.save(member);

        return member;
    }

//    회원 수정하는 구문
    public Member modify (String username, String name, String phoneNumber, String password) {
        Optional<Member> member1 = memberRepository.findByUsername(username);
        Member member = member1.orElse(null);

        if (password == null || password.isEmpty()) {
            member.setName(name);
            member.setPhoneNumber(phoneNumber);
        } else {
            member.setName(name);
            member.setPhoneNumber(phoneNumber);
            member.setPassword(passwordEncoder.encode(password));
        }

        memberRepository.save(member);
        return member;
    }

    //    유저 리스트를 불러오는 구문
    public List<Member> memberList() {
        return memberRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    //    아이디, 비밀번호 찾기 구문
    public Member findByNameAndEmail(String name, String email) {
        Optional<Member> username = memberRepository.findByNameAndEmail(name, email);

        return username.orElse(null);
    }

    public Member findByNameAndEmailAndUsername(String name, String email, String username) {
        Optional<Member> result = memberRepository.findByNameAndEmailAndUsername(name, email, username);
        return result.orElse(null);
    }

    public void PasswordModify (Member member, String password) {
        Member memberModify = member;
        
        memberModify.setPassword(passwordEncoder.encode(password));

        memberRepository.save(memberModify);
    }
}