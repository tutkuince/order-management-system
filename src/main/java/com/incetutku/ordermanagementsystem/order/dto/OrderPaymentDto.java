package com.incetutku.ordermanagementsystem.order.dto;

import com.incetutku.ordermanagementsystem.event.action.Action;
import com.incetutku.ordermanagementsystem.event.action.CustomEventMarker;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.PAYMENT)
public record OrderPaymentDto(String orderId, long amount) implements DomainEvent {
}
