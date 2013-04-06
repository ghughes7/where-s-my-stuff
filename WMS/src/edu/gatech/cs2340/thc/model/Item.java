package edu.gatech.cs2340.thc.model;

import java.io.Serializable;

/**
 * Defines what it means to be an item. Item is an information holder. Data is
 * used to sort, organize and find items in the database. Implements
 * Serializable for persistence requirement. This allows us to pass an
 * ItemProfile object to different activities.
 * 
 * @author Trigger Happy Coders
 * @version 1.0
 */
public class Item implements Serializable {// so we can pass an ItemProfile
											// object to different
											// intents/activities

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

	/**
	 * Constructor for an Item
	 * 
	 * @param itemN
	 *            - String the item's name
	 * @param itemDes
	 *            - String the item's description
	 * @param r
	 *            - String the item's reward
	 * @param t
	 *            - String the type of the item
	 * @param date
	 *            - String date the item was found
	 * @param c
	 *            - String the category of the item
	 * @param l
	 *            - String the location the item was lost/found
	 * @param o
	 *            - String the owner of the item
	 */
	public Item(String itemN, String itemDes, String r, String t, String date,
			String c, String l, String o) {

		itemName = itemN;
		itemDescription = itemDes;
		reward = r;
		// status = s;
		// donation = d;
		type = t;// same as status
		owner = o;
		dateCreated = date;
		catagory = c;
		location = l;

	}

	/**
	 * A getter for item's name
	 * 
	 * @return - the name of the item
	 */
	public String getItemName() {
		return itemName;

	}

	/**
	 * A getter for the item's description
	 * 
	 * @return - the description of the item
	 */
	public String getItemDes() {
		return itemDescription;
	}

	/**
	 * A getter for the item's reward
	 * 
	 * @return - the reward of the item
	 */
	public String getReward() {
		return reward;
	}

	/**
	 * A getter for the item's status
	 * 
	 * @return - the status of the item
	 */
	public String getStatus() {

		return status;
	}

	/**
	 * A getter for whether or not the item is a donation
	 * 
	 * @return - if the item is a donation or just a regular item
	 */
	public boolean getDonation() {
		return donation;

	}

	/**
	 * A getter for the item's type
	 * 
	 * @return - type of the item
	 */
	public String getType() {

		return type;
	}

	/**
	 * A getter for the item's date said item was lost/found
	 * 
	 * @return - date said item was lost/found
	 */
	public String getDate() {
		return dateCreated;

	}

	/**
	 * A getter for the item's category
	 * 
	 * @return - category of the item
	 */
	public String getCatagory() {

		return catagory;
	}

	/**
	 * A getter for the owner of the item
	 * 
	 * @return - owner of the item
	 */
	public String getOwner() {
		return owner;

	}

	/**
	 * A getter for the location of the item
	 * 
	 * @return - location of the item
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Overrides Object's toString() method
	 * 
	 * @return a String of useful information about an Item object
	 */
	@Override
	public String toString() {
		return itemName + " " + catagory + " " + status + " " + dateCreated
				+ " " + type;
	}
}
