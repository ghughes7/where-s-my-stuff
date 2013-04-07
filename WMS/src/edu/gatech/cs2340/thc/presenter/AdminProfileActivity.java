package edu.gatech.cs2340.thc.presenter;

import edu.gatech.cs2340.thc.model.Admin;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.thc.view.ShowUsersActivity;
import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.triggerhappycoders.R.layout;
import edu.gatech.cs2340.triggerhappycoders.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * This is main screen for Admins. It consists of only special admin privileges
 * (i.e. delete, lock, other users' accounts)
 * 
 * @author Trigger Happy Coders
 * @version 1.2
 * 
 */
public class AdminProfileActivity extends Activity {
	Admin ad;
	EditText email;
	UserCollection uc;
	User target;
	String targetEmail = "";

	/**
	 * Default Android Activity method
	 * 
	 * @param savedInstance
	 *            State - Bundle (of information that can be passed to and from
	 *            activities)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_profile);
		ad = new Admin("admin", "adpass", "admin@gmail.com", false, true);
		email = (EditText) findViewById(R.id.email);// get email
		uc = new UserCollection(this);
		target = new User("default", "default", "default", false, false);
	}

	/*
	 * these methods are called when the buttons are clicked
	 */
	/**
	 * This is a corresponding method to the AdminProfileActivity's XML file
	 * "activity_admin_profile". It takes the user's name that is typed into the
	 * text field and promotes that user to an Admin using methods from the
	 * Admin class.
	 * 
	 * @param view
	 *            - View Android stuff
	 */
	public void createAdmin(View view) {
		uc = new UserCollection(this);
		targetEmail = email.getText().toString();
		target = uc.getUser(targetEmail);
		ad.promoteToAdmin(target, uc);
		goToUserList();
	}

	/**
	 * This is a corresponding method to the AdminProfileActivity's XML file
	 * "activity_admin_profile". It takes the user's name that is typed into the
	 * text field and demotes an admin to a user via methods from the Admin
	 * class.
	 * 
	 * @param view
	 *            - View Android stuff
	 */
	public void demoteAdmin(View view) {
		uc = new UserCollection(this);
		targetEmail = email.getText().toString();
		target = uc.getUser(targetEmail);
		ad.demoteToUser(target, uc);
		goToUserList();
	}

	/**
	 * This is the corresponding method to the AdminProfileActivity's XML file
	 * "activity_admin_profile". It takes the user's name that is typed into the
	 * text field and deletes that user from the user collection using methods
	 * from the Admin class.
	 * 
	 * @param view
	 *            - View Android stuff
	 */
	public void remove(View view) {
		uc = new UserCollection(this);
		targetEmail = email.getText().toString();
		target = uc.getUser(targetEmail);
		ad.removeUser(target, uc);
		goToUserList();
	}

	/**
	 * This is the corresponding method to the AdminProfileActivity's XML file
	 * "activity_admin_profile". It takes the user's name that is typed into the
	 * text field and locks that user's account out of the system. The user is
	 * unable to log into the system until after the account has been unlocked
	 * by and administrator.
	 * 
	 * @param view
	 *            - View Android stuff
	 */
	public void lock(View view) {
		uc = new UserCollection(this);
		targetEmail = email.getText().toString();
		target = uc.getUser(targetEmail);
		ad.lock(target, uc);
		goToUserList();
	}

	/**
	 * This is the corresponding method to the AdminProfileActivity's XML file
	 * "activity_admin_profile". It takes the user's name that is typed into the
	 * text field and unlocks a user's account so that they can log back into
	 * the system.
	 * 
	 * 
	 * @param view
	 *            - View Android stuff
	 */
	public void unlock(View view) {
		uc = new UserCollection(this);
		targetEmail = email.getText().toString();
		target = uc.getUser(targetEmail);
		ad.unLock(target, uc);
		goToUserList();
	}

	/**
	 * Method that is called when "Show Users" button is clicked in the
	 * application
	 * 
	 * @param view
	 *            - View Android stuff
	 */
	public void showUserList(View view) {
		goToUserList();
	}

	/**
	 * Visually displays the user collection and each user's status.
	 * 
	 */
	public void goToUserList() {
		Intent intent = new Intent(this, ShowUsersActivity.class);// go to item
																	// profile
																	// page
		startActivity(intent);// go to the item profile page activity
	}

	/**
	 * Default Android implementation of Activities
	 * 
	 * @param menu
	 *            - Menu
	 * @return true
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_profile, menu);
		return true;
	}

}