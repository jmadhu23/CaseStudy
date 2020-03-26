package org.perscholas.casestudy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


enum DefaultRooms {
	Kitchen, MasterBedroom, LivingRoom;
}
@Entity
public class Property {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	private String street;
	private String street2;
	private String city;
	private String state;
	private String zip;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date yearBuilt;
	private String builder;
	private long sqft;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userid",referencedColumnName = "id")
	private User user;
	
	@OneToMany(mappedBy="property",cascade = CascadeType.ALL)
	private List<Room> room;
	
	//Method to automatically create default rooms when property is created.
	public ArrayList<Room> createDefaultRooms() {
		DefaultRooms[] arr = DefaultRooms.values();
		ArrayList<Room> rList = new ArrayList<Room>();
		for(int i=0;i<arr.length;i++) {
			Room r = new Room(arr[i].toString());
			r.setProperty(this);
			rList.add(r);
		}
		return rList;
	}

	//Getters and Setters
	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Date getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(Date yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public long getSqft() {
		return sqft;
	}

	public void setSqft(long sqft) {
		this.sqft = sqft;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
