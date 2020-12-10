package io.training.entity.commonClasses;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "time_created")
    @CreationTimestamp
    private LocalDateTime timeCreated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
/*
        String date= String.valueOf(timeCreated);
        String timeString=date.substring(0,10);*/
        this.timeCreated = timeCreated;
    }
}
