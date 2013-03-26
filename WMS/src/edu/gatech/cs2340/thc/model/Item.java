package edu.gatech.cs2340.thc.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Item implements Serializable{//so we can pass an ItemProfile object to different intents/activities
	
	private String itemName;
	private String itemDescription;
	private String itemLocation;
	private String itemStatus;
	private String itemCategory;
	private String itemReward;
	private String itemType;
	private String itemDate;
	private String itemOwner;
	private boolean itemDonation;
	
	public Item(String name, String description, String location, String category, 
			String reward, String type, String date, String owner){
		
		itemName = name;
		itemDescription = description;
		itemLocation = location;
		//itemStatus = status;
		itemCategory = category;
		itemReward = reward;
		itemType = type;
		itemDate = date;
		itemOwner = owner;
	}
	
	public String getItemName(){
		return itemName;
	}
	public String getItemDes(){
		return itemDescription;
	}
	public String getReward(){
		return itemReward;
	}
	public String getStatus(){
		return itemStatus;
	}
	public boolean getDonation(){
		return itemDonation;
	}
	public String returnType(){
		return itemType;
	}
	public String returnDate(){
		return itemDate;
	}
	public String returnCatagory(){
		return itemCategory;
	}
	public String returnLocation(){
		return itemLocation;
	}
	public String getOwner(){
		return itemOwner;
	}
}
