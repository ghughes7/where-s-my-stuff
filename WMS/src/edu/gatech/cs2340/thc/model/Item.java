package edu.gatech.cs2340.thc.model;

import java.io.Serializable;
import java.util.ArrayList;


public class Item implements Serializable{//so we can pass an ItemProfile object to different intents/activities
	
	private String owner;
	private String itemName;
	private String itemDescription;
	private String reward;
	private String status;
	private boolean donation;
	private String type;
	private String dateCreated;
	private String catagory;
	private String location;
	
	

	public Item(String itemN, String itemDes, String r, String t, String date, String c, String l, String o){
		
		itemName = itemN;
		itemDescription = itemDes;
		reward = r;
		//status = s;
		//donation = d;
		type = t;//same as status
		owner = o;
		dateCreated = date;
		catagory = c;
		location = l;
	

		
	}
	

	
	public String getItemName(){
		return itemName;
		
	}
	public String getItemDes(){
		return itemDescription;
	}
	public String getReward(){
		return reward;
	}
	public String getStatus(){
		
		return status;
	}
	public boolean getDonation(){
		return donation;
		
	}
	public String getType(){
		
		return type;
	}
	public String getDate(){
		return dateCreated;
		
	}
	public String getCatagory(){
		
		return catagory;
	}
	public String getOwner(){
		return owner;
		
	}
	
	public String getLocation(){
		return location;
	}
	
	public String toString(){
		String str = "Item Name: " + getItemName() + "\n" + "Item Description: "
				+getItemDes() + "\n" + "Item Reward: " + getReward() 
				+ "\n" + "Type : " +getType() + "\n" + "Date: " +getDate() + "\n" + 
				"Category: " +getCatagory() + "\n" + "Location: " + getLocation() + "\n" + 
				"Owner: " +getOwner() + "\n";
		return str;
	}
	
	
}
