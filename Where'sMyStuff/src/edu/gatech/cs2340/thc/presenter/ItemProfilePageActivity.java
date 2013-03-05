package edu.gatech.cs2340.thc.presenter;


import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.thc.model.Item;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;

/**
 * 
 * @author circusburger63
 * This shows the item profile page 
 */
 
public class ItemProfilePageActivity extends Activity {

	//auto generated	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_profile_page);
		
		
		displayInfo();//after the user adds a new item, this will display the item info on this activity
	}
	
	public void displayInfo(){
		Intent intent = getIntent();//need new intent obj
		Item item = (Item)intent.getSerializableExtra("itemObj");//get the item from previous activity 
		
		
		EditText editText = (EditText)findViewById(R.id.itemNameProfile);//get the item name text field
		editText.setText(item.getItemName());//add the new item's name into that text field
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
