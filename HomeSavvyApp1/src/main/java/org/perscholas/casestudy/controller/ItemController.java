package org.perscholas.casestudy.controller;

import java.util.List;

import org.perscholas.casestudy.model.Item;
import org.perscholas.casestudy.model.Room;
import org.perscholas.casestudy.service.ItemService;
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
@RequestMapping("/user/room/item")
public class ItemController {
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{id}")
	public String viewItemsPage(@PathVariable(name="id") int id,Model model) {
		Room room = roomService.getRoomById(id);
		List<Item> itemList=itemService.getItemListByRoom(room);
		model.addAttribute("room", room);
		model.addAttribute("itemList", itemList);
		return "user/room/item/item";
	}
	
	@RequestMapping(value="/new/{id}",method=RequestMethod.GET)
	public String addItem(@PathVariable(name="id") int id,Model model) {
		Item item = new Item();
		Room room = roomService.getRoomById(id);
		item.setRoom(room);
		model.addAttribute("room",room);
		model.addAttribute("item", item);
		return "user/room/item/new";
	}
	
	@RequestMapping("/edit/{id}") 
	public ModelAndView editItem(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView();
		Item item = itemService.getItemById(id);
		
		mav.addObject("item",item);
		mav.setViewName("user/room/item/edit");
		return mav;
	}
	
	@RequestMapping("/view/{id}") 
	public ModelAndView viewItem(@PathVariable(name="id") int id) {
		ModelAndView mav = new ModelAndView();
		Item item = itemService.getItemById(id);
		
		mav.addObject("item",item);
		mav.setViewName("user/room/item/view");
		return mav;
	}
	
	@RequestMapping(value = "view/delete", method=RequestMethod.POST) 
	public String deleteItem(@ModelAttribute("item") Item item) {
		int rId = item.getRoom().getId();
		itemService.deleteItem(item.getId());
		System.out.println("Record deleted");
		 return "redirect:/user/room/item/"+rId;
	}
	
	 @RequestMapping(value="/save", method=RequestMethod.POST)
	   public String saveRoom(@ModelAttribute("item") Item item) {
		 System.out.println("Inside save item");
		   itemService.saveItem(item);
		   int rId = item.getRoom().getId();
		   System.out.println("rid="+rId);
	       return "redirect:/user/room/item/"+rId;
	      
	   }


}
