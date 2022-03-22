package com.squareshift.ecommerce.shoppingcart.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartQueryResultDto {
    private String status;
    private String message;
}
