package com.example.viber.service;

import com.example.viber.model.Receiver;
import com.example.viber.repository.ReceiverRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReceiverServiceImpl implements ReceiverService {


    private final ReceiverRepository receiverRepository;

    @Autowired
    public ReceiverServiceImpl(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }

    @Transactional
    @Override
    public void addReceiver(Receiver receiver) {
        receiverRepository.save(receiver);
    }

    @Transactional
    @Override
    public void removeReceiver(String id) {
        receiverRepository.deleteById(id);
    }

    @Override
    public List<Receiver> getAllReceivers() {
        List<Receiver> result = new ArrayList<>();
        receiverRepository.findAll().forEach(result::add);
        return result;
    }
}
