package com.example.viber.service;

import com.example.viber.config.ViberConfig;
import com.example.viber.model.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource(value= {"classpath:viber.properties"})
public class ViberServiceImpl implements ViberService {
    private static final String TOKEN_HEADER_NAME = "X-Viber-Auth-Token";

    private final ViberConfig viberConfig;
    private final RestTemplate restTemplate;
    private final  ReceiverService receiverService;

    @Autowired
    public ViberServiceImpl(ViberConfig viberConfig, RestTemplate restTemplate, ReceiverService receiverService) {
        this.viberConfig = viberConfig;
        this.restTemplate = restTemplate;
        this.receiverService = receiverService;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add(TOKEN_HEADER_NAME, viberConfig.getBotToken());
        return httpHeaders;
    }

    private ResponseEntity<String> sentMessage(String receiverId, String message) {
        ViberMessageOut viberMessageOut = new ViberMessageOut();
        viberMessageOut.setReceiver(receiverId);
        viberMessageOut.setType(MessageType.text);
        viberMessageOut.setText(message);

        HttpEntity<ViberMessageOut> entity = new HttpEntity<>(viberMessageOut, getHeaders());
        return restTemplate.exchange(viberConfig.getSendMessageUrl(), HttpMethod.POST, entity, String.class);
    }

    @Override
    public ResponseEntity<String> setWebhook() {
        /*WebHookInfo webHookInfo = new WebHookInfo();
        webHookInfo.setUrl(botUrl);
        webHookInfo.setEvent_types(new EventTypes[]{EventTypes.subscribed, EventTypes.unsubscribed,
                EventTypes.delivered, EventTypes.message, EventTypes.seen});*/

        String jsonString = new JSONObject()
            .put("url", viberConfig.getBotUrl())
            .put("event_types", new EventTypes[]{EventTypes.subscribed, EventTypes.unsubscribed,
                EventTypes.delivered, EventTypes.message, EventTypes.seen, EventTypes.conversation_started})
            .toString();

        HttpEntity<String> entity = new HttpEntity<>(jsonString, getHeaders());
        return restTemplate.exchange(viberConfig.getSetWebhookUrl(), HttpMethod.POST, entity, String.class);
    }

    @Override
    public ResponseEntity<String> removeWebHook() {
        //String data = "{\"url\": \""+botUrl+"\"}";
        String jsonString = new JSONObject()
            .put("url",viberConfig.getBotUrl())
            .toString();
        HttpEntity<String> entity = new HttpEntity<>(jsonString, getHeaders());
        return restTemplate.exchange(viberConfig.getSetWebhookUrl(), HttpMethod.POST, entity, String.class);
    }

    @Override
    public ResponseEntity<String> getAccountInfo() {
        String jsonString = new JSONObject().toString();
        HttpEntity<String> entity = new HttpEntity<>(jsonString, getHeaders());
        return restTemplate.exchange(viberConfig.getAccountInfoUrl(), HttpMethod.POST, entity, String.class);
    }

    @Override
    public ResponseEntity<String> botProcess(ViberMessageIn message)  {
        if (EventTypes.webhook.equals(message.getEvent())) {
            String jsonString = new JSONObject()
                .put("status", 0)
                .put("status_message", "ok")
                .put("event_types", new EventTypes[]{EventTypes.subscribed, EventTypes.unsubscribed,
                    EventTypes.delivered, EventTypes.message, EventTypes.seen, EventTypes.conversation_started})
                .toString();

            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        }
        else if (EventTypes.conversation_started.equals(message.getEvent())) {
            Sender sender = new Sender();
            sender.setName("ne");
            sender.setAvatar("http://avatar.example.com");
            String jsonString = new JSONObject()
                .put("sender", sender)
                .put("tracking_data", "tracking_data")
                .put("type", "picture")
                .put("text", "welcome to our bot. press send to register")
                .put("media", "https://pbs.twimg.com/profile_images/888823070181478401/kxCHqY19.jpg")
                .put("thumbnail", "http://www.images.com/thumb.jpg")
                .toString();
            return new ResponseEntity<>(jsonString, HttpStatus.OK);
        }
        else
        if (EventTypes.message.equals(message.getEvent())) {
            receiverService.addReceiver(new Receiver(message.getSender().getId()));
            return sentMessage(message.getSender().getId(), "echo: "+message.getMessage().getText());

        } else
        if (EventTypes.subscribed.equals(message.getEvent())) {
            receiverService.addReceiver(new Receiver("aaaaa", "bbbbb"));
            return sentMessage(message.getSender().getId(), "Subscribed");
        } else
        if (EventTypes.unsubscribed.equals(message.getEvent())) {
            receiverService.removeReceiver(message.getSender().getId());
            return sentMessage(message.getSender().getId(), "Unsubscribed");
        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> sentMessages(Message message) {
        receiverService.getAllReceivers().forEach(receiver -> sentMessage(receiver.getId(), message.getText()));


        return new ResponseEntity<>("", HttpStatus.OK);
    }

}