package edu.gatech.cs2340.thc.model;
import java.util.*;


/**
 * for storing a list of users
 * @author circusburger63
 *
 */
public class UserCollection {
	
	private List<User> userArray = new ArrayList<User>();
	
	public UserCollection(){
		User u = new User("fakeuser","fake","blah@gmail.com");	
		userArray.add(u);
	}
	
	public User getUser(){//will change later
		return userArray.get(0);
		
	}
	
	
}
