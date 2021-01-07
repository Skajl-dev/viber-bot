package com.example.viber.service;

import com.example.viber.model.Message;
import com.example.viber.model.ViberMessageIn;
import org.springframework.http.ResponseEntity;

public interface ViberService {

    ResponseEntity<String> sentMessage(String receiverId, String message);

    ResponseEntity<String> setWebhook();

    ResponseEntity<String> removeWebHook();

    ResponseEntity<String> getAccountInfo();

    ResponseEntity<String> botProcess(ViberMessageIn message);

    ResponseEntity<String> sentMessages(Message message);
}
