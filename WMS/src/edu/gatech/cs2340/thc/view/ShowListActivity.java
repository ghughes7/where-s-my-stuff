package edu.gatech.cs2340.thc.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.triggerhappycoders.R.id;
import edu.gatech.cs2340.triggerhappycoders.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class ShowListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_list);

		//displayList();
		

		
	}
	
	public void displayList(){
		EditText list = (EditText) findViewById(R.id.listTest);

		try {

			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(

					openFileInput("ItemCollection")));

			String inputString;

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.show_list, menu);
		return true;
	}

}
