package edu.gatech.cs2340.thc.model;

public class User {
	
	private String name;
	private String password;
	private String email;
	
	public User(String name, String password,String email){
		this.name = name;
		this.password = password;
		this.email = email;
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
	
}
