package com.incetutku.ordermanagementsystem.order.dto;

import com.incetutku.ordermanagementsystem.event.action.Action;
import com.incetutku.ordermanagementsystem.event.action.CustomEventMarker;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.EMAIL)
public record EmailDto(
        String email,
        String customerName,
        String orderIdentifier,
        long totalAmount,
        boolean orderCompleted
) implements DomainEvent {
}
