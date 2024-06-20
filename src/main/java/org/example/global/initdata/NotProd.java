//package org.example.global.initdata;
//
//import org.example.member.entity.Member;
//import org.example.member.repository.MemberRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.time.LocalDateTime;
//
//@Configuration
//@Profile({"prod", "test"})
//public class NotProd {
//    @Bean
//    CommandLineRunner initData(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            Member admin = Member.builder()
//                    .name("관리자")
//                    .username("gysoft")
//                    .email("gysoft@gmail.com")
//                    .password(passwordEncoder.encode("gysoft"))
//                    .phoneNumber("01012345678")
//                    .isEnabled(1)
//                    .createDate(LocalDateTime.now())
//                    .build();
//
//            Member admin2 = Member.builder()
//                    .name("홍길동")
//                    .username("test1")
//                    .email("gusrudchl12@gmail.com")
//                    .password(passwordEncoder.encode("test1"))
//                    .phoneNumber("01012345679")
//                    .isEnabled(1)
//                    .createDate(LocalDateTime.now())
//                    .build();
//
//            memberRepository.save(admin);
//            memberRepository.save(admin2);
//        };
//    }
//}
