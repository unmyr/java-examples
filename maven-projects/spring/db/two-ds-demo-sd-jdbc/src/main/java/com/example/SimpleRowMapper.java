package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class SimpleRowMapper implements RowMapper<Simple> {

  @Override
  @Nullable
  public Simple mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Simple(rs.getInt("id"));
  }
}
