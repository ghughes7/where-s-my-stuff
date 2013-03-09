package edu.gatech.cs2340.thc.model;
import java.util.*;

public class ItemCollection {
	
	private List<Item> itemArray;
	
	public ItemCollection(){
		itemArray = new ArrayList<Item>(); 
		makeUpItems();
	}
	
	public void makeUpItems(){
		itemArray.add(new Item("item1","item1 des", "1", "lost","user1"));
		itemArray.add(new Item("item2","item2 des", "2", "lost","user1"));
	}
	public void addItem(Item i){
		if(i == null){
			//throw exception
			
		}
		itemArray.add(i);
		
	}
	
	public void deleteItem(Item i){
		for(Item item: itemArray){
			if(item.equals(i)){
				itemArray.remove(i);	
				
			}
			
		}
		
	}
	
	public Item getItem(String owner){
		for(int i = 0; i < itemArray.size(); i++){
			if(itemArray.get(i).getOwner().equals(owner)){
				return itemArray.get(i);
				
			}
			
		}
		return null;
		
	}
	
	
	
}
