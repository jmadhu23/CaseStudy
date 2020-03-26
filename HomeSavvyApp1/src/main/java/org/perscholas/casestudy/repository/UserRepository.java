package org.perscholas.casestudy.repository;

import org.perscholas.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	//Method to find user object based on the email
	User findByEmail(String email);
}
