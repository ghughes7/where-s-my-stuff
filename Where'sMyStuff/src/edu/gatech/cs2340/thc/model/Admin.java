package edu.gatech.cs2340.thc.model;
/**
 * Admin is a child of the User class. The only difference between Admins and Users
 * is that Admins have higher 
 *
 * @author galen
 * @version 1.0
 */
public class Admin extends User {
	
	public Admin(String name, String password, String email) {
		super(name, password, email);
		elevatedStatus = true;
	}
	/**
	 * Administrators have the ability to change their own status and the status of other users
	 * 
	 * @param elevatedStatus - differentiates normal users from super users
	 */
	public void setElevatedStatus(boolean elevatedStatus) {
		this.elevatedStatus = elevatedStatus;
	}
	
}
