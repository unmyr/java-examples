package com.example;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class JdbcConfig {

  @Bean("db1Properties")
  @Primary
  @ConfigurationProperties(prefix = "app.datasource.first")
  public DataSourceProperties dataSourceProperties() {
      return new DataSourceProperties();
  }

  @Bean("db1DataSource")
  @Primary
  @ConfigurationProperties("app.datasource.first.configuration")
  public HikariDataSource firstDataSource(
    @Qualifier("db1Properties") DataSourceProperties firstDataSourceProperties
  ) {
      return firstDataSourceProperties.initializeDataSourceBuilder().type(
        HikariDataSource.class
      ).build();
  }

  @Primary
  @Bean(name = "db1NamedJdbcTemplate")
  NamedParameterJdbcTemplate createNamedParameterJdbcTemplate(
    @Qualifier("db1DataSource") DataSource ds
  ) {
      return new NamedParameterJdbcTemplate(ds);
  }

  @Bean("db2Properties")
  @ConfigurationProperties(prefix = "app.datasource.second")
  public DataSourceProperties dataSourceProperties2nd() {
      return new DataSourceProperties();
  }

  @Bean("db2DataSource")
  @ConfigurationProperties("app.datasource.second.configuration")
  public HikariDataSource secondDataSource(
    @Qualifier("db2Properties") DataSourceProperties secondDataSourceProperties
  ) {
      return secondDataSourceProperties.initializeDataSourceBuilder().type(
        HikariDataSource.class
      ).build();
  }

  @Bean(name = "db2NamedJdbcTemplate")
  NamedParameterJdbcTemplate createNamedParameterJdbcTemplate2(
    @Qualifier("db2DataSource") DataSource ds
  ) {
      return new NamedParameterJdbcTemplate(ds);
  }
}
