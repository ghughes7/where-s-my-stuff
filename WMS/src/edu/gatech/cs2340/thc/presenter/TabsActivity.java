package edu.gatech.cs2340.thc.presenter;

import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.triggerhappycoders.MapFragment;
import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.triggerhappycoders.SearchFragment;
import edu.gatech.cs2340.triggerhappycoders.SettingsFragment;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.Menu;

public class TabsActivity extends Activity {

	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		String label1 = "Profile";
		Tab tab = actionBar.newTab();
		tab.setIcon(R.drawable.user_tab);
		TabListener<UserProfileFragment> tl = new TabListener<UserProfileFragment>(this, label1, UserProfileFragment.class);
		tab.setTabListener(tl);
		actionBar.addTab(tab);
		
		/*TestActivity ta = new TestActivity();
		ta.setArguments(getIntent().getExtras());*/
		
		String label2 = "Search";
		Tab tab2 = actionBar.newTab();
		tab2.setIcon(R.drawable.search_tab);
		TabListener<SearchFragment> tl2 = new TabListener<SearchFragment>(this, label2, SearchFragment.class);
		tab2.setTabListener(tl2);
		actionBar.addTab(tab2);
		
		String label3 = "Map";
		Tab tab3 = actionBar.newTab();
		tab3.setIcon(R.drawable.map_tab);
		TabListener<MapFragment> tl3 = new TabListener<MapFragment>(this, label3, MapFragment.class);
		tab3.setTabListener(tl3);
		actionBar.addTab(tab3);
		
		String label4 = "Settings";
		Tab tab4 = actionBar.newTab();
		tab4.setIcon(R.drawable.settings_tab);
		TabListener<SettingsFragment> tl4 = new TabListener<SettingsFragment>(this, label4, SettingsFragment.class);
		tab4.setTabListener(tl4);
		actionBar.addTab(tab4);

		/*Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("user");*/

	}

	private class TabListener<T extends Fragment> implements ActionBar.TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		public TabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// Check if the fragment is already initialized
			if (mFragment == null) {
				// If not, instantiate and add it to the activity
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				// If it exists, simply attach it in order to show it
				ft.attach(mFragment);
			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				// Detach the fragment, because another one is being attached
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// User selected the already selected tab. Usually do nothing.
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tabs, menu);
		return true;
	}

}
