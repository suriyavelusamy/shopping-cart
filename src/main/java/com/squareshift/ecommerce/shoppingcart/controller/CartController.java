package com.squareshift.ecommerce.shoppingcart.controller;

import com.squareshift.ecommerce.shoppingcart.client.ProductClient;
import com.squareshift.ecommerce.shoppingcart.model.dto.CartQueryResultDto;
import com.squareshift.ecommerce.shoppingcart.model.dao.Item;
import com.squareshift.ecommerce.shoppingcart.model.dto.ItemDto;
import com.squareshift.ecommerce.shoppingcart.model.dto.ProductDetailResponseDto;
import com.squareshift.ecommerce.shoppingcart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductClient productClient;


    @GetMapping("/items")
    public List<Item> getItem(){
        return cartService.getItems();
    }

    @PostMapping("/item")
    public ResponseEntity<CartQueryResultDto> createItem(@RequestBody ItemDto itemDto){
        ProductDetailResponseDto productDetailResponseDto = productClient.getProductDetails(itemDto.getProductId());
        if(productDetailResponseDto.getStatus().equals("ERROR")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildResponse("ERROR", productDetailResponseDto.getMessage()));
        }

        try{
            cartService.createItem(itemDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(buildResponse("SUCCESS", "Item has been added to cart"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildResponse("ERROR", e.getMessage()));
        }
    }

    @GetMapping("/checkout-value")
    public ResponseEntity<CartQueryResultDto> getTotalCartValue(@RequestParam("shipping_postal_code") Long postalCode){
        try{
            Double cartValue = cartService.getTotalCartValue(postalCode);
            double cartValueRounded = (double) Math.round(cartValue * 100) / 100;
            return ResponseEntity.status(HttpStatus.OK).body(buildResponse("SUCCESS", "Total value of your shopping cart is : $"+cartValueRounded));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildResponse("ERROR", e.getMessage()));
        }
    }

    @DeleteMapping("/items")
    public ResponseEntity<CartQueryResultDto> deleteAllItems(){
        try{
            cartService.deleteAllItems();
            return ResponseEntity.status(HttpStatus.OK).body(buildResponse("SUCCESS", "All items have been removed from the cart !"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildResponse("ERROR", e.getMessage()));
        }
    }

    private CartQueryResultDto buildResponse(String status, String message){
       return CartQueryResultDto.builder().status(status).message(message).build();
    }

}
