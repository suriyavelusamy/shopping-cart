package com.squareshift.ecommerce.shoppingcart.service;

import com.squareshift.ecommerce.shoppingcart.client.ProductClient;
import com.squareshift.ecommerce.shoppingcart.client.WarehouseClient;
import com.squareshift.ecommerce.shoppingcart.model.dao.Item;
import com.squareshift.ecommerce.shoppingcart.model.dto.ItemDto;
import com.squareshift.ecommerce.shoppingcart.model.dto.Product;
import com.squareshift.ecommerce.shoppingcart.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ItemRepository itemRepository;
    private final ProductClient productClient;
    private final WarehouseClient warehouseClient;
    private final ShippingService shippingService;

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public void createItem(ItemDto itemDto) {
        itemRepository.save(itemDto.toItem());
    }

    public Double getTotalCartValue(Long shippingPostalCode) {
        List<Item> items = itemRepository.findAll();
        Double totalCartPrice = 0.0;
        Double distanceInKM = warehouseClient.getPostalDistance(shippingPostalCode);
        for (Item item : items) {
            totalCartPrice = getItemPrice(distanceInKM, totalCartPrice, item);
        }

        return totalCartPrice;
    }

    private Double getItemPrice(Double distanceInKM, Double totalCartPrice, Item item) {
        Product product = productClient.getProductDetails(item.getProductId()).getProduct();
        Double priceBeforeDiscount =  product.getPrice() * item.getQuantity();
        Double priceAfterDiscount = priceBeforeDiscount - (priceBeforeDiscount * (product.getDiscountPercentage() / 100));
        Double totalWeightInKg = (product.getWeightInGrams() / 1000) * item.getQuantity();
        Double shippingPrice = shippingService.getShippingPrice(distanceInKM, totalWeightInKg);
        totalCartPrice += priceAfterDiscount + shippingPrice;
        return totalCartPrice;
    }

    public void deleteAllItems() {
        itemRepository.deleteAll();
    }
}
