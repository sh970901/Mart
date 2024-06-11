package com.hun.market.core.event;

import org.springframework.context.ApplicationEventPublisher;

public class Events {

    private static ApplicationEventPublisher publisher;

    static void setPublisher(ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }

    public static void raise(Object object){
        if (publisher != null) {
            publisher.publishEvent(object);
        }
    }
}
