package edu.gatech.cs2340.thc.model;
import java.util.*;


public class UserCollection {
	
	private List<User> userArray;
	
	public UserCollection(){
		userArray = new ArrayList<User>();
		makeUpUsers();
	}
	
	public void makeUpUsers(){
		userArray.add(new User("user1","user1pw","blah@gmail.com"));
		userArray.add(new User("user2","user2pw","blah@gmail.com"));
		userArray.add(new User("user3","user3pw","blah@gmail.com"));
		
	}
	public User getUser(String name){
		
		for(int i =0; i < userArray.size(); i++){
			if(name.equals(userArray.get(i).getName())){
				return userArray.get(i);
				
			}
			
		}
		return null;
		
	}
	
	public void addUser(User u){
		userArray.add(u);
		
	}
	
	public void deleteUser(User u){
		
		for(int i =0; i < userArray.size(); i++){
			if(u.equals(userArray.get(i))){
				userArray.remove(i);
				
			}
			
		}
	}
	
	
	
	
}
