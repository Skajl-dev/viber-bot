package com.example.viber.model;

public class ViberMessageIn {
    private EventTypes event;
    private Sender sender;
    private Message message;
    private String context;
    private String user_id;

    public ViberMessageIn() {}

    public EventTypes getEvent() {
        return event;
    }

    public void setEvent(EventTypes event) {
        this.event = event;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}