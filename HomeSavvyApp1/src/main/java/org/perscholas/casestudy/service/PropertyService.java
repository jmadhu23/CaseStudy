package org.perscholas.casestudy.service;

import java.util.List;

import org.perscholas.casestudy.model.Property;
import org.perscholas.casestudy.model.User;
import org.perscholas.casestudy.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

		@Autowired
		private PropertyRepository propertyRepo;
		
		//Method to find all the properties
		public List<Property> getPropertyList() {
			return propertyRepo.findAll();
		}
		
		//Method to save an item after creating a new property or updating an existing property
		public void saveProperty(Property property) {
			propertyRepo.save(property);
		}
		
		//Method to find property based on id
		public Property getPropertyById(int id) {
			return propertyRepo.findById(id).get();
		}
		
		//Method to delete property based on id
		public void deleteProperty(int id) {
			propertyRepo.deleteById(id);
		}
		
		//Method to get the property of a user
		public Property getUserProperty(User user) {
			return propertyRepo.findByUser(user);
		}

}
