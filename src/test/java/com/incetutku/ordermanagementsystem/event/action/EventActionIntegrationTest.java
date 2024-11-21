package com.incetutku.ordermanagementsystem.event.action;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import static org.junit.jupiter.api.Assertions.*;

@ApplicationModuleTest
class EventActionIntegrationTest {

    @Autowired
    RepublishUncompletedEvent republishUncompletedEvent;

    @Test
    void verifyModule() {

    }

    @Test
    void testUncompletedEvent() {
        republishUncompletedEvent.republish(Action.PAYMENT);
    }
}