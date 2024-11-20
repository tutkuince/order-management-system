package com.incetutku.ordermanagementsystem.event.publication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class EventPublicationIntegrationTest {

    @Autowired
    CustomEventPublicationService service;

    @Test
    void getUncompletedEventPublicationTest() {
        List<CustomEventPublication> uncompletedEventPublication = service.getUncompletedEventPublication();

        uncompletedEventPublication.forEach(event -> {
            assertThat(event).isInstanceOf(CustomEventPublication.class);
            assertThat(event.getCompletionDate()).isNull();
        });
    }

    @Test
    void getCompletedEventPublicationTest() {
        List<CustomEventPublication> completedEventPublication = service.getCompletedEventPublication();

        completedEventPublication.forEach(event -> {
            assertThat(event).isInstanceOf(CustomEventPublication.class);
            assertThat(event.getCompletionDate()).isNotNull();
        });
    }
}