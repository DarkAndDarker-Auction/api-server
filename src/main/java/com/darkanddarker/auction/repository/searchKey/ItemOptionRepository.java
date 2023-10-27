package com.darkanddarker.auction.repository.searchKey;

import com.darkanddarker.auction.model.searchKey.ItemOption;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOptionRepository extends JpaRepository<ItemOption, Long> {
}
