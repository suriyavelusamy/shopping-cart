package com.squareshift.ecommerce.shoppingcart.model.dto;

import com.squareshift.ecommerce.shoppingcart.model.dao.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    @NotEmpty(message = "Product Id must not be empty")
    private Long productId;
    @NotEmpty(message = "Product Description must not be empty")
    private String productDesc;
    @NotEmpty(message = "Quantity must not be empty")
    private Integer quantity;

    public Item toItem(){
        Item item = new Item();
        item.setProductId(this.productId);
        item.setProductDesc(this.productDesc);
        item.setQuantity(this.quantity);
        return item;
    }
}
