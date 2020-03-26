
package org.perscholas.casestudy.model;

import java.time.LocalDate;
//import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String type;
	private String serialNumber;
	private double value;
	private String brand;
	private String purchasedFrom;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate datePurchased;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate warrantyStart;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate warrantyEnd;
	private String sparePartNumber;
	private String userDocURL;
	
	@ManyToOne
	@JoinColumn(name="roomid",referencedColumnName = "id")
	private Room room;
	
	//Getters and Setters
	public LocalDate getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(LocalDate datePurchased) {
		this.datePurchased = datePurchased;
	}

	public LocalDate getWarrantyStart() {
		return warrantyStart;
	}

	public void setWarrantyStart(LocalDate warrantyStart) {
		this.warrantyStart = warrantyStart;
	}

	public LocalDate getWarrantyEnd() {
		return warrantyEnd;
	}

	public void setWarrantyEnd(LocalDate warrantyEnd) {
		this.warrantyEnd = warrantyEnd;
	}

	public String getPurchasedFrom() {
		return purchasedFrom;
	}

	public void setPurchasedFrom(String purchasedFrom) {
		this.purchasedFrom = purchasedFrom;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}





	public String getSparePartNumber() {
		return sparePartNumber;
	}

	public void setSparePartNumber(String sparePartNumber) {
		this.sparePartNumber = sparePartNumber;
	}

	public String getUserDocURL() {
		return userDocURL;
	}

	public void setUserDocURL(String userDocURL) {
		this.userDocURL = userDocURL;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	
}
