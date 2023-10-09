package com.darkanddarker.auction.service;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.dto.SignupRequestDto;
import com.darkanddarker.auction.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        if (memberRepository.existsByEmail(signupRequestDto.getEmail())){
            throw new BadRequestException("이미 존재하는 이메일 입니다.");
        }
        memberRepository.save(signupRequestDto.toMember(passwordEncoder));
    }

}
