package com.app.jkko.infraestructure.controller;

import com.app.jkko.application.dto.OrderRequestDTO;
import com.app.jkko.application.dto.OrderResponseDTO;
import com.app.jkko.application.dto.ProductDTO;
import com.app.jkko.application.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class OrderControllerTest {
    @Mock
    private OrderService orderService;
    @InjectMocks
    private OrderController orderController;

    @Test
    public void createOrder(){
        ProductDTO product1 = new ProductDTO("Zapatos", 2, 100000D);
        ProductDTO product2 = new ProductDTO("Chaqueta", 1, 60000D);
        OrderRequestDTO request = new OrderRequestDTO(3, List.of(product1, product2));

        Mockito.when(orderService.order(Mockito.any())).thenReturn(new OrderResponseDTO());
        ResponseEntity<OrderResponseDTO> response = orderController.create(request);
        Assertions.assertNotNull(response);
    }
}
