package com.darkanddarker.auction.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private LocalDateTime lastLogin;

    private LocalDateTime withdrawalAt;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public String authorityToString(){
        return this.authority.toString();
    }

    @Builder
    public Member(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.authority = Authority.ROLE_USER;
    }

}
