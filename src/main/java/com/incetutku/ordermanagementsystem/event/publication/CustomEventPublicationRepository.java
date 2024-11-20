package com.incetutku.ordermanagementsystem.event.publication;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CustomEventPublicationRepository extends CrudRepository<CustomEventPublication, UUID> {
    List<CustomEventPublication> findCustomEventPublicationsByPublicationDateNotNullAndCompletionDateIsNull();
    List<CustomEventPublication> findCustomEventPublicationsByPublicationDateNotNullAndCompletionDateIsNotNull();
}
