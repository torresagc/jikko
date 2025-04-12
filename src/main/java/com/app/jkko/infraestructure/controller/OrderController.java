package com.app.jkko.infraestructure.controller;

import com.app.jkko.application.dto.OrderRequestDTO;
import com.app.jkko.application.dto.OrderResponseDTO;
import com.app.jkko.application.service.OrderService;
import com.app.jkko.infraestructure.util.Route;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Orders", description = "")
@RequestMapping(Route.API)
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(Route.Order.CREATE)
    public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderRequestDTO request) {
        return new ResponseEntity<>(orderService.order(request), HttpStatus.CREATED);
    }
}
