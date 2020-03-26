package org.perscholas.casestudy.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.perscholas.casestudy.model.Property;
import org.perscholas.casestudy.model.Room;
import org.perscholas.casestudy.model.User;
import org.perscholas.casestudy.service.PropertyService;
import org.perscholas.casestudy.service.RoomService;
import org.perscholas.casestudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class PropertyController {
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value = { "/user/property" }, method = RequestMethod.GET)
	public ModelAndView home(Principal principal) {
		ModelAndView model = new ModelAndView();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User user =(User) auth.getPrincipal();
		User user = userService.findUserByEmail(principal.getName());
		Property property = propertyService.getUserProperty(user);
		//model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		if(property!=null) {
			System.out.println("Property="+ property.getName());
			model.addObject("update", true);
			model.addObject(property);
		}
		else {
			Property newProperty = new Property();
			newProperty.setUser(user);

			model.addObject("update", false);
		model.addObject("property", newProperty);
		}
		model.setViewName("user/property");
		return model;
	}
	

	
	@RequestMapping(value="/user/property", method=RequestMethod.POST)
	   public String saveProperty(@ModelAttribute("property") Property property,Principal principal,Model model) {
		User user = userService.findUserByEmail(principal.getName());
		Property existingProperty = propertyService.getUserProperty(user);
		propertyService.saveProperty(property);
		if(existingProperty ==null) {
		   ArrayList<Room> rList= property.createDefaultRooms();
	       for(int i=0;i<rList.size();i++) {
	    	   
	    	   roomService.saveRoom(rList.get(i));
	       }
		}
		model.addAttribute("update", true);
	       return "user/property";
	      
	   }

}
