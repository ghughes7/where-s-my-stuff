package edu.gatech.cs2340.thc.view;

import java.util.Calendar;

import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.thc.presenter.AdminProfileActivity;
import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.triggerhappycoders.R.layout;
import edu.gatech.cs2340.triggerhappycoders.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;

public class DatePickerActivity extends Activity {
	
	private int year;
	private int day;
	private int month;
	private String dateString;
	private DatePicker datePicker;
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picker);
		Intent intent = getIntent();
		user = (User)intent.getSerializableExtra("user");
		
	}
	
	//is called when "done" button is clicked, gets date info, 
	//goes back to create item page
	public void getDate(View view){
		
		datePicker = (DatePicker) findViewById(R.id.datePicker);
		 
		year = datePicker.getYear();
		month = datePicker.getMonth() + 1;
		day = datePicker.getDayOfMonth();
		
		dateString = Integer.toString(month) + "/" + Integer.toString(day) + "/" 
				+ Integer.toString(year);
		
		
		
		Intent intent = new Intent(this, CreateNewItemActivity.class);
		intent.putExtra("user",user);//passes the user back, we didn't do anything with 
		//the user here but it's for persistence purposes
		intent.putExtra("dateString",dateString);//let CreateNewItemActivity have the dateString
		startActivity(intent);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date_picker, menu);
		return true;
	}

}
