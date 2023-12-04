package com.darkanddarker.auction.repository.chat;

import com.darkanddarker.auction.model.auction.AuctionItem;
import com.darkanddarker.auction.model.chat.ChatRoom;
import com.darkanddarker.auction.model.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("SELECT COUNT(c) > 0 FROM ChatRoom c WHERE c.buyer = :member OR c.seller = :member")
    boolean existsByMember(Member member);
    ChatRoom findByAuctionItem(AuctionItem auctionItem);

}
