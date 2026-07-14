package com.farhan.backend.dto.groq;

public class Choice {

    private Message message;

    public Choice() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}