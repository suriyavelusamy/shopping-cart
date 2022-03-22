package com.squareshift.ecommerce.shoppingcart.repository;

import com.squareshift.ecommerce.shoppingcart.model.dao.ShippingWeightAndDistanceMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingWeightAndDistanceMappingRepository  extends JpaRepository<ShippingWeightAndDistanceMapping, Long> {


    @Query("select map from ShippingWeightAndDistanceMapping map " +
            "where map.minDistance <= :distance and map.maxDistance > :distance " +
            "and map.minWeight <= :weight and map.maxWeight > :weight ")
    ShippingWeightAndDistanceMapping fetchShippingWeightAndDistanceMapping(Double distance,Double weight);
}
