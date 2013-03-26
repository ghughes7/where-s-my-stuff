package edu.gatech.cs2340.thc.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import edu.gatech.cs2340.thc.view.ShowUsersActivity;
import edu.gatech.cs2340.triggerhappycoders.R;

public class AdminProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_profile);
	}
	
	/*
	 * these methods are called when the buttons are clicked 
	 */
	public void createAdmin(View view){	
		goToUserList();
	}
	public void remove(View view){
		goToUserList();
	}
	public void lock(View view){
		goToUserList();
	}
	public void unlock(View view){
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
