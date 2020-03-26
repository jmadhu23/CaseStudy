package org.perscholas.casestudy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int sqft;
	private String paintColor;
	private String paintBrand;
	
	@ManyToOne
	@JoinColumn(name="propertyid",referencedColumnName = "id")
	private Property property;
	
	@OneToMany(mappedBy="room",cascade = CascadeType.ALL)
	private List<Item> item;

	//Constructors
	public Room() {

	}

	public Room(String name) {
		this.name = name;
	}
	
	//Getters and Setters
	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSqft() {
		return sqft;
	}

	public void setSqft(int sqft) {
		this.sqft = sqft;
	}

	public String getPaintColor() {
		return paintColor;
	}

	public void setPaintColor(String paintColor) {
		this.paintColor = paintColor;
	}

	public String getPaintBrand() {
		return paintBrand;
	}

	public void setPaintBrand(String paintBrand) {
		this.paintBrand = paintBrand;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
	

	
}
