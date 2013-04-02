
package edu.gatech.cs2340.thc.presenter;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.model.ItemCollection;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.thc.view.CreateNewItemActivity;
import edu.gatech.cs2340.triggerhappycoders.R;

public class UserProfileActivity extends Activity {
	
	private User user;
	private ArrayList<Item> itemList;
	//private UserCollection uc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		
		Intent intent = getIntent();
		user = (User)intent.getSerializableExtra("user");
	
			ItemCollection ic = new ItemCollection(this, user);	//creates the item collection with three pre-made items
		
			
			//gets items info
				
			itemList = ic.getItemsOfUser(user.getEmail());
			
				//sets the information for the user to display
				TextView nameText = (TextView) findViewById(R.id.fullNameText);
				nameText.setText(user.getName());
				//TextView phoneText = (TextView) findViewById(R.id.phoneNumberText);
				//phoneText.setText(user.getPhone());
				TextView emailText = (TextView) findViewById(R.id.emailAddressText);
				emailText.setText(user.getEmail());
				
		
				if(itemList != null){
					StringBuilder itemsString = new StringBuilder();
					//Log.d("itemList size", Integer.toString(itemList.size()));
					for(int i = 0; i < itemList.size(); i++){
						itemsString.append(itemList.get(i).getItemName());
						itemsString.append("\n");
					}
					
			
					EditText itemList = (EditText)findViewById(R.id.itemList);//display items' info
					itemList.setText(itemsString);
					
				}
				
			
	 		//creates the action when the Add Item button is clicked
			Button add = (Button)findViewById(R.id.addItem);
			add.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					//when button is clicked, go to addItemScreen
					Intent addItemScreen = new Intent(getApplicationContext(), CreateNewItemActivity.class);
					addItemScreen.putExtra("user", user);//pass in the already existing UserCollection
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
