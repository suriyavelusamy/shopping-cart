package com.squareshift.ecommerce.shoppingcart.service;

import com.squareshift.ecommerce.shoppingcart.client.ProductClient;
import com.squareshift.ecommerce.shoppingcart.client.WarehouseClient;
import com.squareshift.ecommerce.shoppingcart.model.dao.Item;
import com.squareshift.ecommerce.shoppingcart.model.dto.ItemDto;
import com.squareshift.ecommerce.shoppingcart.model.dto.Product;
import com.squareshift.ecommerce.shoppingcart.model.dto.ProductDetailResponseDto;
import com.squareshift.ecommerce.shoppingcart.repository.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ProductClient productClient;
    @Mock
    private WarehouseClient warehouseClient;
    @Mock
    private ShippingService shippingService;


    private CartService cartService;

    @Before
    public void setUp(){
        cartService = new CartService(itemRepository, productClient, warehouseClient, shippingService);
    }

    @Test
    public void shouldInvokeFindAllRepoMethod(){
        cartService.getItems();
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    public void shouldInvokeSaveRepoMethod(){
        ItemDto item1 = new ItemDto();
        item1.setProductId(123L);
        item1.setQuantity(2);
        cartService.createItem(item1);
        verify(itemRepository, times(1)).save(any());
    }


    @Test
    public void shouldApplyDiscountAndShippingPriceForCartValue(){
        when(itemRepository.findAll()).thenReturn(mockItems());
        when(warehouseClient.getPostalDistance(eq(638301L))).thenReturn(3.0);
        when(productClient.getProductDetails(any())).thenReturn(mockProductResponse());
        when(shippingService.getShippingPrice(any(), any())).thenReturn(10.0);
        Double actualTotalValue = cartService.getTotalCartValue(638301L);
        assertThat(actualTotalValue, is(589.9999995529652));
    }

    @Test
    public void shouldInvokeDeleteAllRepoMethod(){
        cartService.deleteAllItems();
        verify(itemRepository, times(1)).deleteAll();
    }

    private List<Item> mockItems(){
        List<Item> items = new ArrayList<>();
        Item item1 = new Item();
        item1.setId(1L);
        item1.setProductId(123L);
        item1.setQuantity(2);
        items.add(item1);

        Item item2 = new Item();
        item2.setId(1L);
        item2.setProductId(123L);
        item2.setQuantity(1);
        items.add(item2);
        return List.of(item1, item2);
    }

    private ProductDetailResponseDto mockProductResponse() {
        Product product = Product.builder().productId(1L).name("macbookpro model1").discountPercentage(5F).weightInGrams(300.0).price(200.0).build();
        return ProductDetailResponseDto.builder().product(product).status("SUCCESS").build();
    }

}
