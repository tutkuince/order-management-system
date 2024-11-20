package com.incetutku.ordermanagementsystem.event.publication;

import java.util.List;

public interface CustomEventPublicationService {

    List<CustomEventPublication> getUncompletedEventPublication();
    List<CustomEventPublication> getCompletedEventPublication();
}
