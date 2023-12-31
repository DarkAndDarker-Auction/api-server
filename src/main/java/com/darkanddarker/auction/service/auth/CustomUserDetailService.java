package com.darkanddarker.auction.service.auth;

import com.darkanddarker.auction.common.exception.BadRequestException;
import com.darkanddarker.auction.common.exception.NotFoundException;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.authorityToString());
        return new User(
                member.getEmail(),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()){
            return member.map(this::createUserDetails)
                    .orElseThrow(() -> new NotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
        }
        throw new BadRequestException("로그인 정보가 올바르지 않습니다.");
    }
}
