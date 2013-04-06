package edu.gatech.cs2340.thc.presenter;

import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.triggerhappycoders.R.layout;
import edu.gatech.cs2340.triggerhappycoders.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PossibleItemMatches extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_possible_item_matches);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.possible_item_matches, menu);
		return true;
	}

}
