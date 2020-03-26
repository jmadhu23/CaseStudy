package org.perscholas.casestudy.repository;

import java.util.List;

import org.perscholas.casestudy.model.Property;
import org.perscholas.casestudy.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
		//Method to find the list of rooms beloging to a property
		List<Room> findByProperty(Property property);
}
