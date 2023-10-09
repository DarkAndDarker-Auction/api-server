package com.darkanddarker.auction.common.config;

import com.darkanddarker.auction.common.jwt.JwtFilter;
import com.darkanddarker.auction.common.jwt.TokenBlacklist;
import com.darkanddarker.auction.common.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtFilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;
    private final TokenBlacklist tokenBlacklist;

    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider, tokenBlacklist);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
