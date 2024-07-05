package org.example.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.member.repository.MemberRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final MemberRepository memberRepository;
    private final JavaMailSender mailSender;
    private static final String senderEmail = "gusrudchl12@gmail.com";
    private static int number;

    public String PWSearch(String email) {
        MimeMessage message = mailSender.createMimeMessage();

        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("CallGo 이메일 인증");
            String body = "";
            body += "<h1>" + "안녕하세요 CallGo 입니다." + "</h3>";
            body += "<h2>" + "아래 비밀번호로 로그인해주세요." + "</h2>";
            body += "<h1>" + str + "</h1>";
            message.setText(body, "UTF-8", "html");
            mailSender.send(message);
            return str;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
