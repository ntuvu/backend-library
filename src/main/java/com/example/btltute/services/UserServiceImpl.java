package com.example.btltute.services;

import com.example.btltute.domains.Role;
import com.example.btltute.domains.User;
import com.example.btltute.exceptions.CustomException;
import com.example.btltute.exceptions.ExceptionUtils;
import com.example.btltute.models.RoleDTO;
import com.example.btltute.models.RoleToUserDTO;
import com.example.btltute.models.UserDTO;
import com.example.btltute.repositories.RoleRepository;
import com.example.btltute.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User saveUser(UserDTO dto) throws CustomException {
    if (userRepository.existsByUsername(dto.getUsername())) {
      throw new CustomException(
          ExceptionUtils.ACCOUNT_EXISTED,
          ExceptionUtils.messages.get(ExceptionUtils.ACCOUNT_EXISTED));
    }
    if (!dto.getEmail().matches("[a-z0-9]+@[a-z]+\\.[a-z]{2,3}")) {
      throw new CustomException(
          ExceptionUtils.EMAIL_NOT_VALID,
          ExceptionUtils.messages.get(ExceptionUtils.EMAIL_NOT_VALID));
    }
    User user = new User(dto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public Role saveRole(RoleDTO dto) throws CustomException {
    if (roleRepository.existsByName(dto.getName())) {
      throw new CustomException(
          ExceptionUtils.ROLE_EXISTED, ExceptionUtils.messages.get(ExceptionUtils.ROLE_EXISTED));
    }
    Role role = new Role(dto);
    return roleRepository.save(role);
  }

  @Override
  public void addRoleToUser(RoleToUserDTO dto) throws CustomException {
    User user = userRepository.findUserByUsername(dto.getUsername());
    Role role = roleRepository.findRoleByName(dto.getRole());
    user.getRoles().add(role);
  }

  @Override
  public User getUser(String username) {
    return userRepository.findUserByUsername(username);
  }

  @Override
  public List<User> getUserList() {
    return userRepository.findAll();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), authorities);
  }

  @Override
  public UserDetails getUserInfo() throws CustomException {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepository.findUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), authorities);
  }
}
