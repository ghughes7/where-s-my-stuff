package edu.gatech.cs2340.thc.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.presenter.ItemProfilePageActivity;
import edu.gatech.cs2340.thc.presenter.UserProfileActivity;
import edu.gatech.cs2340.triggerhappycoders.DBController;
import edu.gatech.cs2340.triggerhappycoders.R;

public class CreateNewItemActivity extends Activity {
	
	private User user;
	private DBController dh;
	static final int DIALOG_ID = 0;
	private RadioGroup radioTypeGroup;
	private RadioButton radioTypeButton;
	private RadioGroup radioCTypeGroup;
	private RadioButton radioCTypeButton;
	//ItemCollection itemCol = new ItemCollection();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_new_item);
		//Gets the user
		Intent intent = getIntent();
		user = (User)intent.getSerializableExtra("user");
	}
	
	//if user click "create new item" button
	@SuppressWarnings("deprecation")
	public void createNewItem(View View){
		
		//get all info	
		//get the radio button selected
		String type = "";
		radioTypeGroup = (RadioGroup) findViewById(R.id.typeRadio);
		int selectedId = radioTypeGroup.getCheckedRadioButtonId();//get the selected radio button id
		radioTypeButton = (RadioButton) findViewById(selectedId);//find the actual button selected
		if(radioTypeButton.getText().equals("lost")){
			type = "lost";
		}
		else{
			type = "found";
		}
		
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
	
		EditText name = (EditText)findViewById(R.id.nameField);//get name
		EditText reward = (EditText)findViewById(R.id.rewardField);//get reward
		EditText description = (EditText)findViewById(R.id.itemDesField);//get item description
		EditText date = (EditText)findViewById(R.id.dateField);
		EditText location = (EditText)findViewById(R.id.locationField);
		
		String nameString = name.getText().toString();
		String rewardString = reward.getText().toString();
		String descriptionString = description.getText().toString();
		String dateString = date.getText().toString();
		String locationString = location.getText().toString();
		
		/*Item item = new Item(itemString, itemDesString, rewardString, type, 
				dateString, categoryString, user.getEmail());
		*/
		
		//Adds to the database
		this.dh = new DBController(this);
		this.dh.insert(nameString, descriptionString, locationString, 
				categoryString, rewardString, type, dateString, user.getEmail());
		
		showDialog(DIALOG_ID);
		
		//add code to put item into item collection then database
		//addToTextFile(item);//save to text file
		Intent intent = new Intent(this, ItemProfilePageActivity.class);//go to item profile page
		intent.putExtra("user",user);//pass in the ItemProfile object, the string can be named anything
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
	public void goToUserProfile(View view){
		Intent intent = new Intent(this, UserProfileActivity.class);//go back to login page
		startActivity(intent);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}