package com.example;

import org.springframework.data.annotation.Id;

public class Simple {
  @Id
  private int id;

  public Simple(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
