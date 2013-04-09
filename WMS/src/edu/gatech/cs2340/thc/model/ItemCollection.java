package edu.gatech.cs2340.thc.model;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;

import android.content.Context;
import android.util.Log;

public class ItemCollection implements Serializable{
	
	private ArrayList<Item> itemArray;//contains all items created by all users
	private Context fileContext;
	private User user;
	
	public ItemCollection(Context fc, User user){
		itemArray = new ArrayList<Item>(); 
		
		fileContext = fc;
		this.user = user;
		
		//eraseTextFile();
		
		refillArray();
		
		
	}

	
	public void eraseTextFile(){//for testing purposes
		try {
			FileOutputStream fos = fileContext.getApplicationContext()
					.openFileOutput("ItemCollection", Context.MODE_PRIVATE);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	
	public void refillArray(){
		int linesCount = 0;
	
		
		
			try {
				String FILENAME = "ItemCollection";
				BufferedReader inputReader = new BufferedReader
						(new InputStreamReader(fileContext.getApplicationContext().openFileInput(FILENAME)));
					
				String inputString;
				ArrayList<String> info = new ArrayList<String>();

				while ((inputString = inputReader.readLine()) != null) {
					info.add(inputString);
					linesCount++;
					if(linesCount % 8 == 0){
						itemArray.add(new Item(info.get(1),info.get(2),info.get(3), info.get(4), info.get(5), info.get(6),info.get(7), info.get(0)));
						info.clear();
					}
					
				}
			} //close try statement
			
			catch (IOException e) {

				e.printStackTrace();

			}
			
			
		
		
	}
	
	/*
	public int getNumOfAllItems(){
		int numItems = 0;
		int linesCount = 0;
		try {
			BufferedReader inputReader = 
			new BufferedReader(new InputStreamReader(fileContext.getApplicationContext().
			openFileInput("ItemCollection")));
				
			String inputString;
			
			while ((inputString = inputReader.readLine()) != null) {
				linesCount++;
				if (linesCount % 8 == 0) {
					numItems++;
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Log.d("numItems",Integer.toString(numItems));
		return numItems;
		
	}
	
	public int getNumOfUserItems(){
		int numItems = 0;
		try {
		
			
			BufferedReader inputReader = 
			new BufferedReader(new InputStreamReader(fileContext.getApplicationContext().
			openFileInput("ItemCollection")));
				
			String inputString;
			
			while ((inputString = inputReader.readLine()) != null) {
			
				if(inputString.equals(user.getEmail())){
					numItems++;
					
				}
				
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		Log.d("numItems",Integer.toString(numItems));
		return numItems;
		
	}*/
	
	public void addItem(Item i){
		if(i == null){
			//throw exception
		}
		else{
			itemArray.add(i);	
			addToTextFile(i);
		}
	}
	
	public void deleteItem(Item i){
		
		
				try {//this is the copy text file on which the old text file will be copied onto
					FileOutputStream fosUserCollectionCopy = fileContext.getApplicationContext()
							.openFileOutput("ItemCollectionCopy", Context.MODE_PRIVATE);
				} catch (IOException e){
					e.printStackTrace();
				}
				
				try {
					int count = 0;
					BufferedReader inputReader = new BufferedReader(
							new InputStreamReader(fileContext.getApplicationContext()
									.openFileInput("ItemCollection")));
					
					FileOutputStream fosUserCollectionCopy = fileContext.getApplicationContext()
							.openFileOutput("ItemCollectionCopy", Context.MODE_PRIVATE);
					
					String inputString;
					
					ArrayList<String> info = new ArrayList<String>();
					
					while ((inputString = inputReader.readLine()) != null) {
						count++;
						info.add(inputString);
						if(count == 8){
							if (!info.get(count-8).equals(i.getOwner())) {//if the owners are different, then they are not the item to be deleted
								fosUserCollectionCopy.write(info.get(0).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(1).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(2).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(3).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(4).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(5).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(6).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(7).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
							}
							else if(info.get(count-8).equals(i.getOwner()) && !(i.getItemName().equals(info.get(1)))){//if they are same owner, but not the same item name then they are not the item to be deleted
								fosUserCollectionCopy.write(info.get(0).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(1).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(2).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(3).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(4).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(5).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(6).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								fosUserCollectionCopy.write(info.get(7).getBytes());
								fosUserCollectionCopy.write(System.getProperty("line.separator").getBytes());
								
							}
							info.clear();
							count = 0;
						}//if count == 8
					}
					fosUserCollectionCopy.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				try {
					BufferedReader inputReader = new BufferedReader(
							new InputStreamReader(fileContext.getApplicationContext()
									.openFileInput("ItemCollectionCopy")));
					FileOutputStream fos0 = fileContext.getApplicationContext()
							.openFileOutput("ItemCollection", Context.MODE_PRIVATE);//erases original file
					FileOutputStream fos = fileContext.getApplicationContext()
							.openFileOutput("ItemCollection", Context.MODE_APPEND);
					
					String inputString;
					while ((inputString = inputReader.readLine()) != null) {
						fos.write(inputString.getBytes());
						fos.write(System.getProperty("line.separator").getBytes());
						Log.d("inputString", inputString);	
					}
					fos0.close();
					fos.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}	
				refillArray();	
	}
	
	public ArrayList<Item> getItemsOfUser(String owner){
		ArrayList<Item> userItems = new ArrayList<Item>();
	
		for(int i = 0; i < itemArray.size(); i++){
			Log.d("itemArray size",Integer.toString(itemArray.size()));
			Log.d("item owner", itemArray.get(i).getOwner());
			if(itemArray.get(i).getOwner().equals(owner)){
				userItems.add(itemArray.get(i));	
			}
		}
		return userItems;
	}
	
	public ArrayList<Item> getAllItems(){
		ArrayList<Item> userItems = new ArrayList<Item>();
	
		for(int i = 0; i < itemArray.size(); i++){
			userItems.add(itemArray.get(i));
		}
		return userItems;
	}
	
	public void addToTextFile(Item item){
		Log.d("adding to file", item.getItemName());
		String FILENAME = "ItemCollection";
		
		try{
			FileOutputStream fos = fileContext.getApplicationContext().openFileOutput(FILENAME, Context.MODE_APPEND);
			
			fos.write(item.getOwner().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
	
			fos.write(item.getItemName().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			
			fos.write(item.getItemDes().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			
			fos.write(item.getReward().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			
			fos.write(item.getType().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			
			fos.write(item.getDate().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			
			fos.write(item.getCatagory().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			
			fos.write(item.getLocation().getBytes());
			fos.write(System.getProperty("line.separator").getBytes());
			
			fos.close();
		}
		catch(IOException e1){
			e1.printStackTrace();
		}
		
	}
	
	
	public ArrayList<Item> getItemsArray(){
		return itemArray;
		
	}
	
}
