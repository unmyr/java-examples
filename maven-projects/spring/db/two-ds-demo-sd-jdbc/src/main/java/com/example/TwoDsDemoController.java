package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwoDsDemoController {
  @Autowired
  private TwoDsDemoRepo1 repo1;
  @Autowired
  private TwoDsDemoRepo2 repo2;

  @RequestMapping(value="/", method=RequestMethod.GET)
  public List<Long> documentRoot() {
    return List.of(
      repo1.getRecord(),
      repo2.getRecord()
    );
  }

  @RequestMapping(value="/one", method=RequestMethod.GET)
  public Long one() {
    return repo1.getRecord();
  }

  @RequestMapping(value="/two", method=RequestMethod.GET)
  public Long two() {
    return repo2.getRecord();
  }
}
