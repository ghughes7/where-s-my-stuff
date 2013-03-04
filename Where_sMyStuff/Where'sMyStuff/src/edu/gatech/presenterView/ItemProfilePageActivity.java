package edu.gatech.presenterView;


import edu.gatech.TriggerHappyCoders.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;

public class ItemProfilePageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_profile_page);
		
		
		displayInfo();
	}
	
	public void displayInfo(){
		Intent intent = getIntent();
		ItemProfile item = (ItemProfile)intent.getSerializableExtra("itemObj");//get the item from previous activity
		EditText editText = (EditText)findViewById(R.id.itemNameProfile);//get the item name text field
		editText.setText(item.getItemName());//add the new item's name into that text field
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
