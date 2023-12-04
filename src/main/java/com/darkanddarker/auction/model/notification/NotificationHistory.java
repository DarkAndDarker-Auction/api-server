package com.darkanddarker.auction.model.notification;

import com.darkanddarker.auction.model.BaseEntity;
import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.member.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member receiver;

    @JsonProperty("receiverId")
    public Long getReceiverId() {
        return receiver.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private AuctionItem auctionItem;

    @JsonProperty("auctionItemId")
    public Long getAuctionItemId() {
        return auctionItem.getId();
    }

    private Boolean checked;
    @PrePersist
    private void setDefault() {
        this.checked = false;
    }

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private String title;
    private String body;

    public void notificationChecked() {
        this.checked = true;
    }
}
