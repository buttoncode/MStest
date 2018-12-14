package com.buttoncode.mstest.core.security;

import com.buttoncode.mstest.core.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer>
{



}
