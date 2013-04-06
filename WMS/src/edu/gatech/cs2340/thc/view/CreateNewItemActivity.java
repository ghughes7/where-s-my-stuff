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
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Creates a new item activity
 * @author Trigger Happy Coders
 *
 */
public class CreateNewItemActivity extends Activity {

	private RadioGroup radioTypeGroup;
	private RadioButton radioTypeButton;
	private ItemCollection itemCol;
	private RadioGroup radioCTypeGroup;
	private RadioButton radioCTypeButton;
	private DBController dh;
	static final int DIALOG_ID = 0;
	private User user;

	@Override
	/**
	 * This method is the default android material
	 * 
	 * @param savedInstanceState - Bundle  
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_new_item);
		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");
		itemCol = new ItemCollection(this, user);
	}

	/**
	 * This method is used when the user clicks the "create new item" button
	 * 
	 * @param view
	 *            - View The view that was clicked within the ListView
	 */
	public void createNewItem(View View) {

		// get all info

		// get the radio button selected
		String type = "";
		radioTypeGroup = (RadioGroup) findViewById(R.id.typeRadio);
		int selectedId = radioTypeGroup.getCheckedRadioButtonId();// get the
																	// selected
																	// radio
																	// button id
		radioTypeButton = (RadioButton) findViewById(selectedId);// find the
																	// actual
																	// button
																	// selected
		if (radioTypeButton.getText().equals("lost")) {
			type = "Lost";

		} else {
			type = "Found";

		}

		String categoryString = "";
		radioCTypeGroup = (RadioGroup) findViewById(R.id.categoryRadio);
		int selectedCategoryId = radioCTypeGroup.getCheckedRadioButtonId();// get
																			// the
																			// selected
																			// radio
																			// button
																			// id
		radioCTypeButton = (RadioButton) findViewById(selectedCategoryId);// find
																			// the
																			// actual
																			// button
																			// selected
		if (radioCTypeButton.getText().equals("Keepsake")) {
			categoryString = "Keepsake";
		} else if (radioCTypeButton.getText().equals("Heirloom")) {
			categoryString = "Heirloom";
		} else {
			categoryString = "Misc";
		}

		EditText itemName = (EditText) findViewById(R.id.nameField);// get name
		EditText reward = (EditText) findViewById(R.id.rewardField);// get
																	// reward
		EditText itemDes = (EditText) findViewById(R.id.itemDesField);// get
																		// item
																		// description

		String itemString = itemName.getText().toString();
		String rewardString = reward.getText().toString();
		String itemDesString = itemDes.getText().toString();

		EditText date = (EditText) findViewById(R.id.dateField);
		EditText location = (EditText) findViewById(R.id.locationField);

		String dateString = date.getText().toString();
		String locationString = location.getText().toString();

		Item item = new Item(itemString, itemDesString, rewardString, type,
				dateString, categoryString, locationString, user.getEmail());
		itemCol.addItem(item);

		// Adds to the database
		this.dh = new DBController(this);
		this.dh.insert(itemString, itemDesString, locationString,
				categoryString, rewardString, type, dateString, user.getEmail());

		showDialog(DIALOG_ID);

		Intent intent = new Intent(this, TabsActivity.class);// go to item
																// profile page
		intent.putExtra("itemObj", item);// pass in the ItemProfile object, the
											// string can be named anything
		intent.putExtra("user", user);
		startActivity(intent);// go to the item profile page activity
	}

	/**
	 * This method displays a pop up window notifying the user that the item is
	 * added
	 * 
	 * @param id
	 *            - int The row id of the item that was clicked
	 */
	protected final Dialog onCreateDialog(final int id) {
		Dialog dialog = null;
		switch (id) {
		case DIALOG_ID:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Item added.")
					.setCancelable(false)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									CreateNewItemActivity.this.finish();
								}
							})
					.setNegativeButton("Back",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
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

	
	/**
	 * This method is used if the user clicks the "cancel" button
	 * 
	 * @param view
	 *            - View The view that was clicked within the ListView
	 */
	public void goToLoginPage(View view) {

		Intent intent = new Intent(this, TabsActivity.class);// go back to login
																// page
		startActivity(intent);
	}

	@Override
	/**
	 * This method adds items to the action bar if it is present
	 * 
	 * @param menu - Menu inflatable menu
	 * @return true
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
