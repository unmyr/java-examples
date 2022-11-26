package com.example;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FruitsMenuRepositoryTest {
  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private FruitsMenuRepository repository;

  @Test
  public void findAll() {
    FruitsMenu fruitsMenu = new FruitsMenu();
    fruitsMenu.setName("apple");
    fruitsMenu.setPrice(100);
    fruitsMenu.setModTime(Timestamp.valueOf("1999-12-31 23:59:59.999"));
    this.entityManager.persist(fruitsMenu);
    java.util.List<FruitsMenu> fruits = this.repository.findAll();
    assertThat(fruits.get(0).getName()).isEqualTo("apple");
  }

  @Test
  public void findOneByName() {
    FruitsMenu fruitsMenu = new FruitsMenu();
    fruitsMenu.setName("apple");
    fruitsMenu.setPrice(100);
    fruitsMenu.setModTime(Timestamp.valueOf("1999-12-31 23:59:59.999"));
    this.entityManager.persist(fruitsMenu);
    FruitsMenu fruitItem = this.repository.findOneByName("apple");
    assertThat(fruitItem.getPrice()).isEqualTo(100);
  }
}
