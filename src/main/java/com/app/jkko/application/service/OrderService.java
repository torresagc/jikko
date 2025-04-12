package com.app.jkko.application.service;

import com.app.jkko.application.dto.OrderRequestDTO;
import com.app.jkko.application.dto.OrderResponseDTO;
import com.app.jkko.application.mapper.OrderMapper;
import com.app.jkko.domain.model.Order;
import com.app.jkko.domain.repository.OrderRepository;
import com.app.jkko.infraestructure.exception.ErrorMessage;
import com.app.jkko.infraestructure.exception.JIKKOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderResponseDTO order(OrderRequestDTO orderDTO) {
        validateRequest(orderDTO);
        double subtotal = orderDTO.getProducts().stream()
                .mapToDouble(p -> p.getAmount() * p.getQuantity())
                .sum();

        Order order = OrderMapper.toDto(orderDTO, subtotal, shipping(orderDTO.getLevel()), discount(subtotal));

        orderRepository.save(order);

        return OrderMapper.toEntity(order);
    }

    private void validateRequest(OrderRequestDTO orderDTO){
        boolean hasNoProducts = orderDTO.getProducts() == null || orderDTO.getProducts().isEmpty();
        boolean levelMissing = orderDTO.getLevel() == null;

        if (hasNoProducts || levelMissing) {
            throw new JIKKOException(ErrorMessage.FIELD_EMPTY, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *
     * podria mejorar generando una tabla en BD de configuracion y cargando en cache
     */
    public Double shipping(Integer level) {
        return switch (level) {
            case 1, 2 -> 2000D;
            case 3, 4 -> 5000D;
            case 5, 6 -> 8000D;
            default -> 0D;
        };
    }

    /**
     *
     * podria mejorar generando una tabla en BD de configuracion y cargando en cache
     */
    public Double discount(Double total) {
        if (total >= 300000) return 0.15D;
        if (total >= 150000) return 0.10D;
        return 0.0D;
    }
}
