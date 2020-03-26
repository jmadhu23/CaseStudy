package org.perscholas.casestudy.service;

import java.util.List;

import org.perscholas.casestudy.model.Property;
import org.perscholas.casestudy.model.Room;
import org.perscholas.casestudy.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
	@Autowired
	private RoomRepository roomRepo;
	
	public List<Room> getRoomList() {
		return roomRepo.findAll();
	}
	public List<Room> getRoomListByProperty(Property property) {
		return roomRepo.findByProperty(property);
	}
	
	public void saveRoom(Room room) {
		roomRepo.save(room);
	}
	
	public Room getRoomById(int id) {
		return roomRepo.findById(id).get();
	}
	
	public void deleteRoom(int id) {
		roomRepo.deleteById(id);
	}
	

}
