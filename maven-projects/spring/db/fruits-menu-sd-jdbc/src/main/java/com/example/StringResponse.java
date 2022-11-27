package com.example;

public class StringResponse {
  private String message;

  public StringResponse(String s) { 
     this.message = s;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String s) {
    this.message = s;
  }
}
