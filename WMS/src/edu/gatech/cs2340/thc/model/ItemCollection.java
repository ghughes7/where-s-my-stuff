package edu.gatech.cs2340.thc.model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import android.content.Context;
import android.util.Log;

/**
 * Holds all of the item logged in the application.
 * 
 * @author Trigger Happy Coders
 * @version 1.0
 */
public class ItemCollection {

	private ArrayList<Item> itemArray;// contains all items created by all users
	private Context fileContext;
	private User user;

	/**
	 * Constructor for the ItemCollection. Is a simple ArrayList that accepts
	 * Item objects.
	 * 
	 * @param fc
	 *            - Context Android object
	 * @param user
	 *            - User a user that logs into the system and can add lost/found
	 *            items
	 */
	public ItemCollection(Context fc, User user) {
		itemArray = new ArrayList<Item>();

		fileContext = fc;
		this.user = user;

		// eraseTextFile();

		refillArray();

	}

	/**
	 * Erases a text file (that has been previously saved) Solely for testing
	 * purposes.
	 */
	public void eraseTextFile() {// for testing purposes
		try {
			FileOutputStream fos = fileContext.getApplicationContext()
					.openFileOutput("ItemCollection", Context.MODE_PRIVATE);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/*
	 * public void makeUpItems(){ itemArray.add(new Item("item1","item1 des",
	 * "1", "lost","user1")); itemArray.add(new Item("item2","item2 des", "2",
	 * "lost","user2")); itemArray.add(new Item("item3","item3 des", "3",
	 * "lost","user3")); }
	 */

	/**
	 * Fills the ArrayList with current and added users. Writes the file to a
	 * log file.
	 */
	public void refillArray() {
		int linesCount = 0;
		Log.d("in refillArray() num", Integer.toString(getNumOfUserItems()));

		try {
			String FILENAME = "ItemCollection";
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(fileContext.getApplicationContext()
							.openFileInput(FILENAME)));

			String inputString;
			ArrayList<String> info = new ArrayList<String>();

			while ((inputString = inputReader.readLine()) != null) {
				info.add(inputString);
				linesCount++;
				if (linesCount % 8 == 0) {
					itemArray.add(new Item(info.get(1), info.get(2), info
							.get(3), info.get(4), info.get(5), info.get(6),
							info.get(7), info.get(0)));
					info.clear();
				}

			}
		} // close try statement

		catch (IOException e) {

			e.printStackTrace();

		}

		Log.d("in refill method", Integer.toString(itemArray.size()));
	}

	/*
	 * public void setNumOfItems(int num){ String FILENAME = "NumberOfItems";
	 * String number = Integer.toString(num); try { FileOutputStream fos =
	 * fileContext.getApplicationContext() .openFileOutput(FILENAME,
	 * Context.MODE_PRIVATE); fos.write(number.getBytes()); fos.close(); } catch
	 * (IOException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	/**
	 * Gets the total number of items and write them to a log file.
	 * 
	 * @return the total number of items in the ArrayList
	 */
	public int getNumOfAllItems() {
		int numItems = 0;
		int linesCount = 0;
		try {
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(fileContext.getApplicationContext()
							.openFileInput("ItemCollection")));

			String inputString;

			while ((inputString = inputReader.readLine()) != null) {
				linesCount++;
				if (linesCount % 8 == 0) {
					numItems++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Log.d("numItems", Integer.toString(numItems));
		return numItems;

	}

	/**
	 * Gets the number of items for an individual and write them to a log file
	 * 
	 * @return - the number of items a specific user has
	 */
	public int getNumOfUserItems() {
		int numItems = 0;
		try {

			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(fileContext.getApplicationContext()
							.openFileInput("ItemCollection")));

			String inputString;

			while ((inputString = inputReader.readLine()) != null) {

				if (inputString.equals(user.getEmail())) {
					numItems++;

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.d("numItems", Integer.toString(numItems));
		return numItems;

	}

	/**
	 * Adds an item to the ArrayList of Items. It also add that same item to the
	 * text file
	 * 
	 * @param i
	 *            - Item item that is being added to the ItemCollection
	 */
	public void addItem(Item i) {
		if (i == null) {
			// throw exception
		} else {
			itemArray.add(i);
			addToTextFile(i);
		}
	}

	/**
	 * Deletes an item for the ArrayList of Items.
	 * 
	 * @param i
	 *            - Item item that is being removed from the ItemCollection
	 */
	public void deleteItem(Item i) {
		for (Item item : itemArray) {
			if (item.equals(i)) {
				itemArray.remove(i);
			}
		}

		// needs to remove from text file
	}

	/**
	 * A getter that gets the ArrayList object for a specific user's items
	 * 
	 * @param owner
	 *            - String owner of the item
	 * @return the ArrayList of Items for a specific User
	 */
	public ArrayList<Item> getItemsOfUser(String owner) {
		ArrayList<Item> userItems = new ArrayList<Item>();

		for (int i = 0; i < itemArray.size(); i++) {
			if (itemArray.get(i).getOwner().equals(owner)) {
				userItems.add(itemArray.get(i));
			}
		}
		return userItems;
	}

	/**
	 * A getter that gets the ArrayList object for all the items in the
	 * ItemCollection
	 * 
	 * @return an ArrayList of all the Items
	 */
	public ArrayList<Item> getAllItems() {
		ArrayList<Item> userItems = new ArrayList<Item>();

		for (int i = 0; i < itemArray.size(); i++) {
			userItems.add(itemArray.get(i));
		}
		return userItems;
	}

	/**
	 * Adds items to the text file for persistence
	 * 
	 * @param item
	 *            - Item writing item to the file
	 */
	public void addToTextFile(Item item) {
		Log.d("adding to file", item.getItemName());
		String FILENAME = "ItemCollection";

		try {
			FileOutputStream fos = fileContext.getApplicationContext()
					.openFileOutput(FILENAME, Context.MODE_APPEND);

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
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * A getter for the generic ItemArray
	 * 
	 * @return the ArrayList of Items
	 */
	public ArrayList<Item> getItemsArray() {
		return itemArray;

	}

}
