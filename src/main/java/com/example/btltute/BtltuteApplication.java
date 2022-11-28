package com.example.btltute;

import com.example.btltute.domains.Role;
import com.example.btltute.models.RoleDTO;
import com.example.btltute.models.RoleToUserDTO;
import com.example.btltute.models.UserDTO;
import com.example.btltute.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BtltuteApplication {

  public static void main(String[] args) {
    SpringApplication.run(BtltuteApplication.class, args);
  }

//  @Bean
//  CommandLineRunner run(UserService userService) {
//    return args -> {
//      userService.saveRole(new RoleDTO("ROLE_USER"));
//      userService.saveRole(new RoleDTO("ROLE_ADMIN"));
//      userService.saveUser(new UserDTO("taikhoanadmin", "123456", "admin@gmail.com"));
//      userService.saveUser(new UserDTO("taikhoanuser", "123456", "user@gmail.com"));
//      userService.addRoleToUser(new RoleToUserDTO("taikhoanadmin", "ROLE_ADMIN"));
//      userService.addRoleToUser(new RoleToUserDTO("taikhoanuser", "ROLE_USER"));
//    };
//  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
