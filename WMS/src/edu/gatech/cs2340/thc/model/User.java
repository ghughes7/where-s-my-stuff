package edu.gatech.cs2340.thc.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	
	private String name;
	private String password;
	private String email;
	private boolean isLocked;
	
	public User(String name, String password,String email){
		this.name = name;
		this.password = password;
		this.email = email;
		isLocked = false;
	}
	
	public void setName(String n){
		name = n;
		
	}
	public void setPassword(String p){
		password = p;
		
	}
	
	public void setEmail(String e){
		email = e;
		
	}
	
	public String getName(){
		return name;
	}
	public String getPassword(){
		return password;
		
	}
	public String getEmail(){
		return email;
		
	}
	
	public void setUser(){
		isLocked = true;
		
	}
	
	public void lockedUser(){
        isLocked = true;
        
    }
    
    public void unLockedUser(){
        isLocked = false;
        
    }
    public boolean getLockedStatus(){
        return isLocked;
        
    }
	
	
}