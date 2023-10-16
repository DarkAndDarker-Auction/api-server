package com.darkanddarker.auction.repository.searchKey;

import com.darkanddarker.auction.model.searchKey.ArmorType;
import com.darkanddarker.auction.model.searchKey.SlotType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotTypeRepository extends JpaRepository<SlotType, Long> {
}
