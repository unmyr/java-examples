package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FruitsMenuController {
  @Autowired
  FruitsMenuService service;

  @RequestMapping(value="/fruits", method=RequestMethod.GET)
  public List<FruitsMenu> list() {
    return service.findAll();
  }

  @RequestMapping(value="/fruits/{fruitName}", method=RequestMethod.GET)
  public FruitsMenu show(@PathVariable("fruitsName") String fruitName) {
    return service.findOneByName(fruitName);
  }

  @PostMapping(value="/fruits/{fruitsName}", produces="application/json")
  public ResponseEntity<StringResponse> add(@PathVariable("fruitsName") String fruitsName, @RequestBody FruitsMenuAddPayload priceItem) {
    try {
      service.add(fruitsName, priceItem.getPrice());
    } catch (org.springframework.dao.DataIntegrityViolationException ex) {
      return new ResponseEntity<StringResponse>(
        new StringResponse(ex.getCause().toString()), HttpStatus.CONFLICT
      );
    }
    return new ResponseEntity<StringResponse>(
      new StringResponse("OK"), HttpStatus.OK
    );
  }

  @PutMapping(value="/fruits/{fruitsName}", produces="application/json")
  public ResponseEntity<StringResponse> updatePrice(@PathVariable("fruitsName") String fruitsName, @RequestBody FruitsMenuAddPayload priceItem) {
    try {
      service.setPriceByName(fruitsName, priceItem.getPrice());
    } catch (org.springframework.dao.DataIntegrityViolationException ex) {
      return new ResponseEntity<StringResponse>(
        new StringResponse(ex.getCause().toString()), HttpStatus.CONFLICT
      );
    }
    return new ResponseEntity<StringResponse>(
      new StringResponse("OK"), HttpStatus.OK
    );
  }

  @Transactional
  @DeleteMapping(value="/fruits/{fruitsName}", produces="application/json")
  public ResponseEntity<StringResponse> delete(@PathVariable("fruitsName") String fruitsName) {
    service.deleteByName(fruitsName);
    return new ResponseEntity<StringResponse>(
      new StringResponse("OK"), HttpStatus.OK
    );
  }
}
