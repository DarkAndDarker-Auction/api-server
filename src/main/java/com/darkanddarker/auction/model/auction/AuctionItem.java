package com.darkanddarker.auction.model.auction;

import com.darkanddarker.auction.model.VerificationEventType;
import com.darkanddarker.auction.model.member.Member;
import com.darkanddarker.auction.model.searchKey.Item;
import com.darkanddarker.auction.model.searchKey.Rarity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuctionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Member seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Rarity rarity;

    private LocalDateTime expirationTime;

    @Column(nullable = false)
    private boolean allowOffer;

    @Enumerated(EnumType.STRING)
    private AuctionStatusType auctionStatusType;

    @PrePersist
    private void setDefault() {
        this.expirationTime = LocalDateTime.now().plusHours(12);
        this.auctionStatusType = AuctionStatusType.ACTIVE;
    }

    // 아이템 다이내믹 옵션
    private Long action_speed;
    private Long additional_magical_damage;
    private Long additional_move_speed;
    private Long additional_physical_damage;
    private Long agility;
    private Long all_attribute;
    private Long armor_penetration;
    private Long armor_rating;
    private Long buff_duration_bonus;
    private Long debuff_duration_bonus;
    private Long headshot_damage_reduction;
    private Long item_equip_speed;
    private Long knowledge;
    private Long magical_penetration;
    private Long magic_resistance;
    private Long magical_healing;
    private Long magical_interaction_speed;
    private Long magical_power;
    private Long max_health;
    private Long move_speed_bonus;
    private Long physical_damage_bonus;
    private Long physical_damage_reduction;
    private Long physical_healing;
    private Long projectile_reduction;
    private Long regular_interaction_speed;
    private Long resourcefulness;
    private Long spell_capacity_bonus;
    private Long spell_casting_speed;
    private Long strength;
    private Long true_magical_damage;
    private Long true_physical_damage;
    private Long weapon_damage;
    private Long will;

    // 아이템 고정 옵션
    private Long strength_static;
    private Long agility_static;
    private Long will_static;
    private Long knowledge_static;
    private Long resourcefulness_static;
    private Long armor_rating_static;
    private Long weapon_damage_static;
    private Long move_speed_static;
    private Long magical_damage_static;
    private Long magic_weapon_damage_static;
    private Long max_health_static;

    // 아이템 가격
    private Long priceGold;
    private Long priceGoldIngot;
    private Long priceGoldenKey;
    private Long priceEventCurrency;

    public AuctionItem updateStatus(AuctionStatusType auctionStatusType) {
        this.auctionStatusType = auctionStatusType;
        return this;
    }
}
