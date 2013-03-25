package edu.gatech.cs2340.thc.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.view.CreateNewItemActivity;
import edu.gatech.cs2340.thc.view.ShowListActivity;
import edu.gatech.cs2340.thc.view.ShowUsersActivity;
import edu.gatech.cs2340.triggerhappycoders.R;

public class UserProfileActivity extends Activity {
	
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		//gets the user
		Intent intent = getIntent();
		user = (User)intent.getSerializableExtra("user");
								
		//sets the information for the user to display
		TextView nameText = (TextView) findViewById(R.id.fullNameText);
		nameText.setText(user.getName());
		TextView emailText = (TextView) findViewById(R.id.emailAddressText);
		emailText.setText(user.getEmail());
					
	 	//creates the action when the Add Item button is clicked
		Button add = (Button)findViewById(R.id.addItem);
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//when button is clicked, the screen changes
				Intent addItemScreen = new Intent(getApplicationContext(), 
						CreateNewItemActivity.class);
				addItemScreen.putExtra("user", user);
				startActivity(addItemScreen);
			}
		});	
		
		Button itemList = (Button)findViewById(R.id.listItem);
		itemList.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//when button is clicked, the screen changes
				Intent addItemScreen = new Intent(getApplicationContext(), 
						ItemProfilePageActivity.class);
				addItemScreen.putExtra("user", user);
				startActivity(addItemScreen);
			}
		});	
		
		Button userList = (Button)findViewById(R.id.listUser);
		userList.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//when button is clicked, the screen changes
				Intent addItemScreen = new Intent(getApplicationContext(), 
						ShowUsersActivity.class);
				addItemScreen.putExtra("user", user);
				startActivity(addItemScreen);
			}
		});	
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}
}