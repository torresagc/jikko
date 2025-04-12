package com.app.jkko.application.mapper;

import com.app.jkko.application.dto.ProductDTO;
import com.app.jkko.domain.model.Product;

import java.util.UUID;

public class ProductMapper {

    public static Product toDto(ProductDTO productDTO){
        UUID productId = UUID.randomUUID();
        return Product.builder()
                .id(productId)
                .amount(productDTO.getAmount())
                .name(productDTO.getName())
                .quantity(productDTO.getQuantity())
                .build();
    }
}
