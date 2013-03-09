package edu.gatech.cs2340.thc.presenter;


import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.view.ShowListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ItemProfilePageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_profile);
		
		
		displayInfo();
	}
	
	public void displayInfo(){
		Intent intent = getIntent();
		Item item = (Item)intent.getSerializableExtra("itemObj");//get the item obj from previous activity
		
		EditText itemText = (EditText)findViewById(R.id.info);//get the item name text field
		
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Item Name: " + item.getItemName() + "\n" + "Item Description: " + item.getItemDes() + "\n" +
		"Reward: " + item.getReward() + "\n" + "Type: " +  item.returnType());
		
		itemText.setText(stringBuffer);//add the new item's name into that text field
		
		/*
		EditText itemText = (EditText)findViewById(R.id.itemNameProfile);//get the item name text field
		itemText.setText(item.getItemName());//add the new item's name into that text field
		EditText descriptionText = (EditText)findViewById(R.id.itemDesProfile);
		descriptionText.setText(item.getItemDes());
		EditText catagoryText = (EditText)findViewById(R.id.catagory);
		catagoryText.setText(item.returnCatagory());
		EditText rewardText = (EditText)findViewById(R.id.rewardProfile);
		rewardText.setText(item.getReward());
		EditText typeText = (EditText)findViewById(R.id.typeProfile);
		typeText.setText(item.returnType());
		EditText dateText = (EditText)findViewById(R.id.dateCreated);
		dateText.setText(item.returnDate());
		EditText statusText = (EditText)findViewById(R.id.status);
		statusText.setText(item.getStatus());
		*/
		
	}
	
	public void goToItemList(View view){
		Intent intent = new Intent(this, ShowListActivity.class);//go to item list page
		startActivity(intent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
