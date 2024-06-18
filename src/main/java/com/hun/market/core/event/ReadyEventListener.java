package com.hun.market.core.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnWebApplication
public class ReadyEventListener {

    String banner = """
         
         _____ ___ _____ ___   ____  ___ _____ ___ _____ ___ _____  ___ \s
        |_   _/ _ |_   _/ _ \\ / _  |/ _ |_   _/ _ |_   _/ _ |___  |/ _ \\\s
          | || | | || || | | | (_| | | | || || | | || || | | | _| | | | |
          | || |_| || || |_| |> _  | |_| || || |_| || || |_| ||_  | |_| |
          |_| \\___/ |_| \\___//_/ |_|\\___/ |_| \\___/ |_| \\___/   |_|\\___/\s
                                                                        \s
    
        """;

    @Autowired
    private ApplicationContext context;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        log.info("{}", banner);
    }

}
