package com.example;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextObserver {
  @EventListener
  public void handleContextRefresh(ContextRefreshedEvent event) {
    System.out.println(event.getClass() + ": context is refreshed.");
  }

  @EventListener
  public void handleContextClosed(ContextClosedEvent event) {
    System.out.println(event.getClass() + ": context is closed.");
  }

  @EventListener
  public void onApplicationEvent(ApplicationEvent event) {
    if (event instanceof ContextRefreshedEvent || event instanceof ContextClosedEvent) {
      return;
    } else {
      System.out.println(event.getClass());
    }
  }

}
