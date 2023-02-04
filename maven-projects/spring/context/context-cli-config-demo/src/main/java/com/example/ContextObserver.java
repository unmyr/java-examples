package com.example;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class ContextObserver implements ApplicationListener<ApplicationEvent> {

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    if (event instanceof ContextRefreshedEvent) {
      System.out.println(event.getClass() + ": context is refreshed.");
    } else if (event instanceof ContextClosedEvent) {
      System.out.println(event.getClass() + ": context is closed.");
    } else {
      System.out.println(event.getClass());
    }
  }

}
