package com.example.btltute.controllers;

import com.example.btltute.exceptions.CustomException;
import com.example.btltute.exceptions.ExceptionUtils;
import com.example.btltute.models.CartCreateDTO;
import com.example.btltute.models.CartUpdateDTO;
import com.example.btltute.models.ErrorDTO;
import com.example.btltute.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @PostMapping
  public ResponseEntity<Object> createCart(@RequestBody CartCreateDTO dto) {
    try {
      cartService.createCart(dto);
    } catch (CustomException ex) {
      return new ResponseEntity<>(
          new ErrorDTO(ex.getMessageKey(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateCart(@RequestBody CartUpdateDTO dto, @PathVariable Long id) {
    try {
      cartService.updateCart(dto, id);
    } catch (CustomException ex) {
      return new ResponseEntity<>(
          new ErrorDTO(ex.getMessageKey(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteCart(@PathVariable Long id) {
    try {
      cartService.deleteCart(id);
    } catch (CustomException ex) {
      return new ResponseEntity<>(
          new ErrorDTO(ex.getMessageKey(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping
  public ResponseEntity<Object> getCartList() {
    try {
      return new ResponseEntity<>(cartService.getCartOfCurrentUser(), HttpStatus.OK);
    } catch (CustomException ex) {
      return new ResponseEntity<>(
          new ErrorDTO(ex.getMessageKey(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
