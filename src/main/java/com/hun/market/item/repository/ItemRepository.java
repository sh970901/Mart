package com.hun.market.item.repository;

import com.hun.market.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    Optional<Item> findById(Long id);

    void save(Item item);
}
