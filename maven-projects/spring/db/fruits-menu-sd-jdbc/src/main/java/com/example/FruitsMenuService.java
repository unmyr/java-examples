package com.example;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

  public Optional<FruitsMenu> findOneById(long itemId) {
    return jdbcTemplate.query(
      "SELECT id, name, price, mod_time FROM fruits_menu WHERE id=?",
      new FruitsMenuRowMapper(),
      itemId
    ).stream().findFirst();
  }

  public Optional<FruitsMenu> findOneByName(String fruitName) {
    return jdbcTemplate.query(
      "SELECT id, name, price, mod_time FROM fruits_menu WHERE name=?",
      new FruitsMenuRowMapper(),
      fruitName
    ).stream().findFirst();
  }

  @Transactional
  public int setPriceById(long itemId, int price) {
    return jdbcTemplate.update(
      "UPDATE fruits_menu SET price = ? WHERE id = ?",
      price, itemId
    );
  }

  @Transactional
  public int setPriceByName(String fruitName, int price) {
    return jdbcTemplate.update(
      "UPDATE fruits_menu SET price = ? WHERE name = ?",
      price, fruitName
    );
  }

  @Transactional
  public int add(String fruitName, int price) {
    try {
      return jdbcTemplate.update(
        "INSERT INTO fruits_menu (name, price, mod_time) VALUES (?,?,?)",
        fruitName,
        price,
        Timestamp.from(Instant.now())
      );
    } catch(DataIntegrityViolationException ex) {
      System.out.println("Class=" + ex.getClass().toString());
      // Cause:
      // org.postgresql.util.PSQLException: ERROR: duplicate key value violates unique constraint "fruits_menu_name_key"
      //   Detail: Key (name)=(Peach) already exists.
      System.out.println("Cause=" + ex.getCause());
      // Message:
      // PreparedStatementCallback; SQL [INSERT INTO fruits_menu (name, price, mod_time) VALUES (?,?,?)]; ERROR: duplicate key value violates unique constraint "fruits_menu_name_key"
      //  Detail: Key (name)=(Peach) already exists.
      System.out.println("Message=" + ex.getMessage());
      return 0;
    }
  }

  @Transactional
  public int deleteByName(String fruitName) {
    return jdbcTemplate.update(
      "DELETE FROM fruits_menu WHERE name = ?",
      fruitName
    );
  }

  @Transactional
  public int deleteById(long itemId) {
    return jdbcTemplate.update(
      "DELETE FROM fruits_menu WHERE id = ?",
      itemId
    );
  }
}
