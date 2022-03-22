package com.squareshift.ecommerce.shoppingcart.model.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class ShippingWeightAndDistanceMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Double minDistance;

    @Column
    private Double maxDistance;

    @Column
    private Double minWeight;

    @Column
    private Double maxWeight;

    @Column
    private Double price;

}
