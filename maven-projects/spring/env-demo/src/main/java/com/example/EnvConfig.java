package com.example;

import java.net.InetAddress;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/environments.properties")
@ConfigurationProperties("demo")
public class EnvConfig {
  private boolean boolValue;
  private String langName;
  private InetAddress remoteIp;
  private int serverPort;

  public boolean isBoolValue() {
    return boolValue;
  }

  public void setBoolValue(boolean boolValue) {
    this.boolValue = boolValue;
  }

  public String getLangName() {
    return langName;
  }

  public void setLangName(String langName) {
    this.langName = langName;
  }

  public InetAddress getRemoteIp() {
    return remoteIp;
  }

  public void setRemoteIp(InetAddress remoteIp) {
    this.remoteIp = remoteIp;
  }

  public int getServerPort() {
    return serverPort;
  }

  public void setServerPort(int serverPort) {
    this.serverPort = serverPort;
  }
}
