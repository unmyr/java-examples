package com.example;

import java.util.List;

public interface FruitsMenuService {
  void add(String fruitName, int price);
  List<FruitsMenu> findAll();
  FruitsMenu findOneByName(String fruitName);
  void setPriceByName(String fruitName, int price);
  void deleteByName(String fruitName);
}
