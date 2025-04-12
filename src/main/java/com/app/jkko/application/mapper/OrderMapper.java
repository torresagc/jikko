package com.app.jkko.application.mapper;

import com.app.jkko.application.dto.OrderRequestDTO;
import com.app.jkko.application.dto.OrderResponseDTO;
import com.app.jkko.domain.model.Order;

import java.util.UUID;
import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toDto(OrderRequestDTO orderDTO,Double subtotal, Double shipping, Double discountPercentage){
        UUID orderId = UUID.randomUUID();

        Double totalShipping = subtotal + shipping;
        Double discount = totalShipping * discountPercentage;
        Double total = totalShipping - discount;

        return Order.builder()
                .id(orderId)
                .level(orderDTO.getLevel())
                .subtotal(subtotal)
                .shipping(shipping)
                .discount(discount)
                .total(total)
                .products(orderDTO.getProducts().stream().map(ProductMapper::toDto
                ).collect(Collectors.toList()))
                .build();
    }

    public static OrderResponseDTO toEntity(Order order){
        return OrderResponseDTO.builder()
                .discount(order.getDiscount())
                .shipping(order.getShipping())
                .subtotal(order.getSubtotal())
                .total(order.getTotal())
                .build();
    }
}
