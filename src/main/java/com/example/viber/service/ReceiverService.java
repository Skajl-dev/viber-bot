package com.example.viber.service;

import com.example.viber.model.Receiver;
import java.util.List;

public interface ReceiverService {

    void addReceiver(Receiver receiver);

    void removeReceiver(String id);

    List<Receiver> getAllReceivers();
}
