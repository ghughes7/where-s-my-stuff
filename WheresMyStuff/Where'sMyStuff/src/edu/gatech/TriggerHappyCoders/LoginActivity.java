package edu.gatech.TriggerHappyCoders;

import edu.gatech.TriggerHappyCoders.R;
import edu.gatech.User.User;
import edu.gatech.User.UserCollection;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class LoginActivity extends Activity {
	
	private User user;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        UserCollection uc = new UserCollection();
        user = uc.getUser();
        
        Button login=(Button)findViewById(R.id.link_to_login);
		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
                EditText name = (EditText)findViewById(R.id.reg_fullname);
                EditText password = (EditText)findViewById(R.id.reg_password);
                showDialog(name.getText().toString(),password.getText().toString());
            }
        
		});
		
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
			}
		});
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void showDialog(String name, String password){
		 AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		 
		 if(user.getName().equals(name) && user.getPassword().equals(password)){
				alertDialog.setMessage("login successful");
				alertDialog.setButton(-3,"OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				}
			});
         	
         }
		 else{
				alertDialog.setMessage("login fail!!!!");
				alertDialog.setButton(-3,"OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
				}
			});
			 
		 }
		
		
		alertDialog.show();
		
	}
}