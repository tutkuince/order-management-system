package com.incetutku.ordermanagementsystem.order;

import com.incetutku.ordermanagementsystem.order.dto.InventoryRequestDto;
import com.incetutku.ordermanagementsystem.order.dto.OrderDto;
import com.incetutku.ordermanagementsystem.order.dto.OrderResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest(mode = ApplicationModuleTest.BootstrapMode.DIRECT_DEPENDENCIES)
class OrderIntegrationTest {

    @Autowired
    OrderService orderService;

    @Test
    void verifyModule() {

    }

    @Test
    void createOrder() {
        List<InventoryRequestDto> requestDtoList = List.of(
                new InventoryRequestDto("ruller", 1),
                new InventoryRequestDto("pencil", 3)
        );

        OrderDto orderDto = new OrderDto("Test Man", "test@test.com", requestDtoList);

        OrderResponseDto order = orderService.createOrder(orderDto);

        assertThat(order.message()).isEqualTo("Order Currently Processed");
        assertThat(order.statusCode()).isEqualTo(102);

    }
}