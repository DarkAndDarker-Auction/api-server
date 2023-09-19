package com.darkanddarker.auction.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id
    Long id;

    private String email;
    private String password;


    @Enumerated(EnumType.STRING)
    private Authority authority;

    public String authorityToString(){
        return this.authority.toString();
    }

}
