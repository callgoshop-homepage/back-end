package org.example.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.global.baseentity.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //    이름
    private String name;

    //    아이디
    @Column(unique = true)
    private String username;

    //    비밀번호
    private String password;

    //    이메일
    @Column(unique = true)
    private String email;

    //    전화번호
    @Column(unique = true)
    private String phoneNumber;

    //    회원가입 승인 구문
    private int isEnabled = 0;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("MEMBER"));

        if (isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("admin"));
        }
        return authorities;
    }

    public boolean isAdmin() {
        return "gysoft".equals(username);
    }
}
