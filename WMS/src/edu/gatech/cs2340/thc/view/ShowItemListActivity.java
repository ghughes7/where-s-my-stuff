package edu.gatech.cs2340.thc.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import edu.gatech.cs2340.triggerhappycoders.R;

/**
 * for testing purposes
 * @author circusburger63
 *
 */
public class ShowItemListActivity extends Activity {

	@Override
	/**
	 * This method is the default android material
	 * 
	 * @param savedInstanceState - Bundle  
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_list);

		displayList();
		

		
	}
	
	/**
	 * This method displays the list from the user collection
	 */
	public void displayList(){
		EditText list = (EditText) findViewById(R.id.listTest);

		try {
			
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(

					openFileInput("ItemCollection")));

			String inputString = "";

			StringBuffer stringBuffer = new StringBuffer();
			
			while ((inputString = inputReader.readLine()) != null) {
				
				stringBuffer.append(inputString + "\n");

			}

			list.setText(stringBuffer.toString());

		} catch (IOException e) {

			e.printStackTrace();

		}
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
		// getMenuInflater().inflate(R.menu.show_list, menu);
		return true;
	}

}
