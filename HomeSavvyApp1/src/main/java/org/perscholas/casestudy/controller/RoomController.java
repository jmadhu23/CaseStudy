package org.perscholas.casestudy.controller;

import java.util.List;

import org.perscholas.casestudy.model.Property;
import org.perscholas.casestudy.model.Room;
import org.perscholas.casestudy.service.PropertyService;
import org.perscholas.casestudy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/room")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private PropertyService propertyService;
	
	@RequestMapping("/{id}")
	public String viewRoomsPage(@PathVariable(name="id") int id,Model model) {
		//System.out.println("inside rooms.."+roomList.get(0).getName());
		Property property = propertyService.getPropertyById(id);
		List<Room> roomList=roomService.getRoomListByProperty(property);
		
		model.addAttribute("property", property);
		model.addAttribute("roomList", roomList);
		return "user/room/room";
	}
	
	@RequestMapping(value="/new/{id}",method=RequestMethod.GET)
	public String addRoom(@PathVariable(name="id") int id,Model model) {
		Room room = new Room();
		System.out.println("inside addroom........");
		Property property = propertyService.getPropertyById(id);
		room.setProperty(property);
		model.addAttribute("property",property);
		model.addAttribute("room", room);
		return "user/room/new";
	}
	
	@RequestMapping("/edit/{id}") 
	public ModelAndView editRoom(@PathVariable(name="id") int id) {
		System.out.println("inside edit,id= "+id);
		ModelAndView mav = new ModelAndView();
		Room room = roomService.getRoomById(id);
		
		mav.addObject("room",room);
		mav.setViewName("user/room/edit");
		return mav;
	}
	
	@RequestMapping("/view/{id}") 
	public ModelAndView viewRoom(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView();
		Room room = roomService.getRoomById(id);
		
		mav.addObject("room",room);
		mav.setViewName("user/room/view");
		return mav;
	}
	
	@RequestMapping(value = "view/delete", method=RequestMethod.POST) 
	public String deleteRoom(@ModelAttribute("room") Room room) {
		int pId = room.getProperty().getId();
		
		roomService.deleteRoom(room.getId());
		System.out.println("Record deleted");
		 return "redirect:/user/room/"+pId;
	}
	
	 @RequestMapping(value="/save", method=RequestMethod.POST)
	   public String saveRoom(@ModelAttribute("room") Room room) {
	       roomService.saveRoom(room);
	       int id = room.getProperty().getId();
	       System.out.println("Room saved");
	       System.out.println("id="+id);
	       return "redirect:/user/room/"+id;
	      
	   }

	
	
}
