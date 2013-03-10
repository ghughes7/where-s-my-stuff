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
import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.thc.presenter.UserProfileActivity;
import edu.gatech.cs2340.thc.view.CreateNewItemActivity;


public class LoginActivity extends Activity {
	
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		//temporary code
		//UserCollection uc = new UserCollection();
		//user = uc.getUser();
		
		
		
	}
	
	
	//is called when login button is clicked
	public void goToItemProfile(View view){//supposed to go to user profile, will change later
		EditText name = (EditText)findViewById(R.id.username);//get name
        EditText password = (EditText)findViewById(R.id.password);//get password
        //needs to check if user is valid
		Intent intent = new Intent(this, UserProfileActivity.class);
		startActivity(intent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	
	/*      all temp code until database is running
	
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
		*/
		


}