package com.example.btltute.repositories;

import com.example.btltute.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findUserByUsername(String username);

  boolean existsByUsername(String username);
}
