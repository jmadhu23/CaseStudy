package org.perscholas.casestudy.repository;

import org.perscholas.casestudy.model.Property;
import org.perscholas.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> {
	//Method to find the property of a user
	Property findByUser(User user);
}
