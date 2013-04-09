package edu.gatech.cs2340.thc.model;

import java.util.ArrayList;

import android.util.Log;



public class MatchItems {
	private ItemCollection itemCollection;
	private Item item;
	
	private ArrayList<Item> itemArray;//for the entire list of items
	private ArrayList<Item> matchedItemArray;//temp array for a single item's matched items
	
	public MatchItems(ItemCollection ic, Item i){
		itemCollection = ic;
		itemArray = itemCollection.getAllItems();//whole item list
		matchedItemArray = new ArrayList<Item>();//matched item array for the item below
		item = i;//the item to be matched
		
	}
	
	public void matchTheItem(){

		for(int i = 0; i < itemArray.size();i++){
			//matches by exact name and location only, also checks whether the item is found. Right now,
			//it doesn't matches up with lost and lost items. 
			Log.d("item owner: " , item.getOwner());
			Log.d("itemArray.get(i).getOwner()", itemArray.get(i).getOwner());
			Log.d("item.getItemName()",itemArray.get(i).getItemName());
			Log.d("itemArray.get(i).getItemName()",itemArray.get(i).getItemName());
			Log.d( "item.getLocation()", item.getLocation());
			Log.d("itemArray.get(i).getLocation()",itemArray.get(i).getLocation());
			Log.d("itemArray.get(i).getType()",itemArray.get(i).getType());
			Log.d("                 ","                      ");
			if(!(item.getOwner().equals(itemArray.get(i).getOwner())) && item.getItemName().equals(itemArray.get(i).getItemName()) && item.getLocation().equals
					(itemArray.get(i).getLocation()) && itemArray.get(i).getType().equals("Found")){
				matchedItemArray.add(itemArray.get(i));
			}	
			
		}
		
	}
	
	
	
	public ArrayList<Item> getMatchItemsArray(){
		return matchedItemArray;
		
	}
}






