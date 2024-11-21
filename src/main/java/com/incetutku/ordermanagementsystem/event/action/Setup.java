package com.incetutku.ordermanagementsystem.event.action;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Setup implements ApplicationRunner {

    private final EventActionRepository repository;

    public Setup(EventActionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);

        provider.addIncludeFilter(new AnnotationTypeFilter(CustomEventMarker.class));

        Set<BeanDefinition> beanDefinitions = provider.findCandidateComponents("com.incetutku.ordermanagementsystem");

        Map<String, String> eventActionMap = new HashMap<>();

        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (beanDefinition instanceof AnnotatedBeanDefinition) {
                Map<String, Object> annotationAttributeMap = ((AnnotatedBeanDefinition) beanDefinition)
                        .getMetadata()
                        .getAnnotationAttributes(CustomEventMarker.class.getCanonicalName());

                eventActionMap.put(annotationAttributeMap.get("eventAction").toString(), beanDefinition.getBeanClassName());
            }
        }

        List<EventAction> eventActionList = new ArrayList<>(eventActionMap.size());

        eventActionMap.forEach((k, v) -> {
            EventAction eventAction = new EventAction();
            Action action = Action.getActionByName(k);
            eventAction.setAction(action);
            eventAction.setEventCanonicalName(v);

            if (repository.findEventActionByAction(action).isEmpty()) {
                eventActionList.add(eventAction);
            }
        });

        if (!eventActionList.isEmpty()) {
            repository.saveAll(eventActionList);
        }
    }
}
