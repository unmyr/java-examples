package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OneService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public int testConnection() {
		Integer result = jdbcTemplate.queryForObject(
			"SELECT 1", Integer.class
		);

		return (result != null) ? result : -1;
	}
}
