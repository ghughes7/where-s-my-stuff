package edu.gatech.cs2340.thc.view;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.model.UserCollection;
import edu.gatech.cs2340.triggerhappycoders.R;

/**
 * for testing purposes
 * @author Trigger Happy Coders (23)
 *
 */
public class ShowUsersActivity extends ListActivity  {

	@Override
	/**
	 * This method is the default android material
	 * 
	 * @param savedInstanceState - Bundle  
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.users_list_activity);
		displayList();
	}
	
	/**
	 * This method displays the list from the user collection  
	 */
	public void displayList(){
		
		TextView list = (TextView) findViewById(R.id.selection);
		EditText number = (EditText) findViewById(R.id.number);
		
		UserCollection uc = new UserCollection(this);
		//uc.deleteUser("tunae");
		
		
		List <User> userList = uc.getUserList();
		String info = "";
		ArrayList<String> infoArray = new ArrayList<String>();
		
		
		for(int i = 0; i < userList.size(); i++){
			info = "Email: " + userList.get(i).getEmail() + "\n" + "Name: " + 
					userList.get(i).getName() + "\n" + "Admin: " + userList.get(i).getIsAdmin()
					+ "\n" + "Locked Out: " + userList.get(i).getLockedStatus();
			infoArray.add(info);
			info = "";
		}
		
		
		/*
		try {

			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(

					openFileInput("UserCollection")));

			String inputString;

			StringBuffer stringBuffer = new StringBuffer();

			while ((inputString = inputReader.readLine()) != null) {

				stringBuffer.append(inputString + "\n");
				//Log.d("inputString0", inputString);

			}
			
			//list.setText(stringBuffer.toString());

		} catch (IOException e) {

			e.printStackTrace();

		}
		*/
		
		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>
		(this,android.R.layout.simple_list_item_1, infoArray);
		        this.setListAdapter(adapter);*/
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, infoArray));
		getListView().setTextFilterEnabled(true);
	}
	
	

	/**
	 * This method shows a dialog box when a user is clicked
	 * 
	 * @param parent - ListView activity's list view widget
	 * @param v - View The view that was clicked within the ListView
	 * @param position - int The position of the view in the list
	 * @param id - long The row id of the item that was clicked 
	 */
	public void onListItemClick(ListView parent, View v, int position, long id) {
		super.onListItemClick(parent, v, position, id);
		new AlertDialog.Builder(this).setTitle("User")
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
	/**
	 * This method adds items to the action bar if it is present
	 * 
	 * @param menu - Menu inflatable menu
	 * @return true
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.show_users, menu);
		return true;
	}

}
