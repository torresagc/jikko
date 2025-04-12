package com.app.jkko.application.service;

import com.app.jkko.application.dto.OrderRequestDTO;
import com.app.jkko.application.dto.OrderResponseDTO;
import com.app.jkko.application.dto.ProductDTO;
import com.app.jkko.domain.model.Order;
import com.app.jkko.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.util.List;

@SpringBootTest
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @Test
    void orderShouldCalculateCorrectValuesSaveToDatabase() {

        ProductDTO product1 = new ProductDTO("Zapatos", 2, 100000D);
        ProductDTO product2 = new ProductDTO("Chaqueta", 1, 60000D);
        OrderRequestDTO requestDTO = new OrderRequestDTO(3, List.of(product1, product2));

        Mockito.when(orderRepository.save(Mockito.any())).thenReturn(new Order());

        OrderResponseDTO response = orderService.order(requestDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(260000D, response.getSubtotal());
        Assertions.assertEquals(26500D, response.getDiscount());
        Assertions.assertEquals(5000D, response.getShipping());
    }


    @Test
    void discountReturnsOk() {
        Assertions.assertEquals(0.15D, orderService.discount(300000D));
        Assertions.assertEquals(0.15D, orderService.discount(500000D));
        Assertions.assertEquals(0.10D, orderService.discount(150000D));
        Assertions.assertEquals(0.10D, orderService.discount(200000D));
        Assertions.assertEquals(0.10D, orderService.discount(299999D));
        Assertions.assertEquals(0.0D, orderService.discount(149999D));
    }

    @Test
    void shippingReturnsOk() {
        Assertions.assertEquals(2000D, orderService.shipping(1));
        Assertions.assertEquals(2000D, orderService.shipping(2));
        Assertions.assertEquals(5000D, orderService.shipping(3));
        Assertions.assertEquals(5000D, orderService.shipping(4));
        Assertions.assertEquals(8000D, orderService.shipping(5));
        Assertions.assertEquals(8000D, orderService.shipping(6));
    }

}
