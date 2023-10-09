package com.darkanddarker.auction.common.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenBlacklist {
    private static final String BLACKLIST_PREFIX = "blacklist:";
    private static final Long EXPIRATION_TIME = 30L;

    private final RedisTemplate<String, String> redisTemplate;

    public void blacklistAccessToken(String accessToken) {
        String key = BLACKLIST_PREFIX + accessToken;
        redisTemplate.opsForValue().set(key, accessToken, EXPIRATION_TIME, TimeUnit.MINUTES);
    }

    public boolean isAccessTokenBlacklisted(String accessToken) {
        String key = BLACKLIST_PREFIX + accessToken;
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

}
