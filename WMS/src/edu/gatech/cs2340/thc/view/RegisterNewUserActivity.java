package edu.gatech.cs2340.thc.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.thc.presenter.ItemProfilePageActivity;
import edu.gatech.cs2340.triggerhappycoders.LoginActivity;
import edu.gatech.cs2340.triggerhappycoders.R;

/**
 * Registration form
 * 
 * @author THC
 * 
 */
public class RegisterNewUserActivity extends Activity {

	private UserCollection uc;

	@Override
	/**
	 * This method is the default android material
	 * 
	 * @param savedInstanceState - Bundle  
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		uc = new UserCollection(this);

		// While on Register screen the user clicks login
		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
		loginScreen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Switching to Login Screen/closing register screen

				Context mContext = getApplicationContext();
				Intent i = new Intent();// go back to login
				i.setClass(mContext, LoginActivity.class);
				startActivity(i);
				// finish();
			}
		});

		// Shows if registration is successful
		Button registered = (Button) findViewById(R.id.btnRegister);
		registered.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText name = (EditText) findViewById(R.id.reg_name);
				EditText password = (EditText) findViewById(R.id.reg_password);
				EditText email = (EditText) findViewById(R.id.reg_email);
				showDialog(name.getText().toString(), password.getText()
						.toString(), email.getText().toString());
			}
		});

	}

	/*
	 * public void validateRegistration(View view){ EditText name = (EditText)
	 * findViewById(R.id.reg_name); EditText password = (EditText)
	 * findViewById(R.id.reg_password); EditText email = (EditText)
	 * findViewById(R.id.reg_email); showDialog(name.getText().toString(),
	 * email.getText() .toString(), password.getText().toString()); }
	 */

	/**
	 * This method uses pop up dialogues that tell the user if registration is
	 * successful
	 * 
	 * @param name
	 *            - String user's name
	 * @param password
	 *            - String user's password
	 * @param email
	 *            - String user's email
	 */
	public void showDialog(String name, String password, String email) {

		AlertDialog alertDialog = new AlertDialog.Builder(this).create();

		// If email is already in the User Collection don't allow registration
		// if(u.getEmail() == uc.getUser(email).getEmail()){

		if (uc.getUser(email) != null
				&& email.equals(uc.getUser(email).getEmail())) {
			alertDialog.setMessage("Email already exists.");
			alertDialog.setButton(-3, "OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// here you can add functions
						}
					});
		}

		else {
			alertDialog.setMessage("Registration Successful");
			User u = new User(name, password, email, false, false);
			uc.addUser(u);

			alertDialog.setButton(-3, "OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// here you can add functions

						}
					});
		}

		alertDialog.show();
	}
}