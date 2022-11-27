package com.example;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@DataJdbcTest
@Sql({"/schema.sql", "/test-data.sql"})
public class FruitsMenuServiceTest {
 	private FruitsMenuService service;

  @Autowired
  FruitsMenuServiceTest(JdbcTemplate jdbcTemplate) {
 		  service = new FruitsMenuService(jdbcTemplate);
  }

  @Test
	public void findAll() {
		List<FruitsMenu> fruits = service.findAll();
		Assertions.assertThat(fruits.size()).isEqualTo(3);
    Assertions.assertThat(
      fruits.get(0).getModTime().toString()
    ).isEqualTo("1999-12-31 23:59:59.999");
	}

  @Test
	public void findOneByName() {
		Optional<FruitsMenu> optionalFruits = service.findOneByName("Apple");
    Assertions.assertThat(optionalFruits.isPresent()).isEqualTo(true);
    FruitsMenu fruitItem = optionalFruits.get();
		Assertions.assertThat(fruitItem.getName()).isEqualTo("Apple");
    Assertions.assertThat(fruitItem.getPrice()).isEqualTo(100);
    Assertions.assertThat(
      fruitItem.getModTime().toString()
    ).isEqualTo("1999-12-31 23:59:59.999");
	}

  @Test
  public void setPriceByName() {
    int count = service.setPriceByName("Orange", 99);
    Assertions.assertThat(count).isEqualTo(1);

    Optional<FruitsMenu> optionalFruits = service.findOneByName("Orange");
    FruitsMenu fruitItem = optionalFruits.get();
    Assertions.assertThat(fruitItem.getPrice()).isEqualTo(99);
  }

  @Test
  public void addAndDelete() {
    int count = service.add("Peach", 110);
    Assertions.assertThat(count).isEqualTo(1);

    count = service.deleteByName("Peach");
    Assertions.assertThat(count).isEqualTo(1);
  }
}
