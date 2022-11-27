DROP TABLE IF EXISTS fruits_menu CASCADE;
CREATE TABLE fruits_menu (
  id SERIAL PRIMARY KEY,
  name VARCHAR(16) UNIQUE,
  price INTEGER,
  mod_time timestamp DEFAULT current_timestamp
);
