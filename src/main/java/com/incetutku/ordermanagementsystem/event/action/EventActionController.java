package com.incetutku.ordermanagementsystem.event.action;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/event-action")
public class EventActionController {

    private final RepublishUncompletedEvent republishUncompletedEvent;

    public EventActionController(RepublishUncompletedEvent republishUncompletedEvent) {
        this.republishUncompletedEvent = republishUncompletedEvent;
    }

    @GetMapping
    public ResponseEntity<String> publishEvent(@RequestParam(name = "action") String action) {
        republishUncompletedEvent.republish(Action.getActionByName(action));
        return new ResponseEntity<>("Event Triggered", HttpStatus.NO_CONTENT);
    }
}
