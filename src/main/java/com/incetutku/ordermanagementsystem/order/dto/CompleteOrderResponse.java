package com.incetutku.ordermanagementsystem.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jmolecules.event.types.DomainEvent;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CompleteOrderResponse(boolean paymentComplete) {
}
