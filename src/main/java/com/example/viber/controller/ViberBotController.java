package com.example.viber.controller;

import com.example.viber.model.Message;
import com.example.viber.model.ViberMessageIn;
import com.example.viber.service.ViberService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ViberBotController {

    @Autowired
    private ViberService viberService;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("ping", HttpStatus.OK);
    }

    @GetMapping(value = "/setwebhook", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> setWebHook() throws JSONException {
        return viberService.setWebhook();
    }

    @GetMapping(value = "/removewebhook", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> removeWebHook() throws JSONException {
        return viberService.removeWebHook();
    }

    @PostMapping(value = "/bot", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> botProcess(@RequestBody ViberMessageIn message) {
        return viberService.botProcess(message);
    }

    @GetMapping(value = "/accountinfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getAccountInfo() {
        return viberService.getAccountInfo();
    }

    @PostMapping(value = "/sent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> sentMessage(@RequestBody Message message) {
        return viberService.sentMessages(message);
    }
}
