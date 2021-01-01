package com.lifemeds.admin.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EventListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("event: " + event);
    }
}