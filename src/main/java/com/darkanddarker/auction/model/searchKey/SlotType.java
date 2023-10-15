package com.darkanddarker.auction.model.searchKey;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class SlotType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;
}
