package edu.gatech.cs2340.thc.presenter;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.triggerhappycoders.DBController;
import edu.gatech.cs2340.triggerhappycoders.R;

public class UserItemsActivity extends ListActivity {

	private User user;
	TextView selection;
	public int idToModify; 
	DBController db;

	List<String[]> list = new ArrayList<String[]>();
	List<String[]> names2 = null ;
	String[] stg1;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check);
		
		Button userProf = (Button)findViewById(R.id.userProf);
		userProf.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//when button is clicked, the screen changes
				Intent addItemScreen = new Intent(getApplicationContext(), 
						UserProfileActivity.class);
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

		stg1=new String[names2.size()]; 
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
				x++;
			}
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
		new AlertDialog.Builder(this)
		.setTitle("Your Item:")
		.setMessage("" + getListView().getItemAtPosition(position))
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}
}