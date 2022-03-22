package com.squareshift.ecommerce.shoppingcart.spring;

import com.squareshift.ecommerce.shoppingcart.config.ProductServiceRestProperties;
import com.squareshift.ecommerce.shoppingcart.config.WarehouseServiceRestProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Data
@Configuration
public class ConfigurationBeans {

    private final ProductServiceRestProperties productServiceRestProperties;
    private final WarehouseServiceRestProperties warehouseServiceRestProperties;

    @Bean
    @Qualifier("ProductServiceRestTemplate")
    public RestTemplate productServiceRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(productServiceRestProperties.getDomain())
                .build();
    }

    @Bean
    @Qualifier("WarehouseServiceRestTemplate")
    public RestTemplate warehouseServiceRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(warehouseServiceRestProperties.getDomain())
                .build();
    }


}
