package edu.gatech.cs2340.triggerhappycoders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import edu.gatech.cs2340.thc.model.Admin;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.thc.presenter.AdminProfileActivity;
import edu.gatech.cs2340.thc.presenter.TabsActivity;
import edu.gatech.cs2340.thc.presenter.UserProfileFragment;
import edu.gatech.cs2340.thc.view.RegisterNewUserActivity;

/**
 * Shows a login page that allows a user to log in to the application
 * 
 * @author Trigger Happy Coders (23)
 * 
 */
public class LoginActivity extends Activity {

	private User user;
	private UserCollection userCollection;
	private Security security;
	Admin ad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		userCollection = new UserCollection(this);

		// ad = new Admin("admin","adpass","admin@gmail.com",false, true);
		// userCollection.addUser(ad);
		// userCollection.eraseTextFile();
		security = new Security(userCollection);

		// When the user clicks "Sign up" changes to registration screen
		TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
		registerScreen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Switching to Register screen
				Context mContext = getApplicationContext();
				Intent i = new Intent(mContext, RegisterNewUserActivity.class);
				startActivity(i);
			}
		});
	}

	/**
	 * This method is called when login button is clicked
	 * 
	 * @param view
	 *            - View supposed to go to the user
	 */
	public void validateLogin(View view) {// supposed to go to user profile,
											// will change later

		EditText email = (EditText) findViewById(R.id.username);// get name
		EditText password = (EditText) findViewById(R.id.password);// get
																	// password

		security.findUser(email.getText().toString());

		if (security.isUserLocked()) {
			// show message that they are locked
			showLockedUserDialog();
		}
		// if valid user
		else if (security.checkMatch(email.getText().toString(), password
				.getText().toString())) {
			User user = userCollection.getUser(email.getText().toString());
			if (user.getIsAdmin()) {// launch the admin profile page
				Intent intent = new Intent(this, AdminProfileActivity.class);
				// intent.putExtra("user",user);
				startActivity(intent);
			} else {
				Intent intent = new Intent(this, TabsActivity.class);
				intent.putExtra("user", user);// pass in the already existing
												// UserCollection
				startActivity(intent);
			}
		}
		// bad login, increase attempt
		else {
			security.checkAttempts(email.getText().toString(), password
					.getText().toString());
			// Log.d("email",email.getText().toString());
			if (security.getIsLocked()) {
				TextView textview = new TextView(this);
				textview.setText("Locked Out. Restart app to log in again.");
				setContentView(textview);
			}
			showIncorrectAttemptDialog();
		}
	}

	@Override
	/**
	 * This method adds items to the action bar if it is present
	 * 
	 * @param menu - Menu  
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	/**
	 * This method shows user is locked out
	 */
	public void showLockedUserDialog() {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog
				.setMessage("Locked Out. Admin needs to unlock your account");
		alertDialog.setButton(-3, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// here you can add functions

			}
		});
		alertDialog.show();
	}

	/**
	 * This method shows incorrect attempt box
	 */
	public void showIncorrectAttemptDialog() {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setMessage("Wrong email and/or password. Try again");
		alertDialog.setButton(-3, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
			}
		});
		alertDialog.show();
	}
}