package org.perscholas.casestudy.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.perscholas.casestudy.model.Item;
import org.perscholas.casestudy.model.Property;
import org.perscholas.casestudy.model.Room;
import org.perscholas.casestudy.model.User;
import org.perscholas.casestudy.service.ItemService;
import org.perscholas.casestudy.service.PropertyService;
import org.perscholas.casestudy.service.RoomService;
import org.perscholas.casestudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/report")
public class ReportController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/reporthome")
	public String viewReportHome(Model model,Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		
		model.addAttribute("userName", user.getFirstname() + " " + user.getLastname());
		return "user/report/reporthome";
		
	}
	
	@RequestMapping("/all")
	public String viewItemsPage(Model model,Principal principal) {
		System.out.println("inside all");
		User user = userService.findUserByEmail(principal.getName());
		System.out.println("user id="+user.getId());
		Property property = propertyService.getUserProperty(user);
		
		System.out.println("properry id="+property.getId());
		List<Room> roomList = roomService.getRoomListByProperty(property);
		List<Item> itemList=new ArrayList<Item>();
		for(int i=0;i<roomList.size();i++) {
			System.out.println("inside for");
			itemList.addAll(itemService.getItemListByRoom(roomList.get(i)));
		}
		
		
		model.addAttribute("itemList", itemList);
		return "user/report/itemslist";
	}
	
	@RequestMapping("/movable")
	public String viewMovableItems(Model model,Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Property property = propertyService.getUserProperty(user);
		List<Room> roomList = roomService.getRoomListByProperty(property);
		List<Item> itemList=new ArrayList<Item>();
		for(int i=0;i<roomList.size();i++) {
			System.out.println("inside for");
			itemList.addAll(itemService.getItemListByType(roomList.get(i),"movable"));
		}
		model.addAttribute("itemList", itemList);
		return "user/report/itemslist";
	}
	
	@RequestMapping("/fixed")
	public String viewFixedItems(Model model,Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		Property property = propertyService.getUserProperty(user);
		List<Room> roomList = roomService.getRoomListByProperty(property);
		List<Item> itemList=new ArrayList<Item>();
		for(int i=0;i<roomList.size();i++) {
			System.out.println("inside for");
			itemList.addAll(itemService.getItemListByType(roomList.get(i),"fixed"));
		}
		model.addAttribute("itemList", itemList);
		return "user/report/itemslist";
	}
	
	@RequestMapping("/expiringwarranty")
	public String viewExpiringWarranty(Model model,Principal principal) {
		User user = userService.findUserByEmail(principal.getName());
		System.out.println("user id="+user.getId());
		Property property = propertyService.getUserProperty(user);
		
		System.out.println("properry id="+property.getId());
		List<Room> roomList = roomService.getRoomListByProperty(property);
		LocalDate endDate = LocalDate.now();
		LocalDate begDate = endDate.plusMonths(1);
		List<Item> itemList=new ArrayList<Item>();
		System.out.println("endDate"+endDate);
		System.out.println("begDate"+begDate);
		for(int i=0;i<roomList.size();i++) {
			System.out.println("inside for");
			itemList.addAll(itemService.getItemListByDateRange(roomList.get(i),endDate,begDate));
		}
		
		
		model.addAttribute("itemList", itemList);
		
		
		return "user/report/itemslist";
	}
	
	@RequestMapping("/itemview/{id}") 
	public ModelAndView viewItem(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView();
		Item item = itemService.getItemById(id);
		
		mav.addObject("item",item);
		mav.setViewName("user/report/itemview");
		return mav;
	}
	

}
