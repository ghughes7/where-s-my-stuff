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

public class AdminProfileActivity extends Activity {
	Admin ad;
	EditText email;
	UserCollection uc;
	User target;
	String targetEmail = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_profile);
		ad = new Admin("admin", "adpass", "admin@gmail.com", false, true);
		email = (EditText) findViewById(R.id.email);//get email
		uc = new UserCollection(this);
		target = new User ("default","default","default",false,false);
	}
	
	/*
	 * these methods are called when the buttons are clicked 
	 */
	public void createAdmin(View view){
		uc = new UserCollection(this);
		targetEmail = email.getText().toString();
		target = uc.getUser(targetEmail);
		ad.promoteToAdmin(target, uc);
		goToUserList();
	}
	public void demoteAdmin(View view){
		uc = new UserCollection(this);
		targetEmail = email.getText().toString();
		target = uc.getUser(targetEmail);
		ad.demoteToUser(target, uc);
		goToUserList();
	}
	public void remove(View view){
		uc = new UserCollection(this);
		targetEmail = email.getText().toString();
		target = uc.getUser(targetEmail);
		ad.removeUser(target, uc);		
		goToUserList();
	}
	public void lock(View view){
		uc = new UserCollection(this);
		targetEmail = email.getText().toString();
		target = uc.getUser(targetEmail);
		ad.lock(target, uc);
		goToUserList();
	}
	public void unlock(View view){
		uc = new UserCollection(this);
		targetEmail = email.getText().toString();
		target = uc.getUser(targetEmail);
		ad.unLock(target, uc);
		goToUserList();
	}
	public void showUserList(View view) {
		goToUserList();
	}
	
	public void goToUserList(){
		Intent intent = new Intent(this, ShowUsersActivity.class);//go to item profile page
		startActivity(intent);//go to the item profile page activity
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_profile, menu);
		return true;
	}

}