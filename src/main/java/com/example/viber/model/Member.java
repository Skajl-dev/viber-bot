package com.example.viber.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Member {
    private String id;

    public Member() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}