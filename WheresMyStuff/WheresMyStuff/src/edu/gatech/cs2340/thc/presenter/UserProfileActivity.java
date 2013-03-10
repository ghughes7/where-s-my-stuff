
package edu.gatech.cs2340.thc.presenter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.model.ItemCollection;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.thc.view.CreateNewItemActivity;
import edu.gatech.cs2340.triggerhappycoders.R;

public class UserProfileActivity extends Activity {
	
	private User user;
	private Item item1, item2, item3;
	private UserCollection uc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		
		uc = new UserCollection();	//creates the user collection with the pre-made user
		user = uc.getUser("user1@gmail.com");	//gets the user's info
		
		
			ItemCollection ic = new ItemCollection();	//creates the item collection with three pre-made items
			//gets items info
			item1 = ic.getItem(user.getName());
			item2 = ic.getItem(user.getName());		
			
			//sets the information for the user to display
			TextView nameText = (TextView) findViewById(R.id.fullNameText);
			nameText.setText(user.getName());
			//TextView phoneText = (TextView) findViewById(R.id.phoneNumberText);
			//phoneText.setText(user.getPhone());
			TextView emailText = (TextView) findViewById(R.id.emailAddressText);
			emailText.setText(user.getEmail());
			
			//gets the button for the items and sets them up if an item exists
			Button firstItem = (Button)findViewById(R.id.firstItem);
			if(item1.getItemName() != null){
				firstItem.setText(item1.getItemName());
				//firstItem.setBackgroundResource(R.drawable.login_button);
			}
			else
				firstItem.setText("");
			
			Button secondItem = (Button)findViewById(R.id.secondItem);
			if(item2.getItemName() != null){
				secondItem.setText(item2.getItemName());
				//secondItem.setBackgroundResource(R.drawable.login_button);
			}
			else
	 			secondItem.setText("");
			
	 		
			
	 		//creates the action when the Add Item button is clicked
			Button add = (Button)findViewById(R.id.addItem);
			add.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//when button is clicked, the screen changes
					Intent addItemScreen = new Intent(getApplicationContext(), CreateNewItemActivity.class);
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
