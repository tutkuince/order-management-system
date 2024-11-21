package com.incetutku.ordermanagementsystem.event.action;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventActionRepository extends CrudRepository<EventAction, Long> {
    Optional<EventAction> findEventActionByAction(Action action);
}
