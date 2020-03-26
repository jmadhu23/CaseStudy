package org.perscholas.casestudy.repository;

import java.time.LocalDate;
import java.util.List;

import org.perscholas.casestudy.model.Item;
import org.perscholas.casestudy.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	 //Method to find the list of items that belongs to a room
	List<Item> findByRoom(Room room);
	//Method to find the list of items that belongs to a room based on item type(fixed or movable)
	List<Item> findByRoomAndType(Room room,String type); 
	////Method to find the list of items that belongs to a room and warranty end date is between start and end dates
	List<Item> findByRoomAndWarrantyEndBetween(Room room,LocalDate start,LocalDate end);
}
