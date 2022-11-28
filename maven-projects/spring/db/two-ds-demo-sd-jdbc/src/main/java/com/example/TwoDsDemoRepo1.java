package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TwoDsDemoRepo1 {
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public TwoDsDemoRepo1(
    @Qualifier("db1NamedJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate
  ) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public long getRecord() {
    return jdbcTemplate.query(
      "SELECT id FROM user_1.simple",
      new SimpleRowMapper()
    ).stream().count();
  }
}
