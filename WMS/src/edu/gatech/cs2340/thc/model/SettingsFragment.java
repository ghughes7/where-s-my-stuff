package edu.gatech.cs2340.thc.model;

import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.triggerhappycoders.R.layout;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Settings (Extra-Credit) 
 * Customize how you use the application
 * 
 * @author Trigger Happy Coders
 * @version 1.0
 */
public class SettingsFragment extends Fragment {

	/**
	 * Default Android implementation of Fragments
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_settings_fragment, container, false);
	}
}
