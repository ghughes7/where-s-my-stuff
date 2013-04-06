package edu.gatech.cs2340.thc.presenter;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.model.ItemCollection;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.thc.view.CreateNewItemActivity;
import edu.gatech.cs2340.thc.view.ShowUsersActivity;
import edu.gatech.cs2340.triggerhappycoders.R;

/**
 * Displays the main user information (name, picture, items)
 * 
 * @author Trigger Happy Coders
 * 
 */
public class UserProfileFragment extends Fragment {

	private User user;
	//private UserCollection uc;
	private ArrayList<Item> itemList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_test, container, false);
	}

	@Override
	/**
	 * This method is how a fragment starts an activity 
	 */
	public void onStart() {
		super.onStart();

		Intent intent = getActivity().getIntent();
		user = (User) intent.getSerializableExtra("user");
		/*uc = new UserCollection(getActivity());
		user = uc.getUser("user1@gmail.com");*/

		ItemCollection ic = new ItemCollection(getActivity(), user); // creates the item collection with three pre-made items
		// gets items info
		itemList = ic.getItemsOfUser(user.getEmail());

		// sets the information for the user to display
		TextView nameText = (TextView) getView().findViewById(R.id.fullNameText);
		nameText.setText(user.getName());
		// TextView phoneText = (TextView) findViewById(R.id.phoneNumberText);
		// phoneText.setText(user.getPhone());
		TextView emailText = (TextView) getView().findViewById(R.id.emailAddressText);
		emailText.setText(user.getEmail());

		if (itemList != null) {
			StringBuilder itemsString = new StringBuilder();
			// Log.d("itemList size", Integer.toString(itemList.size()));
			for (int i = 0; i < itemList.size(); i++) {
				itemsString.append(itemList.get(i).getItemName());
				itemsString.append("\n");
			}

			EditText itemList = (EditText) getView().findViewById(R.id.itemList);// display items' info
			itemList.setText(itemsString);

		}

		// creates the action when the Add Item button is clicked
		Button add = (Button) getView().findViewById(R.id.addItem);
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			/**
			 * This method launches item profile so that users can create new items
			 * 
			 * @param v - View The view that was clicked within the ListView  
			 */
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// when button is clicked, go to addItemScreen
				Intent addItemScreen = new Intent(getActivity().getApplicationContext(), CreateNewItemActivity.class);
				addItemScreen.putExtra("user", user);// pass in the already existing  UserCollection
				startActivity(addItemScreen);
			}
		});
		
		Button userList = (Button)getView().findViewById(R.id.listUser);
		userList.setOnClickListener(new View.OnClickListener() {
			@Override
			/**
			 * This method launches item profile so that users can create new items
			 * 
			 * @param v - View The view that was clicked within the ListView  
			 */
			public void onClick(View v) {
				//when button is clicked, the screen changes
				Intent addItemScreen = new Intent(getActivity().getApplicationContext(), 
						ShowUsersActivity.class);
				addItemScreen.putExtra("user", user);
				startActivity(addItemScreen);
			}
		});
		
		Button itemList = (Button)getView().findViewById(R.id.listItem);
		itemList.setOnClickListener(new View.OnClickListener() {
			@Override
			
			/**
			 * This method launches item profile so that users can create new items
			 * 
			 * @param v - View The view that was clicked within the ListView  
			 */
			public void onClick(View v) {
				//when button is clicked, the screen changes
				Intent addItemScreen = new Intent(getActivity().getApplicationContext(), 
						UserItemsActivity.class);
				addItemScreen.putExtra("user", user);
				startActivity(addItemScreen);
			}
		});	
		
	}
}
