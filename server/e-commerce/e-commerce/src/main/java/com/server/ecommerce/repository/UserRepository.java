package com.server.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.ecommerce.Enum.userRole;
import com.server.ecommerce.entity.user;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {

	Optional<user> findFirstByEmail(String email);
	user findByRole(userRole UserRole);

}
