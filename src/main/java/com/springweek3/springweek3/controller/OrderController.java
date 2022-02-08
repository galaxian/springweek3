package com.springweek3.springweek3.controller;

import com.springweek3.springweek3.dto.OrderDto;
import com.springweek3.springweek3.dto.OrderRequestDto;
import com.springweek3.springweek3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order/request")
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok()
                .body(orderService.addOrder(orderRequestDto));
    }

    @GetMapping("/orders")
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }
}
