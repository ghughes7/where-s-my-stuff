package edu.gatech.cs2340.thc.model;
import java.util.*;

/**
 * Makes a collection of users that are registered for using this application
 */
public class UserCollection {
	
	//Creates an array list of users
	private List<User> userArray = new ArrayList<User>();
	
	//Makes a user and adds it to the array list of users
	public UserCollection(){
		User u = new User("fakeuser","fake","blah@gmail.com");	
		userArray.add(u);
	}
	
	//Gets the user
	public User getUser(){
		return userArray.get(0);	
	}
}
