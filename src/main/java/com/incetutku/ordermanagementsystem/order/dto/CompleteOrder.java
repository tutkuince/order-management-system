package com.incetutku.ordermanagementsystem.order.dto;

import com.incetutku.ordermanagementsystem.event.action.Action;
import com.incetutku.ordermanagementsystem.event.action.CustomEventMarker;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.COMPLETE_PAYMENT)
public record CompleteOrder(
        String orderIdentifier,
        boolean paymentComplete
) implements DomainEvent {
}
