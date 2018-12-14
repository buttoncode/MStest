package com.buttoncode.mstest.core.security;

import com.buttoncode.mstest.core.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>
{

	Role findByName(String name);

}
