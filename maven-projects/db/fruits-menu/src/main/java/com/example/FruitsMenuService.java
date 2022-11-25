package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FruitsMenuService {
  @Autowired
  FruitsMenuRepository repository;

  public 
  void add(String fruitName, int price) {
    FruitsMenu fruitItem = new FruitsMenu();
    fruitItem.setName(fruitName);
    fruitItem.setPrice(price);
    repository.save(fruitItem);
  }

  public List<FruitsMenu> findAll() {
    return repository.findAll();
  }

  @Transactional
  void deleteByName(String fruitName) {
    repository.deleteByName(fruitName);
  }
}
