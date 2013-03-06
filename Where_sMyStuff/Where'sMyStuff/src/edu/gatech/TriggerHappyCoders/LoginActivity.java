package edu.gatech.TriggerHappyCoders;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import edu.gatech.TriggerHappyCoders.R;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.thc.view.CreateNewItemActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;



public class LoginActivity extends Activity {
	
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
//		Gets the user from UserCollection
		UserCollection uc = new UserCollection();
		user = uc.getUser();
		
		
		
		Button login=(Button)findViewById(R.id.loginButton);
		login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.username);
                EditText password = (EditText)findViewById(R.id.password);
                showDialog(name.getText().toString(),password.getText().toString());
                /*
               
                */
            }
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	//     all temp code until database is running
	
	public void showDialog(String name, String password){
		 AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		 //User logs in with a matching user name and password
		 if(user.getName().equals(name) && user.getPassword().equals(password)){
				alertDialog.setMessage("Login successful");
				alertDialog.setButton(-3,"OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				}
			});
         	
         }
		 //User enters a user name and password that do not match
		 else{
				alertDialog.setMessage("Login failed");
				alertDialog.setButton(-3,"OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				}
			});
			 
		 }
		 alertDialog.show();
	}

}
