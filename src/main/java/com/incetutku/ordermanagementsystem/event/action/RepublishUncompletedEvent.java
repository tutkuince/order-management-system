package com.incetutku.ordermanagementsystem.event.action;

import com.incetutku.ordermanagementsystem.exception.ModulithException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.modulith.events.CompletedEventPublications;
import org.springframework.modulith.events.IncompleteEventPublications;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RepublishUncompletedEvent {

    public final EventActionRepository eventActionRepository;
    private final IncompleteEventPublications incompleteEventPublications;
    private final CompletedEventPublications completedEventPublications;
    private final Environment environment;

    public RepublishUncompletedEvent(
            EventActionRepository eventActionRepository,
            IncompleteEventPublications incompleteEventPublications,
            CompletedEventPublications completedEventPublications,
            Environment environment) {
        this.eventActionRepository = eventActionRepository;
        this.incompleteEventPublications = incompleteEventPublications;
        this.completedEventPublications = completedEventPublications;
        this.environment = environment;
    }

    public void republish(Action action) {
        Optional<EventAction> optionalEventAction = eventActionRepository.findEventActionByAction(action);

        if (optionalEventAction.isEmpty()) {
            log.info("No event action found for action: {}", action);
        }

        if (optionalEventAction.isPresent()) {
            log.info("Republish uncompleted events for Action {}", action);

            EventAction eventAction = optionalEventAction.get();


            try {
                Class<?> actionClass = Class.forName(eventAction.getEventCanonicalName());
                incompleteEventPublications.resubmitIncompletePublications(ep -> ep.getEvent().getClass() == actionClass);
                completedEventPublications.deletePublicationsOlderThan(Duration.ofHours(environment.getProperty("delete.event.duration", Long.class, 100L)));
            } catch (ClassNotFoundException e) {
                throw new ModulithException(e.getMessage());
            }

        }
    }

    public void republish(List<Action> actions) {
        actions.forEach(this::republish);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void republish() {
        log.info("Republishing uncompleted events");
        Arrays.stream(Action.values()).forEach(this::republish);
    }
}
