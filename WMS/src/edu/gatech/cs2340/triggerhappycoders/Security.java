package edu.gatech.cs2340.triggerhappycoders;

import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;

public class Security {
	private int attempts;
	private boolean isLocked;
	private UserCollection collection;
	private User user;

	public Security(String name) {
		attempts = 0;
		isLocked = false;
		// user = new User(name, password, email);
		collection = new UserCollection();
		// @SuppressWarnings("unused")
		user = collection.getUser(name);

	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public boolean getIsLocked() {
		return isLocked;
	}

	public void checkAttempts(String name, String password) { // increments
																// attempts when
																// the username
																// or password
																// is wrong
		// and when the attempts are not equal to 3
		if (!(user.getName().equals(name) || user.getPassword()
				.equals(password)) && attempts < 3) {
			attempts++;
		} else {
			// attempts = 3;
			if (user != null) {
				user.lockedUser();
			}
			isLocked = true;
		}
	}

	/*
	 * public void lockDown(){
	 * 
	 * AlertDialog alertDialog = new AlertDialog.Builder(this).create(); Button
	 * btn=(Button)findviewById(R.id.loginButton);
	 * 
	 * if(isLocked == true){ alertDialog.setMessage("Locked out nigga!!!");
	 * btn.setEnabled(false); } }
	 */
}
