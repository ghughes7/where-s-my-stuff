package edu.gatech.cs2340.thc.presenter;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.model.ItemCollection;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.view.DatePickerActivity;
import edu.gatech.cs2340.thc.view.MatchedItemActivity;
import edu.gatech.cs2340.triggerhappycoders.DBController;
import edu.gatech.cs2340.triggerhappycoders.R;

public class UserItemsActivity extends ListActivity {

	private User user;
	TextView selection;
	public int idToModify; 
	DBController db;
	private Item chosenItem;

	List<String[]> list = new ArrayList<String[]>();
	List<String[]> names2 = null ;
	String[] stg1;
	int itemIndex;
	ArrayList<Item> itemlist = new ArrayList<Item>();//temp stuff
	Item item;//temp stuff 
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		
		Button userProf = (Button)findViewById(R.id.userProf);
		userProf.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//when button is clicked, the screen changes
				Intent addItemScreen = new Intent(getApplicationContext(), 
						UserProfileFragment.class);
				addItemScreen.putExtra("user", user);
				startActivity(addItemScreen);
			}
		});	
		
		//Gets the database info
		db = new DBController(this);
	    names2 = db.selectAll();
	    
	    //Gets the user
	    Intent intent = getIntent();
	    user = (User)intent.getSerializableExtra("user");

		/*stg1=new String[names2.size()]; 
		int x=0;
		String stg;

		//Goes through the database
		for (String[] name : names2) {
			//Checks for a specific user
			if(name[8].equals(user.getEmail())){
				stg = "\n" + "Name: " + name[1] + "\n" + "Description: " + 
						name[2] + "\n" + "Location: " + name[3] + "\n" + 
						"Category: " + name[4] + "\n" + "Reward: " + name[5] + 
						"\n" + "Type: " + name[6] + "\n" + "Date: " + name[7] + 
						"\n" + "Owner: " + name[8] + "\n";				
				stg1[x]=stg;
				//we are making temp items and putting it into an array, so we can later
				//find the item that user pick to see matched items
				item =  new Item(name[1],name[2] ,name[5],name[6], name[7],name[4],name[3],name[8]);
				itemArray.add(item);
				x++;
			}
		}*/
	    
	    ItemCollection itemCollection = new ItemCollection(this,user);
	    itemlist = itemCollection.getItemsOfUser(user.getEmail());
	    String[] stg1 = new String[itemlist.size()];
	    
	    for(int i = 0; i < itemlist.size();i++){
	    	//Log.d("itemlist info",itemlist.get(i).toString());
	    	stg1[i] = itemlist.get(i).toString();
	    }
	    
	    
	    
		//Displays to the screen
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
			(this,android.R.layout.simple_list_item_1, stg1);
        this.setListAdapter(adapter);
		selection=(TextView)findViewById(R.id.selection);
	}      
	
	//Displays the clicked item at the top of the page
	public void onListItemClick(ListView parent, View v, int position, long id) {
		super.onListItemClick(parent, v, position, id);
		itemIndex = position;//for the index of the item that the user clicked on 
		new AlertDialog.Builder(this)
		.setTitle("Your Item:")
		.setMessage("" + getListView().getItemAtPosition(position))
		.setPositiveButton("See Matched Items", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				chosenItem = itemlist.get(itemIndex);//retrieve the item from the array we made earlier 
				launchMatchedItemActivity();
			}
		})
		.show();
	}
	
	
	
	public void launchMatchedItemActivity(){
		Intent i = new Intent(this, MatchedItemActivity.class);
		i.putExtra("user", user);
		i.putExtra("item",chosenItem);
		startActivity(i);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}
}