package com.example;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GreetingEnConfigClass {
  public GreetingService getConfig() {
      return new GreetingEnImpl("Jane Doe");
  }
}
