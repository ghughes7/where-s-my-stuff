package edu.gatech.cs2340.thc.model;

public class User {
	
	protected String name;
	protected String password;
	protected String email;
	protected boolean elevatedStatus;//this is true for Admin - coherent with Liskov Substitution Property
	
	public User(String name, String password,String email){
		this.name = name;
		this.password = password;
		this.email = email;
		elevatedStatus = false;
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
	
	public boolean getelevatedStatus() {
		return elevatedStatus;
	}
	
}
