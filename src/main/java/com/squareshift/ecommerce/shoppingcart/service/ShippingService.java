package com.squareshift.ecommerce.shoppingcart.service;

import com.squareshift.ecommerce.shoppingcart.model.dao.ShippingWeightAndDistanceMapping;
import com.squareshift.ecommerce.shoppingcart.repository.ShippingWeightAndDistanceMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingWeightAndDistanceMappingRepository shippingMapRepository;

    public Double getShippingPrice(Double totalDistance, Double totalWeight){
        ShippingWeightAndDistanceMapping mapping = shippingMapRepository.fetchShippingWeightAndDistanceMapping(
                 totalDistance,
                 totalWeight
         );

        return mapping.getPrice();
    }
}
