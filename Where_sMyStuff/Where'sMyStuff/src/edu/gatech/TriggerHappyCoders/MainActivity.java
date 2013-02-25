package edu.gatech.TriggerHappyCoders;

import edu.gatech.User.UserCollection;
import edu.gatech.User.User;
import android.widget.Button;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;



public class MainActivity extends Activity {
	
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
