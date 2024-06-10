package com.hun.market.item.repository;

import com.hun.market.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    Optional<Item> findById(Long id);

    void save(Item item);

    @Query("SELECT i FROM Item i WHERE i.id IN :ids")
    List<Item> findAllById(@Param("ids") List<Long> ids);
}
