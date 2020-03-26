package org.perscholas.casestudy.repository;

import org.perscholas.casestudy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRole(String role);
}
