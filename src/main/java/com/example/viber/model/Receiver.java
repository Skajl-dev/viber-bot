package com.example.viber.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Receiver {
    @Id
    private String id;
    private String name;
    private String email;

    public Receiver() {}

    public Receiver(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Receiver(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}