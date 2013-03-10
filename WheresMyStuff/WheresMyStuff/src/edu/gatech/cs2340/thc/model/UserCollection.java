package edu.gatech.cs2340.thc.model;
import java.util.*;


public class UserCollection {
	
	private List<User> userArray;
	
	public UserCollection(){
		userArray = new ArrayList<User>();
		makeUpUsers();
	}
	
	public void makeUpUsers(){
		userArray.add(new User("user1","user1pw","user1@gmail.com"));
		userArray.add(new User("user2","user2pw","user2@gmail.com"));
		userArray.add(new User("user3","user3pw","user3@gmail.com"));
	}
	public User getUser(String email){
	        
/*	        for(int i = 0; i < userArray.size(); i++){
	            if(email.equals(userArray.get(i).getEmail())){
	                return userArray.get(i);    
	            }
	        }
	        return null;*/
		

		User tmp = userArray.get(0);
		for(int i =0; i < userArray.size(); i++){
			if(email.equals(userArray.get(i).getEmail())){
				tmp = userArray.get(i);	
			}
		}
		return tmp;
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