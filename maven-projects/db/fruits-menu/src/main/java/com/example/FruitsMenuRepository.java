package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitsMenuRepository extends JpaRepository<FruitsMenu, Integer>{
  Integer deleteByName(String name);
  FruitsMenu findOneByName(String name);
}
