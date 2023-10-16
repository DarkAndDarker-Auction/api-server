package com.darkanddarker.auction.repository.searchKey;

import com.darkanddarker.auction.model.searchKey.ArmorType;
import com.darkanddarker.auction.model.searchKey.WeaponType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponTypeRepository extends JpaRepository<WeaponType, Long> {
}
