package com.incetutku.ordermanagementsystem.order;

import com.incetutku.ordermanagementsystem.inventory.exposed.InventoryDto;
import com.incetutku.ordermanagementsystem.inventory.exposed.InventoryService;
import com.incetutku.ordermanagementsystem.order.dto.InventoryRequestDto;
import com.incetutku.ordermanagementsystem.order.dto.OrderDto;
import com.incetutku.ordermanagementsystem.order.dto.OrderResponseDto;
import com.incetutku.ordermanagementsystem.order.type.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class OrderService {

    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;
    private final OrderInventoryRepository orderInventoryRepository;

    public OrderService(InventoryService inventoryService, OrderRepository orderRepository, OrderInventoryRepository orderInventoryRepository) {
        this.inventoryService = inventoryService;
        this.orderRepository = orderRepository;
        this.orderInventoryRepository = orderInventoryRepository;
    }

    public OrderResponseDto createOrder(OrderDto orderDto) {
        // Get Inventories By Names
        List<String> inventoryNames = orderDto.inventoryRequestDtoList().stream()
                .map(InventoryRequestDto::inventoryName)
                .toList();

        // Get Inventories
        List<InventoryDto> inventoryDtoList = inventoryService.fetchAllInName(inventoryNames);

        // Persist the order
        final AtomicLong amount = new AtomicLong();
        Order savedOrder = buildAndPersistOrder(orderDto);
        log.info("Order created: {}", savedOrder);

        // Build and persist the OrderInventory
        buildAndPersistOrderInventories(orderDto, inventoryDtoList, savedOrder.getId(), amount);

        return new OrderResponseDto("Order Currently Processed", 102);
    }

    private void buildAndPersistOrderInventories(OrderDto orderDto, List<InventoryDto> inventoryDtoList, long orderId, AtomicLong amount) {
        List<OrderInventory> orderInventories = new ArrayList<>(inventoryDtoList.size());

        inventoryDtoList.forEach(inventoryDto -> {
            OrderInventory orderInventory = new OrderInventory();
            orderInventory.setOrderId(orderId);
            InventoryRequestDto inventoryRequestDto = getInventoryRequestDtoByName(inventoryDto.name(), orderDto.inventoryRequestDtoList());
            orderInventory.setInventoryId(inventoryDto.id());
            orderInventory.setQty(inventoryRequestDto.qty());

            long totalPrice = inventoryDto.price() * inventoryRequestDto.qty();
            orderInventory.setTotalQtyPrice(totalPrice);

            orderInventories.add(orderInventory);
            amount.addAndGet(totalPrice);
        });

        log.info("Order Inventories persisted: {}", orderInventories);
        orderInventoryRepository.saveAll(orderInventories);
    }

    private static InventoryRequestDto getInventoryRequestDtoByName(String inventoryName, List<InventoryRequestDto> inventoryRequestDtoList) {
        return inventoryRequestDtoList.stream()
                .filter(inv -> inv.inventoryName().equals(inventoryName))
                .findFirst().orElse(null);
    }

    private Order buildAndPersistOrder(OrderDto orderDto) {
        Order createOrder = Order.builder()
                .orderIdentifier(UUID.randomUUID().toString())
                .customerName(orderDto.customerName())
                .customerEmail(orderDto.customerEmail())
                .status(Status.COMPLETED)
                .build();

        return orderRepository.save(createOrder);
    }
}