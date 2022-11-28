package com.example.btltute.controllers;

import com.example.btltute.exceptions.CustomException;
import com.example.btltute.exceptions.ExceptionUtils;
import com.example.btltute.models.ErrorDTO;
import com.example.btltute.models.RoleDTO;
import com.example.btltute.models.RoleToUserDTO;
import com.example.btltute.models.UserDTO;
import com.example.btltute.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<Object> getUserList() {
    return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
  }

  @PostMapping("/signup")
  public ResponseEntity<Object> saveUser(@RequestBody UserDTO dto) {
    try {
      return new ResponseEntity<>(userService.saveUser(dto), HttpStatus.CREATED);
    } catch (CustomException ex) {
      return new ResponseEntity<>(
          new ErrorDTO(ex.getMessageKey(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/role")
  public ResponseEntity<Object> saveRole(@RequestBody RoleDTO dto) {
    try {
      return new ResponseEntity<>(userService.saveRole(dto), HttpStatus.CREATED);
    } catch (CustomException ex) {
      return new ResponseEntity<>(
          new ErrorDTO(ex.getMessageKey(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    } catch (Exception ex) {
      return new ResponseEntity<>(
          ExceptionUtils.messages.get(ExceptionUtils.E_INTERNAL_SERVER),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/add-role-to-user")
  public ResponseEntity<Object> addRoleToUser(@RequestBody RoleToUserDTO dto) {
    userService.addRoleToUser(dto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
