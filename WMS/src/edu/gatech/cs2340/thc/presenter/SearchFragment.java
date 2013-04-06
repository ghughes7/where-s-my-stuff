package edu.gatech.cs2340.thc.presenter;

import java.util.ArrayList;
import java.util.HashMap;

import edu.gatech.cs2340.thc.model.Item;
import edu.gatech.cs2340.thc.model.ItemCollection;
import edu.gatech.cs2340.thc.model.User;
import edu.gatech.cs2340.triggerhappycoders.R;
import edu.gatech.cs2340.triggerhappycoders.R.id;
import edu.gatech.cs2340.triggerhappycoders.R.layout;

import android.os.Bundle;
//import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFragment extends Fragment {

	// List view
	private ListView lv;

	// Listview Adapter
	ArrayAdapter<String> adapter;

	// Search EditText
	EditText inputSearch;

	// ArrayList for Listview
	ArrayList<HashMap<String, String>> productList;

	private User user;
	// private UserCollection uc;
	private ArrayList<Item> itemList;
	String itemsString[];

	MyCustomAdapter dataAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_search_fragment, container,
				false);
	}

	@Override
	public void onStart() {
		super.onStart();

		Intent intent = getActivity().getIntent();
		user = (User) intent.getSerializableExtra("user");

		ItemCollection ic = new ItemCollection(getActivity(), user); // creates
																		// the
																		// item
																		// collection
																		// with
																		// three
																		// pre-made
																		// items
		// gets items info
		itemList = ic.getAllItems();

		displayListView();

		/*
		 * if (itemList != null) { itemsString = new String[itemList.size()]; //
		 * Log.d("itemList size", Integer.toString(itemList.size())); for (int i
		 * = 0; i < itemList.size(); i++) {
		 * 
		 * itemsString.append(itemList.get(i).getItemName());
		 * itemsString.append("\n");
		 * 
		 * itemsString[i] = itemList.get(i).getItemName(); }
		 * 
		 * EditText itemList = (EditText) getView()
		 * .findViewById(R.id.itemList);// display items' info //
		 * itemList.setText(itemsString);
		 * 
		 * }
		 * 
		 * 
		 * String products[] = { "Dell Inspiron", "HTC One X", "HTC Wildfire S",
		 * "HTC Sense", "HTC Sensation XE", "iPhone 4S",
		 * "Samsung Galaxy Note 800", "Samsung Galaxy S3", "MacBook Air",
		 * "Mac Mini", "MacBook Pro" };
		 * 
		 * 
		 * lv = (ListView) getView().findViewById(R.id.list_view); inputSearch =
		 * (EditText) getView().findViewById(R.id.inputSearch);
		 * 
		 * // Adding items to listview adapter = new
		 * ArrayAdapter<String>(getActivity(), R.layout.list_item,
		 * R.id.item_name, itemsString); lv.setAdapter(adapter);
		 * 
		 * inputSearch.addTextChangedListener(new TextWatcher() {
		 * 
		 * @Override public void onTextChanged(CharSequence cs, int arg1, int
		 * arg2, int arg3) { // When user changed the Text
		 * SearchFragment.this.adapter.getFilter().filter(cs); }
		 * 
		 * @Override public void beforeTextChanged(CharSequence arg0, int arg1,
		 * int arg2, int arg3) { // TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void afterTextChanged(Editable arg0) { // TODO
		 * Auto-generated method stub } });
		 */
	}

	private void displayListView() {

		// Array list of countries
		// ArrayList<Country> theItemList = new ArrayList<Country>();

		// create an ArrayAdaptar from the String Array
		dataAdapter = new MyCustomAdapter(
				getActivity().getApplicationContext(), R.layout.item_info_list,
				itemList);
		ListView listView = (ListView) getView().findViewById(R.id.list_view);
		// Assign adapter to ListView
		listView.setAdapter(dataAdapter);

		// enables filtering for the contents of the given ListView
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Item item = (Item) parent.getItemAtPosition(position);
				Toast.makeText(getActivity().getApplicationContext(), item.getItemName(),
						Toast.LENGTH_SHORT).show();
				//launch intent to PossibleItemMatches
//				goToPossibleMatches();
			}
		});

		
		EditText myFilter = (EditText) getView().findViewById(R.id.inputSearch);
		myFilter.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				dataAdapter.getFilter().filter(s.toString());
			}
		});
	}
	/*
	protected void goToPossibleMatches() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(), PossibleItemMatches.class);
		startActivity(intent);
		
	}
	*/

	private class MyCustomAdapter extends ArrayAdapter<Item> {

		private ArrayList<Item> originalList;
		private ArrayList<Item> theItemList;
		private ItemFilter filter;

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<Item> theItemList) {
			super(context, textViewResourceId, theItemList);
			this.theItemList = new ArrayList<Item>();
			this.theItemList.addAll(theItemList);
			this.originalList = new ArrayList<Item>();
			this.originalList.addAll(theItemList);
		}

		@Override
		public Filter getFilter() {
			if (filter == null) {
				filter = new ItemFilter();
			}
			return filter;
		}

		private class ViewHolder {

			TextView name;
			TextView category;
			// TextView status;
			TextView date;
			TextView type;

			TextView owner;
			TextView location;
			TextView itemDes;
			TextView reward;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));
			if (convertView == null) {

				LayoutInflater vi = (LayoutInflater) getActivity()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.item_info_list, null);

				holder = new ViewHolder();
				holder.name = (TextView) convertView
						.findViewById(R.id.nameView);
				holder.category = (TextView) convertView
						.findViewById(R.id.categoryView);
				// holder.status = (TextView)
				// convertView.findViewById(R.id.statusView);
				holder.date = (TextView) convertView
						.findViewById(R.id.dateView);
				holder.type = (TextView) convertView
						.findViewById(R.id.typeView);

				holder.owner = (TextView) convertView
						.findViewById(R.id.ownerView);
				holder.location = (TextView) convertView
						.findViewById(R.id.locationView);
				holder.itemDes = (TextView) convertView
						.findViewById(R.id.itemDesView);
				holder.reward = (TextView) convertView
						.findViewById(R.id.rewardView);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Item item = theItemList.get(position);
			holder.owner.setText(item.getOwner());
			holder.name.setText(item.getItemName());
			holder.category.setText(item.getCatagory());
			// holder.status.setText(item.getStatus());
			holder.date.setText(item.getDate());
			holder.type.setText(item.getType());
			holder.location.setText(item.getLocation());
			holder.itemDes.setText(item.getItemDes());
			holder.reward.setText(item.getReward());

			return convertView;

		}

		private class ItemFilter extends Filter {

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {

				constraint = constraint.toString().toLowerCase();
				FilterResults result = new FilterResults();
				if (constraint != null && constraint.toString().length() > 0) {
					ArrayList<Item> filteredItems = new ArrayList<Item>();

					for (int i = 0, l = originalList.size(); i < l; i++) {
						Item item = originalList.get(i);
						if (item.toString().toLowerCase().contains(constraint))
							filteredItems.add(item);
					}
					result.count = filteredItems.size();
					result.values = filteredItems;
				} else {
					synchronized (this) {
						result.values = originalList;
						result.count = originalList.size();
					}
				}
				return result;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {

				theItemList = (ArrayList<Item>) results.values;
				notifyDataSetChanged();
				clear();
				for (int i = 0, l = theItemList.size(); i < l; i++)
					add(theItemList.get(i));
				notifyDataSetInvalidated();
			}
		}
	}
}
