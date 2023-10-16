package com.darkanddarker.auction.repository.auth;

import com.darkanddarker.auction.model.jwt.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    void deleteByEmail(String email);
    Optional<RefreshToken> findByEmail(String email);

}
