package com.darkanddarker.auction.model.notification;

import com.darkanddarker.auction.model.BaseEntity;
import com.darkanddarker.auction.model.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FCMToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Member member;

    private String token;

    public void updateToken(String token) {
        this.token = token;
    }
}
