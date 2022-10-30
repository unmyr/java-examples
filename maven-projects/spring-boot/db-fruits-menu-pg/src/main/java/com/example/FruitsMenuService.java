package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.FruitsMenu;
import com.example.FruitsMenuRepository;

@Service
@Transactional
public class FruitsMenuService {
  @Autowired
  FruitsMenuRepository repository;

  public List<FruitsMenu> findAll() {
    // return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
    return repository.findAll();
  } 
}
