package com.incetutku.ordermanagementsystem.order.dto;

import com.incetutku.ordermanagementsystem.event.action.Action;
import com.incetutku.ordermanagementsystem.event.action.CustomEventMarker;
import jakarta.validation.constraints.NotBlank;
import org.jmolecules.event.types.DomainEvent;

@CustomEventMarker(eventAction = Action.COMPLETE_PAYMENT)
public record CompleteOrder(
        @NotBlank(message = "OrderIdentifier is required")
        String orderIdentifier,
        boolean paymentComplete
) implements DomainEvent {
}
