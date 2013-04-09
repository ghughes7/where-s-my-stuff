package edu.gatech.cs2340.thc.view;


import java.io.FileOutputStream;
import java.io.IOException;

import edu.gatech.cs2340.triggerhappycoders.DBController;
import edu.gatech.cs2340.triggerhappycoders.LoginActivity;
import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.model.ItemCollection;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.presenter.ItemProfilePageActivity;
import edu.gatech.cs2340.thc.presenter.TabsActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CreateNewItemActivity extends Activity {
	
	private RadioGroup radioTypeGroup;
	private RadioButton radioTypeButton;
	private ItemCollection itemCol;
	private RadioGroup radioCTypeGroup;
	private EditText date;
	private RadioButton radioCTypeButton;
	private DBController dh;
	static final int DIALOG_ID = 0;
	private User user; 
	private String dateString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_new_item);
		Intent intent = getIntent();
		user = (User)intent.getSerializableExtra("user");
		dateString = (String)intent.getSerializableExtra("dateString");
		
		date = (EditText)findViewById(R.id.dateField);
		if(dateString != null){//if user has added a date, then display it
			if(dateString != null){
				date.setText(dateString);
			}
		}
		
		itemCol = new ItemCollection(this, user);
	}
	
	//if user click "create new item" button
	public void createNewItem(View View){
		
		//get all info
		
		//get the radio button selected
		String type = "";
		radioTypeGroup = (RadioGroup) findViewById(R.id.typeRadio);
		int selectedId = radioTypeGroup.getCheckedRadioButtonId();//get the selected radio button id
		radioTypeButton = (RadioButton) findViewById(selectedId);//find the actual button selected
		if(radioTypeButton.getText().equals("lost")){
			type = "Lost";
			
		}
		else{
			type = "Found";
			
		}
		Log.d("type", type);
		String categoryString = "";
		radioCTypeGroup = (RadioGroup) findViewById(R.id.categoryRadio);
		int selectedCategoryId = radioCTypeGroup.getCheckedRadioButtonId();//get the selected radio button id
		radioCTypeButton = (RadioButton) findViewById(selectedCategoryId);//find the actual button selected
		if(radioCTypeButton.getText().equals("Keepsake")){
			categoryString = "Keepsake";
		}
		else if(radioCTypeButton.getText().equals("Heirloom")){
			categoryString = "Heirloom";
		}
		else{
			categoryString = "Misc";
		}
		
	
		EditText itemName = (EditText)findViewById(R.id.nameField);//get name
		EditText reward = (EditText)findViewById(R.id.rewardField);//get reward
		EditText itemDes = (EditText)findViewById(R.id.itemDesField);//get item description
	
		String itemString = itemName.getText().toString();
		String rewardString = reward.getText().toString();
		String itemDesString = itemDes.getText().toString();
		
		
		
		EditText location = (EditText)findViewById(R.id.locationField);
		
		String dateString = date.getText().toString();
		String locationString = location.getText().toString();
		
		Item item = new Item(itemString, itemDesString, rewardString, type, dateString, categoryString,locationString, user.getEmail());
		itemCol.addItem(item);
		
		//Adds to the database
		this.dh = new DBController(this);
		this.dh.insert(itemString, itemDesString, locationString, 
						categoryString, rewardString, type, dateString, user.getEmail());
				
		showDialog(DIALOG_ID);
		
		Intent intent = new Intent(this, TabsActivity.class);//go to item profile page
		intent.putExtra("itemObj",item);//pass in the ItemProfile object, the string can be named anything
		intent.putExtra("user",user);
		startActivity(intent);//go to the item profile page activity
	}
	
	//Displays a pop up window notifying the user that the item is added
		protected final Dialog onCreateDialog(final int id) {
			Dialog dialog = null;
			switch(id) {
			case DIALOG_ID:
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Item added.")
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						CreateNewItemActivity.this.finish();
	              }
				})
				.setNegativeButton("Back", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						CreateNewItemActivity.this.finish();
					}
				});
				AlertDialog alert = builder.create(); 
				dialog = alert;
				break;
			default:
			}
			return dialog;
		}
	
	//if user click "cancel" button
	public void goToLoginPage(View view){
		
		Intent intent = new Intent(this, TabsActivity.class);//go back to login page
		startActivity(intent);
		
	}
	
	//when user clicks on "date" button
	public void getDate(View view){
		Intent intent = new Intent(this, DatePickerActivity.class);
		//need to pass the user forward, so it can be pass back, for persistence purposes 
		intent.putExtra("user",user);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
