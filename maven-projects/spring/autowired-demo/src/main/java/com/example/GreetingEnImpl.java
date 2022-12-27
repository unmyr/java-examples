package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class GreetingEnImpl implements GreetingService {
  public String name;

  public GreetingEnImpl(
    @Value("${demo.en.name:nil}") String name
  ) {
    this.name = name;
  }

  @Override
  public String sayHello() {
    return "Hello " + name + ".";
  }
}
