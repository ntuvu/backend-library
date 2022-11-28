package com.example.btltute.services;

import com.example.btltute.domains.Role;
import com.example.btltute.domains.User;
import com.example.btltute.exceptions.CustomException;
import com.example.btltute.models.RoleDTO;
import com.example.btltute.models.RoleToUserDTO;
import com.example.btltute.models.UserDTO;
import java.util.List;

public interface UserService {

  User saveUser(UserDTO dto) throws CustomException;

  Role saveRole(RoleDTO dto) throws CustomException;

  void addRoleToUser(RoleToUserDTO dto);

  User getUser(String username);

  List<User> getUserList();
}