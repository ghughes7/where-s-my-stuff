package edu.gatech.cs2340.triggerhappycoders;

import edu.gatech.cs2340.thc.view.CreateNewItemActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * This is the main activity. It is called when the android application is launched.
 * 
 * @author circusburger63
 * @version 1.0
 */
public class LoginActivity extends Activity {

	/**
	 * Default activity stuff
	 * @param savedInstanceState - this is the saved data that can be passed to another activity
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

	}
	
	
	/**
	 * //This is called when login button is clicked, some code required in the activity_login.xml
	 * @param view is required by Android to get the corresponding components in the xml file
	 */
	public void goToItemProfile(View view) {// supposed to go to user profile,
											// will change later
		EditText name = (EditText) findViewById(R.id.username);// get name
		EditText password = (EditText) findViewById(R.id.password);// get password
																
		
		// needs to check if user is valid
		
		
		Intent intent = new Intent(this, CreateNewItemActivity.class);//go to create new item activity
		startActivity(intent);

	}

	/**
	 * We are not using this right now
	 * @return boolean - android made boolean 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
