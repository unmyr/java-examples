package com.example;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class FruitsMenuService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public FruitsMenuService(JdbcTemplate t) {
    this.jdbcTemplate = t;
  }

  public List<FruitsMenu> findAll() {
    return jdbcTemplate.query(
      "SELECT id, name, price, mod_time FROM fruits_menu",
      new FruitsMenuRowMapper()
    );
  }

  public Optional<FruitsMenu> findOneByName(String fruitName) {
    return jdbcTemplate.query(
      "SELECT id, name, price, mod_time FROM fruits_menu WHERE name=?",
      new FruitsMenuRowMapper(),
      fruitName
    ).stream().findFirst();
  }

  public int setPriceByName(String fruitName, int price) {
    return jdbcTemplate.update(
      "UPDATE fruits_menu SET price = ? WHERE name = ?",
      price, fruitName
    );
  }

  public int add(String fruitName, int price) {
    return jdbcTemplate.update(
      "INSERT INTO fruits_menu (name, price, mod_time) VALUES (?,?,?)",
      fruitName,
      price,
      Timestamp.from(Instant.now())
    );
  }

  public int deleteByName(String fruitName) {
    return jdbcTemplate.update(
      "DELETE FROM fruits_menu WHERE name = ?",
      fruitName
    );
  }
}
