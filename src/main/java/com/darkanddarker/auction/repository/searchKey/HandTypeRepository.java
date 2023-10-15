package com.darkanddarker.auction.repository.searchKey;

import com.darkanddarker.auction.model.searchKey.ArmorType;
import com.darkanddarker.auction.model.searchKey.HandType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandTypeRepository extends JpaRepository<HandType, Long> {
}
