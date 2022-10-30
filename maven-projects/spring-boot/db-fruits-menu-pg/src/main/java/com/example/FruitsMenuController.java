package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.FruitsMenu;

@RestController
public class FruitsMenuController {
  @Autowired
  FruitsMenuService service;

  @RequestMapping(value="/api/lists",method=RequestMethod.GET)
  public List<FruitsMenu> index() {
    return service.findAll();
  }
}
