package com.squareshift.ecommerce.shoppingcart.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "service.product.rest")
public class ProductServiceRestProperties {
    private String domain;
    private String productDetailsGetUrl;
}
