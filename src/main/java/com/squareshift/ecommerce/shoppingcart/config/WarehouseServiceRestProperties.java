package com.squareshift.ecommerce.shoppingcart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "service.warehouse.rest")
public class WarehouseServiceRestProperties {
    private String domain;
    private String postalDistanceGetUrl;
}
