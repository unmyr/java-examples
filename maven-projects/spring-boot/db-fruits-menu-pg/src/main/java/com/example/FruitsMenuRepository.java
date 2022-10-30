package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FruitsMenu;

@Repository
public interface FruitsMenuRepository extends JpaRepository<FruitsMenu, Integer>{
  
}
