package com.incetutku.ordermanagementsystem.order;

import com.incetutku.ordermanagementsystem.order.dto.CompleteOrder;
import com.incetutku.ordermanagementsystem.order.dto.CompleteOrderResponse;
import com.incetutku.ordermanagementsystem.order.dto.OrderDto;
import com.incetutku.ordermanagementsystem.order.dto.OrderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/complete")
    public ResponseEntity<CompleteOrderResponse> completeOrder(@RequestBody CompleteOrder completeOrder) {
        return ResponseEntity.ok(orderService.completePayment(completeOrder));
    }
}
