package com.example;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fruits_menu")
public class FruitsMenu {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="name")
  private String name;

  @Column(name="price")
  private int price;

  @Column(name="mod_time")
  private Timestamp modTime;

  public Integer getId() {
    return id;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Timestamp getModTime() {
    return modTime;
  }

  public void setModTime(Timestamp modTime) {
    this.modTime = modTime;
  }
}
