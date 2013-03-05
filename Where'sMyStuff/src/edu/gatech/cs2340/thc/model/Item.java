package edu.gatech.cs2340.thc.model;

import java.io.Serializable;//needed so that an item obj can be pass from intents to intents

@SuppressWarnings("serial") //with this annotation we are going to hide compiler warning

/**
 * For storing a single item, holding its infomation
 * @author circusburger63
 * 
 */
public class Item implements Serializable{//so we can pass an ItemProfile object to different intents/activities
	
	private String itemName;
	private String itemDescription;
	private String reward;
	private String status;
	private boolean donation;
	private String type;
	private String dateCreated;
	private String catagory;
	
	public Item(String itemN, String itemDes, String r, String t){
		itemName = itemN;
		itemDescription = itemDes;
		reward = r;
		//status = s;
		//donation = d;
		type = t;
		//dateCreated = date;
		//catagory = c;
		
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
	public String returnType(){
		
		return type;
	}
	public String returnDate(){
		return dateCreated;
		
	}
	public String returnCatagory(){
		
		return catagory;
	}
}