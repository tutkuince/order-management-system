package com.incetutku.ordermanagementsystem.event.publication;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "event_publication")
public class CustomEventPublication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private Timestamp completionDate;
    private String listenerId;
    private String eventType;
    private Timestamp publicationDate;
    private String serializedEvent;
}
