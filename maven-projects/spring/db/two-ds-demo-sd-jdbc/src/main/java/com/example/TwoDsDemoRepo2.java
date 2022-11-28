package com.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TwoDsDemoRepo2 {
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public TwoDsDemoRepo2(
    @Qualifier("db2NamedJdbcTemplate") NamedParameterJdbcTemplate jdbcTemplate
  ) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public long getRecord() {
    return jdbcTemplate.query(
      "SELECT id FROM user_2.simple",
      new SimpleRowMapper()
    ).stream().count();
  }
}
