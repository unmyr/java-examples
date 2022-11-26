package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FruitsMenuServiceImpl implements FruitsMenuService {
  @Autowired
  private FruitsMenuRepository repository;

  public FruitsMenuServiceImpl(FruitsMenuRepository repository) {
      this.repository = repository;
  }

  @Override
  public void add(String fruitName, int price) {
    FruitsMenu fruitItem = new FruitsMenu();
    fruitItem.setName(fruitName);
    fruitItem.setPrice(price);
    repository.save(fruitItem);
  }

  @Override
  public List<FruitsMenu> findAll() {
    return repository.findAll();
  }
  @Override
  public FruitsMenu findOneByName(String fruitName) {
    return repository.findOneByName(fruitName);
  }

  @Override
  public void setPriceByName(String fruitName, int price) {
    FruitsMenu fruitItem = repository.findOneByName(fruitName);
    fruitItem.setPrice(price);
    repository.save(fruitItem);
  }

  @Override
  @Transactional
  public void deleteByName(String fruitName) {
    repository.deleteByName(fruitName);
  }
}
