package com.darkanddarker.auction.model.searchKey;

import com.darkanddarker.auction.model.searchKey.*;

import javax.persistence.*;

@Entity
public class item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private HandType handType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private WeaponType weaponType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private ArmorType armorType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private SlotType slotType;

    @Column(nullable = false)
    private String name;

}
