package edu.gatech.cs2340.thc.presenter;


import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.view.ShowItemListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ItemProfilePageActivity extends Activity {

	private Item item;
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_profile);
		
		
		displayInfo();
	}
	
	public void displayInfo(){
		Intent intent = getIntent();
		item = (Item)intent.getSerializableExtra("itemObj");//get the item obj from previous activity
		user = (User)intent.getSerializableExtra("user");
		
		EditText itemText = (EditText)findViewById(R.id.info);//get the item name text field
		
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Item Name: " + item.getItemName() + "\n" + "Item Description: " + item.getItemDes() + "\n" +
		"Reward: " + item.getReward() + "\n" + "Type: " +  item.getType());
		
		itemText.setText(stringBuffer);//add the new item's name into that text field
	
		
	}
	
	
	public void goToItemList(View view){
		Intent intent = new Intent(this, TabsActivity.class);//go to item list page
		intent.putExtra("user", user);
		startActivity(intent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
