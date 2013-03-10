package edu.gatech.cs2340.thc.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.triggerhappycoders.R;

/**
 * Registration form 
 * @author THC
 *
 */
public class RegisterNewUserActivity extends Activity{
	private User person;
	private UserCollection uc = new UserCollection();
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		// While on Register screen the user clicks login
		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
		loginScreen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Switching to Login Screen/closing register screen
				finish();
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
				showDialog(name.getText().toString(), email.getText()
						.toString(), password.getText().toString());
			}
		});
		
	}
	
/*	public void validateRegistration(View view){
		EditText name = (EditText) findViewById(R.id.reg_name);
		EditText password = (EditText) findViewById(R.id.reg_password);
		EditText email = (EditText) findViewById(R.id.reg_email);
		showDialog(name.getText().toString(), email.getText()
				.toString(), password.getText().toString());
	}*/

	/*
	 * Pop up dialogues that tell the user if registration is successful
	 */
	public void showDialog(String name, String password, String email) {
		
		
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		
		// If email is already in the User Collection don't allow registration
		//if(u.getEmail() == uc.getUser(email).getEmail()){
		
		if(email.equals(uc.getUser(email).getEmail())){
			alertDialog.setMessage("Email already exists.");
			alertDialog.setButton(-3, "OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// here you can add functions
						}
					});
		}
		
		else{
			alertDialog.setMessage("Registration Successful");
			User u = new User(name, password, email);
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