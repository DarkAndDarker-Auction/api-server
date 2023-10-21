package com.darkanddarker.auction.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken {
    @Id
    private String email;

    private String refreshToken;

    public RefreshToken updateValue(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
