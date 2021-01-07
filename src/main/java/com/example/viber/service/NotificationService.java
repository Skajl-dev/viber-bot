package com.example.viber.service;

import com.example.viber.model.Receiver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    private final ViberService viberService;
    private final ReceiverService receiverService;

    @Autowired
    public NotificationService(ViberService viberService, ReceiverService receiverService) {
        this.viberService = viberService;
        this.receiverService = receiverService;
    }

    @Scheduled(fixedRate = 12000)
    public void sendNotificationMessage() {
        Thread thread = new Send(receiverService.getAllReceivers());
        thread.start();
    }

    class Send extends Thread {
        private final List<Receiver> receiverList;
        private int counter = 30;

        Send(List<Receiver> receivers) {this.receiverList = receivers;}

        public void run() {
            for (int i = 0; i < receiverList.size(); i++) {
            viberService.sentMessage(receiverList.get(i).getId(), "HELLLO");

                if (i == counter) {
                    try {
                        Thread.currentThread().sleep(2000);
                        counter += 30;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
