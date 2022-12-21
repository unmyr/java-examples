package com.example;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class FruitsMenuController {
  @Autowired
  FruitsMenuService service;

  @RequestMapping(value="/fruits", method=RequestMethod.GET)
  public ResponseEntity<?> list(
    @RequestParam(name = "name", required = false) String fruitName
  ) {
    if (fruitName == null) {
      return ResponseEntity.ok(service.findAll());
    }

    try {
      return ResponseEntity.ok(
        service.findOneByName(fruitName).get()
      );
    } catch(NoSuchElementException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping(value="/fruits", produces="application/json")
  public ResponseEntity<?> updatePriceByName(
    @RequestParam(name = "name", required = false) String fruitName,
    @RequestBody FruitsMenuAddPayload priceItem
  ) {
    int count = 0;
    if (fruitName != null) {
      count = service.setPriceByName(fruitName, priceItem.getPrice());
    }

    Map<String,Object> map = new HashMap<>();
    map.put("count", count);
    if (count == 0) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(map);
  }

  @Transactional
  @DeleteMapping(value="/fruits", produces="application/json")
  public ResponseEntity<?> deleteItemByName(
    @RequestParam(name = "name", required = false) String fruitName
  ) {
    int count = 0;
    if (fruitName != null) {
      count = service.deleteByName(fruitName);
    }

    Map<String,Object> map = new HashMap<>();
    map.put("count", count);
    return ResponseEntity.ok(map);
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

  @RequestMapping(value="/fruits/{id}", method=RequestMethod.GET)
  public ResponseEntity<FruitsMenu> getItemById(
    @PathVariable("id") long itemId
  ) {
    try {
      return ResponseEntity.ok(
        service.findOneById(itemId).get()
      );
    } catch(NoSuchElementException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping(value="/fruits/{id}", produces="application/json")
  public ResponseEntity<?> updatePrice(
    @PathVariable("id") long itemId,
    @RequestBody FruitsMenuAddPayload priceItem
  ) {
    int count = service.setPriceById(itemId, priceItem.getPrice());
    if (count > 0) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @Transactional
  @DeleteMapping(value="/fruits/{id}", produces="application/json")
  public ResponseEntity<?> deleteById(@PathVariable("id") long itemId) {
    service.deleteById(itemId);
    return ResponseEntity.noContent().build();
  }
}
