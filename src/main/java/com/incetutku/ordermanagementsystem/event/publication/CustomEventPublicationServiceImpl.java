package com.incetutku.ordermanagementsystem.event.publication;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomEventPublicationServiceImpl implements CustomEventPublicationService {

    private final CustomEventPublicationRepository customEventPublicationRepository;

    public CustomEventPublicationServiceImpl(CustomEventPublicationRepository customEventPublicationRepository) {
        this.customEventPublicationRepository = customEventPublicationRepository;
    }

    @Override
    public List<CustomEventPublication> getUncompletedEventPublication() {
        return customEventPublicationRepository.findCustomEventPublicationByPublicationDateNotNullAndCompletionDateIsNull();
    }

    @Override
    public List<CustomEventPublication> getCompletedEventPublication() {
        return customEventPublicationRepository.findCustomEventPublicationByPublicationDateNotNullAndCompletionDateIsNotNull();
    }
}
