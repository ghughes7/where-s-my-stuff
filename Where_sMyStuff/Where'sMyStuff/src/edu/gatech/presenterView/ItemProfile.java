package edu.gatech.presenterView;

import java.io.Serializable;

@SuppressWarnings("serial") //with this annotation we are going to hide compiler warning
public class ItemProfile implements Serializable{//so we can pass an ItemProfile object to different intents/activities
	
	private String itemName;
	private String itemDescription;
	private String reward;
	private String status;
	private boolean donation;
	private String type;
	private String dateCreated;
	private String catagory;
	
	public ItemProfile(String itemN, String itemDes, String r, String t){
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
