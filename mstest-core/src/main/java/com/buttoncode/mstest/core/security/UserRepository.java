package com.buttoncode.mstest.core.security;

import com.buttoncode.mstest.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{

	User findByEmail(String email);

}
