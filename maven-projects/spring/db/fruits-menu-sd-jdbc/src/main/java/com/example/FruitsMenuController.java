package com.example;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
  public ResponseEntity<FruitsMenu> show(@PathVariable("fruitName") String fruitName) {
    try {
      return ResponseEntity.ok(
        service.findOneByName(fruitName).get()
      );
    } catch(NoSuchElementException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping(value="/fruits/{fruitsName}", produces="application/json")
  public ResponseEntity<?> updatePrice(@PathVariable("fruitsName") String fruitsName, @RequestBody FruitsMenuAddPayload priceItem) {
    int count = service.setPriceByName(fruitsName, priceItem.getPrice());
    if (count > 0) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping(value="/fruits", produces="application/json")
  public ResponseEntity<?> add(
    @RequestBody FruitsMenuAddPayload addPayload
  ) {
    try {
      String fruitName = addPayload.getName();
      int count = service.add(fruitName, addPayload.getPrice());
      FruitsMenu fruitItem = service.findOneByName(fruitName).get();

      URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(fruitName)
        .toUri();

      if (count == 0) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(fruitItem);
      } else {
        return ResponseEntity.created(uri).body(fruitItem);
      }
    } catch(NoSuchElementException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @Transactional
  @DeleteMapping(value="/fruits/{fruitsName}", produces="application/json")
  public ResponseEntity<?> delete(@PathVariable("fruitsName") String fruitsName) {
    service.deleteByName(fruitsName);
    return ResponseEntity.noContent().build();
  }
}
