package com.incetutku.ordermanagementsystem.event.action;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(indexes = {

})
public class EventAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 2, nullable = false)
    private Action action;
    @Column(name = "event_can", nullable = false)
    private String eventCanonicalName;
}
