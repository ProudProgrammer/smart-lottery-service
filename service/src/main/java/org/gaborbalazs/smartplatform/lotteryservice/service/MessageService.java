package org.gaborbalazs.smartplatform.lotteryservice.service;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private static final String HELLO = "Hello";

    public String getHelloMessage() {
        return HELLO;
    }
}
