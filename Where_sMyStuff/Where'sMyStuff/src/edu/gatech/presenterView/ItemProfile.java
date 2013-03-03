package edu.gatech.presenterView;

public class ItemProfile {
	
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
