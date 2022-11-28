package com.example.btltute.repositories;

import com.example.btltute.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Role findRoleByName(String name);

  boolean existsByName(String name);
}
