package com.incetutku.ordermanagementsystem.order.dto;

import com.incetutku.ordermanagementsystem.event.action.Action;
import com.incetutku.ordermanagementsystem.event.action.CustomEventMarker;

@CustomEventMarker(eventAction = Action.EMAIL)
public record EmailDto(
        String email,
        String customerName,
        String orderIdentifier,
        long totalAmount,
        boolean orderCompleted
) {
}
