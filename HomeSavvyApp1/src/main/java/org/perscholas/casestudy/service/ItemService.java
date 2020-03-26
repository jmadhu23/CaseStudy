package org.perscholas.casestudy.service;

import java.time.LocalDate;
import java.util.List;

import org.perscholas.casestudy.model.Item;
import org.perscholas.casestudy.model.Room;
import org.perscholas.casestudy.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepo;
	
	//Method to find all the items 
	public List<Item> getItemList() {
		return itemRepo.findAll();
	}
	
	//Method to find all the items belonging to a room
	public List<Item> getItemListByRoom(Room room) {
		return itemRepo.findByRoom(room);
	}
	
	//Method to find all the items of a particular item type in a room
	public List<Item> getItemListByType(Room room,String type) {
		return itemRepo.findByRoomAndType(room, type);
	}	
	
	//Method to find all the items of a room whose warranty end date is between start and end dates
	public List<Item> getItemListByDateRange(Room room,LocalDate start,LocalDate end) {
		return itemRepo.findByRoomAndWarrantyEndBetween(room, start, end);
	}
	
	//Method to save an item after creating a new item or updating an existing item
	public void saveItem(Item item) {
		itemRepo.save(item);
	}
	
	//Method to find an item with a given id
	public Item getItemById(int id) {
		return itemRepo.findById(id).get();
	}
	
	//Method to delete an item with a given id
	public void deleteItem(int id) {
		itemRepo.deleteById(id);
	}
}
