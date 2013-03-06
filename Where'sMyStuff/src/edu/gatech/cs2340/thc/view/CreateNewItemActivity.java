package edu.gatech.cs2340.thc.view;


import edu.gatech.cs2340.triggerhappycoders.LoginActivity;
import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.presenter.ItemProfilePageActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;



public class CreateNewItemActivity extends Activity {
	
	private RadioGroup radioTypeGroup;
	private RadioButton radioTypeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_new_item);
		
		
	}
	
	//if user click "create new item" button
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
		
	
		EditText itemName = (EditText)findViewById(R.id.nameField);//get name
		EditText reward = (EditText)findViewById(R.id.rewardField);//get reward
		EditText itemDes = (EditText)findViewById(R.id.itemDesField);//get item description
		
		String itemString = itemName.getText().toString();
		String rewardString = reward.getText().toString();
		String itemDesString = itemDes.getText().toString();
		
		Item item = new Item(itemString, itemDesString, rewardString, type);
		
		//add code to put item into item collection then database
		
		
		Intent intent = new Intent(this, ItemProfilePageActivity.class);//go to item profile page
		intent.putExtra("itemObj",item);//pass in the ItemProfile object, the string can be named anything
		startActivity(intent);//go to the item profile page activity
	}
	
	
	
	//if user click "cancel" button
	public void goToLoginPage(View view){
		
		Intent intent = new Intent(this, LoginActivity.class);//go back to login page
		startActivity(intent);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
