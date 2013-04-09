package edu.gatech.cs2340.thc.view;

import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.model.ItemCollection;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.presenter.TabsActivity;
import edu.gatech.cs2340.thc.presenter.UserProfileFragment;
import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.triggerhappycoders.R.layout;
import edu.gatech.cs2340.triggerhappycoders.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ConfirmMatchedItemActivity extends Activity {
	
	private User user;
	private Item matchedItem;
	private Item lostItem;
	private ItemCollection itemCollection;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirm_matched_item);
		
		Intent intent = getIntent();
		user = (User)intent.getSerializableExtra("user");
		matchedItem = (Item)intent.getSerializableExtra("matched item");
		lostItem = (Item)intent.getSerializableExtra("lost item");
		
		itemCollection = new ItemCollection(this, user);
		
		EditText lostItemText = (EditText)findViewById(R.id.lostItem);
		EditText MatchedItemText = (EditText)findViewById(R.id.foundItem);
		
		lostItemText.setText(lostItem.toString());
		MatchedItemText.setText(matchedItem.toString());
	}
	
	//is called when confirm items is clicked, changes the item's type to "resolved"
	public void resolveItems(View view){
		
		Item newItem1 = new Item(lostItem.getItemName(),lostItem.getItemDes(),lostItem.getReward(),
				"Resolved",lostItem.getDate(),lostItem.getCatagory(),lostItem.getLocation(),
				lostItem.getOwner());//recreate lost item
		Item newItem2 = new Item(matchedItem.getItemName(),matchedItem.getItemDes(),matchedItem.getReward(),
				"Resolved",matchedItem.getDate(),matchedItem.getCatagory(),matchedItem.getLocation(),
				matchedItem.getOwner());//recreate found item
		
		itemCollection.deleteItem(lostItem);
		itemCollection.deleteItem(matchedItem);
		
		itemCollection.addItem(newItem1);
		itemCollection.addItem(newItem2);
		
		Intent intent = new Intent(this, TabsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent); 
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirm_matched_item, menu);
		return true;
	}

}
