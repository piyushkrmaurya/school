package com.herokuapp.schoolmvc.model;

import java.sql.Date;

public class Event {
    Long eventId;
    String name;
    Date date;

    public Event() {
        
    }

    public Event(Long eventId, String name, Date date) {
        this.eventId = eventId;
        this.name = name;
        this.date = date;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}