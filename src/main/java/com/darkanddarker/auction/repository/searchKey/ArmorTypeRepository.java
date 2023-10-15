package com.darkanddarker.auction.repository.searchKey;

import com.darkanddarker.auction.model.searchKey.ArmorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ArmorTypeRepository extends JpaRepository<ArmorType, Long> {
}
