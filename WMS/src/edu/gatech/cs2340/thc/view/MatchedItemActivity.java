package edu.gatech.cs2340.thc.view;

import java.util.ArrayList;

import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.model.ItemCollection;
import edu.gatech.cs2340.thc.model.MatchItems;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.triggerhappycoders.R.layout;
import edu.gatech.cs2340.triggerhappycoders.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MatchedItemActivity extends ListActivity {
	
	private Item item;
	private Item chosenItem;
	private User user;
	private MatchItems matchItems;
	private ItemCollection itemCollection;
	private ArrayList<Item> matchedItemsArray;
	private int itemIndex;
			
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_matched_items);
		
		
		Intent intent = getIntent();
		user = (User)intent.getSerializableExtra("user");
		item = (Item) intent.getSerializableExtra("item");//item to be matched
		
		itemCollection = new ItemCollection(this, user);
		
		matchItems = new MatchItems(itemCollection, item);
	
		
		EditText itemName = (EditText) findViewById(R.id.itemName);
		itemName.setText("ITEM NAME: " + item.getItemName());//display item's name
		
		matchItems.matchTheItem();
		
		matchedItemsArray = matchItems.getMatchItemsArray();//gets the array of the item's matched items
		
		String [] stringItemArray = new String[matchedItemsArray.size()];//puts it into a string array for displaying purposes 
		String tempString;
		
		for(int i = 0; i < matchedItemsArray.size(); i++){
			tempString = "Item Name: " + matchedItemsArray.get(i).getItemName() + "\n"
					+ "Item Description: " + matchedItemsArray.get(i).getItemDes() + "\n"
					+ "location: " + matchedItemsArray.get(i).getLocation()+ "\n"
					+"Type: " +  matchedItemsArray.get(i).getType()+ "\n"
					+"Owner's Email: " +  matchedItemsArray.get(i).getOwner()+ "\n";
			stringItemArray[i] = tempString;
		}
		
		if(matchedItemsArray.size() == 0){//if no matched items
			stringItemArray = new String[1]; 
			stringItemArray[0] = "No matched items found :(";
			
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
		(this,android.R.layout.simple_list_item_1, stringItemArray);
		this.setListAdapter(adapter);
		
		
	}
	
	public void onListItemClick(ListView parent, View v, int position, long id) {
		super.onListItemClick(parent, v, position, id);
		itemIndex = position;
		new AlertDialog.Builder(this)
		.setTitle("Matched Item:")
		.setMessage("" + getListView().getItemAtPosition(position))
		.setPositiveButton("Click To Confirm Matched Item", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				chosenItem = matchedItemsArray.get(itemIndex);
				confirmItems();
			}
		})
		.show();
	}
	
	public void confirmItems(){
		Intent i = new Intent(this, ConfirmMatchedItemActivity.class);
		i.putExtra("user", user);
		i.putExtra("matched item",chosenItem);//matched item
		i.putExtra("lost item",item);//their lost item
		startActivity(i);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.matched_item, menu);
		return true;
	}

}
