package edu.gatech.User;

/**
 * Creates a user class where user has a name, email, and password.
 */
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
