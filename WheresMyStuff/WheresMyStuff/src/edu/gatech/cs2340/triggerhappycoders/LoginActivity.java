package edu.gatech.cs2340.triggerhappycoders;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.thc.view.CreateNewItemActivity;
import edu.gatech.cs2340.thc.view.RegisterNewUserActivity;

/**
 * Login form
 * @author THC
 *
 */
public class LoginActivity extends Activity {
	
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		// Handles the user
		UserCollection uc = new UserCollection();
		user = uc.getUser();
		
		TextView registerScreen = (TextView) findViewById(R.id.textView3);		

        // When the user clicks "Log In" 
        Button login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Validates if login info is correct
				EditText name = (EditText)findViewById(R.id.reg_email);
				EditText password = (EditText) findViewById(R.id.reg_password);
				showDialog(name.getText().toString(), password.getText().toString());
			}
		});
		
		// When the user clicks "Sign up" changes to registration screen
		registerScreen.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), RegisterNewUserActivity.class);
				startActivity(i);
			}
		});	
	}
	
	/*//is called when login button is clicked
	public void goToItemProfile(View view){//supposed to go to user profile, will change later
		EditText name = (EditText)findViewById(R.id.username);//get name
        EditText password = (EditText)findViewById(R.id.password);//get password
        //needs to check if user is valid
        showDialog(name.getText().toString(), password.getText().toString());
		Intent intent = new Intent(this, CreateNewItemActivity.class);
		startActivity(intent);
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	
	/*      
	 * Pop up dialogue letting the user know if login is successful
	 */
	public void showDialog(String name, String password){
		 AlertDialog alertDialog = new AlertDialog.Builder(this).create(); 
		 // If info valid, login successful
		 if(user.getName().equals(name) && user.getPassword().equals(password)){
				alertDialog.setMessage("Login successful");
				alertDialog.setButton(-3,"OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				}
			});     	
         }
		 // If info invalid, login failed
		 else{
				alertDialog.setMessage("Login Failed");
				alertDialog.setButton(-3,"OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				}
			});
			 
		}	
		alertDialog.show();
	}
}
