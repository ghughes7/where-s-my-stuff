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
import edu.gatech.cs2340.thc.presenter.UserProfileActivity;
import edu.gatech.cs2340.thc.view.RegisterNewUserActivity;

/**
 * 
 * Login form
 * @author THC
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
		
		ad = new Admin("admin","adpass","admin@gmail.com",false, true);
		
		/*
		userCollection.eraseTextFile();
		
		User u = new User("tuna","tunap","tunae",false,false);
		User u2 = new User("tuna2","tunap2","tunae2",false,false);
		User u3 = new User("bacon","baconp","bacone",false,false);
		
		userCollection.addUser(u);
		userCollection.addUser(u3);
		userCollection.addUser(u2);
		userCollection.addUser(ad);
	
		ad.promoteToAdmin(userCollection.getUser(u.getEmail()), userCollection);
		
		
		
		User u2 = new User("tuna2","tunap2","tunae2",false,false);
		ad.lock(u2,userCollection);
		
		//for testing purposes
		Context mContext = getApplicationContext();
		Intent i = new Intent(mContext, ShowUsersActivity.class);
		startActivity(i);
		
		*/
		
		
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
	
	
	
	//is called when login button is clicked
	public void validateLogin(View view){//supposed to go to user profile, will change later
		
		EditText email = (EditText)findViewById(R.id.username);//get name
        EditText password = (EditText)findViewById(R.id.password);//get password
    
        security.findUser(email.getText().toString());
        
        
        if(security.isUserLocked()){
        	//show message that they are locked
        	showLockedUserDialog();
        }
      //if valid user
        else if(security.checkMatch(email.getText().toString(), password.getText().toString())){
        	User user = userCollection.getUser(email.getText().toString());
        	
        	if(user.getIsAdmin()){//launch the admin profile page
        		Intent intent = new Intent(this, AdminProfileActivity.class);
        		//intent.putExtra("user",user);
        		startActivity(intent);
        	}
        	else{
        		 Intent intent = new Intent(this, UserProfileActivity.class);
                 intent.putExtra("user", user);//pass in the already existing UserCollection
                 startActivity(intent);
        		
        	}
           
        	 
        }
        
        //bad login, increase attempt
        else{
            security.checkAttempts(email.getText().toString(), password.getText().toString());
            
            //Log.d("email",email.getText().toString());
            if(security.getIsLocked()){
                TextView textview = new TextView(this);
                textview.setText("Locked Out. Restart app to log in again.");
                setContentView(textview);
            }
            showIncorrectAttemptDialog();
        }
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	
	
	
	
	/*      
	 * shows user is locked out 
	 */
	public void showLockedUserDialog(){
		 AlertDialog alertDialog = new AlertDialog.Builder(this).create(); 
				alertDialog.setMessage("Locked Out. Admin needs to unlock your account");
				alertDialog.setButton(-3,"OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
					
				}
			});     	
         
		 
		 	
		alertDialog.show();
	}
	
	/*      
	 * shows incorrect attempt box
	 */
	public void showIncorrectAttemptDialog(){
		 AlertDialog alertDialog = new AlertDialog.Builder(this).create(); 
				alertDialog.setMessage("Wrong email and/or password. Try again");
				alertDialog.setButton(-3,"OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
					
				}
			});     	
        
		 
		 	
		alertDialog.show();
	}
	
	
}
