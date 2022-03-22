package com.squareshift.ecommerce.shoppingcart.model.dao;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long productId;

    @Column
    private String productDesc;

    @Column
    private Integer quantity;


}
