package com.darkanddarker.auction.repository.searchKey;

import com.darkanddarker.auction.model.searchKey.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByOrderByName();
}
