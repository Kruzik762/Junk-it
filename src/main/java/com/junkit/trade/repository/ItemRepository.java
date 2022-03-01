package com.junkit.trade.repository;

import com.junkit.trade.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository <Item, Long> {

    public List<Item> findAllByUserUserId(Long userId);
}
