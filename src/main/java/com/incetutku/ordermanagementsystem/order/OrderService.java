package com.incetutku.ordermanagementsystem.order;

import com.incetutku.ordermanagementsystem.exception.ModulithException;
import com.incetutku.ordermanagementsystem.inventory.exposed.InventoryDto;
import com.incetutku.ordermanagementsystem.inventory.exposed.InventoryService;
import com.incetutku.ordermanagementsystem.order.dto.*;
import com.incetutku.ordermanagementsystem.order.type.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class OrderService {

    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;
    private final OrderInventoryRepository orderInventoryRepository;
    private final OrderEventService orderEventService;

    public OrderService(
            InventoryService inventoryService,
            OrderRepository orderRepository,
            OrderInventoryRepository orderInventoryRepository,
            OrderEventService orderEventService) {
        this.inventoryService = inventoryService;
        this.orderRepository = orderRepository;
        this.orderInventoryRepository = orderInventoryRepository;
        this.orderEventService = orderEventService;
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

        OrderPaymentDto orderPaymentDto = new OrderPaymentDto(savedOrder.getOrderIdentifier(), amount.get());

        EmailDto emailDto = new EmailDto(orderDto.customerEmail(), orderDto.customerName(), savedOrder.getOrderIdentifier(), orderPaymentDto.amount(), false);

        orderEventService.completeOrder(orderPaymentDto, emailDto);

        return new OrderResponseDto("Order Currently Processed", 102);
    }

    public CompleteOrderResponse completePayment(CompleteOrder completeOrder) {
        Optional<Order> optionalOrder = orderRepository.findOrderByOrderIdentifier(completeOrder.orderIdentifier());
        if (optionalOrder.isEmpty()) {
            throw new ModulithException("Order with id not found " + completeOrder.orderIdentifier());
        }
        Order order = optionalOrder.get();
        final long amount = orderInventoryRepository.orderIdAmount(order.getId());
        EmailDto emailDto = new EmailDto(order.getCustomerEmail(), order.getCustomerName(), order.getOrderIdentifier(), amount, true);

        orderEventService.completePayment(completeOrder, emailDto);

        return new CompleteOrderResponse(true);
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
