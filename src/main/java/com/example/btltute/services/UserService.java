package com.example.btltute.services;

import com.example.btltute.domains.Role;
import com.example.btltute.domains.User;
import com.example.btltute.exceptions.CustomException;
import com.example.btltute.models.RoleDTO;
import com.example.btltute.models.RoleToUserDTO;
import com.example.btltute.models.UserDTO;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

  User saveUser(UserDTO dto) throws CustomException;

  Role saveRole(RoleDTO dto) throws CustomException;

  void addRoleToUser(RoleToUserDTO dto) throws CustomException;

  User getUser(String username);

  List<User> getUserList();

  UserDetails getUserInfo() throws CustomException;
}
