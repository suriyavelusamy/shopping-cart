package com.squareshift.ecommerce.shoppingcart.repository;

import com.squareshift.ecommerce.shoppingcart.model.dao.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
