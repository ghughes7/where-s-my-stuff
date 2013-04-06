package edu.gatech.cs2340.thc.model;

import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.triggerhappycoders.R.layout;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This Fragment will be responsible for displaying the item's location on
 * Google Maps. Will interface with Google API's. -Extra Credit being worked on
 * 
 * @author Trigger Happy Coders
 * @version 1.0
 */
public class MapFragment extends Fragment {
	/**
	 * Default Android implementation to create Fragments.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_map_fragment, container,
				false);
	}

}
