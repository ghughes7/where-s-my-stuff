package edu.gatech.presenterView;


import edu.gatech.TriggerHappyCoders.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ItemProfilePageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_profile_page);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
