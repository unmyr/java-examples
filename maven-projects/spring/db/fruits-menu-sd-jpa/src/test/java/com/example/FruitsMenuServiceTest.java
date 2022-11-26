package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class FruitsMenuServiceTest {
  @Autowired
  private FruitsMenuRepository repository;

  private FruitsMenuService service;

  @BeforeEach
  public void setup() {
    this.service = new FruitsMenuServiceImpl(repository);
    service.add("Apple", 100);
    service.add("Banana", 120);
    service.add("Orange", 110);
  }

  @Test
  public void addAndDelete() {
    List<FruitsMenu> fruits;
    fruits = service.findAll();
    assertThat(fruits.size()).isEqualTo(3);

    service.add("Peach", 99);

    FruitsMenu fruitItem = service.findOneByName("Peach");
    assertThat(fruitItem.getPrice()).isEqualTo(99);
    fruits = service.findAll();
    assertThat(fruits.size()).isEqualTo(4);

    service.deleteByName("Peach");
    fruits = service.findAll();
    assertThat(fruits.size()).isEqualTo(3);
  }

  @Test
  public void updatePrice() {
    service.setPriceByName("Banana", 121);
    FruitsMenu fruitItem = service.findOneByName("Banana");
    assertThat(fruitItem.getPrice()).isEqualTo(121);
  }
}
