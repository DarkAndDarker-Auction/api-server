package com.darkanddarker.auction.dto;

import com.darkanddarker.auction.model.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    private String email;
    private String password;
    private String nickname;

    @Builder
    public SignupRequestDto(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(this.email)
                .nickname(this.nickname)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
