package inventory;

import map.MapViewActivity;
import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class InventoryInfoFragment extends ListFragment{


	public InventoryInfoFragment(){};
	public InventoryInfoFragment(Inventory inventory){
		InventoryInfoFragment.inventory = inventory;
	}



	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		super.setHasOptionsMenu(true);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, 
			Bundle savedInstanceState)
	{


		View view = inflater.inflate(R.layout.inventory_info_list_layout, null);
		ListView lv = (ListView) view.findViewById(android.R.id.list);

		String[] inventory_info = view.getResources().getStringArray(R.array.inventory_info_array);

		lv.setAdapter(new InventoryInfoListAdapter<String>(this.getActivity(), R.layout.inventory_info_row_layout,
				R.id.textView_inventory_info_field,inventory_info));
		return view;

	}



	@Override
	public void onResume()
	{
		super.onResume();
		Log.d(LOG_INFO_TAG, "onResume() called");
	}



	@Override
	public void onPause()
	{
		super.onPause();
		Log.d(LOG_INFO_TAG, "onPause() called");
	}


	@Override
	public void onStop()
	{
		super.onStop();
		Log.d(LOG_INFO_TAG, "onStop() called");
	}


	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(LOG_INFO_TAG, "onResume() called");
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_location:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(getActivity(), MapViewActivity.class);

			Bundle b = new Bundle();

			Float longitude = new Float(inventory.getCurrentLongitude());
			Float latitude = new Float(inventory.getCurrentLatitude());

			b.putFloat(INVENTORY_CURRENT_LONGITUDE_KEY, longitude);
			b.putFloat(INVENTORY_CURRENT_LATITUDE_KEY, latitude);
			
			b.putSerializable(Inventory.INVENTORY_RETRIEVAL_KEY,inventory);
			
			b.putString("caller", this.getClass().getName());
			
			intent.putExtras(b);
			startActivity(intent);

			return super.onOptionsItemSelected(item);
		default:
			return super.onOptionsItemSelected(item);
		}
	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){

		super.onCreateOptionsMenu(menu, inflater);

		inflater.inflate(R.menu.menu_location, menu);
	}

	private class InventoryInfoListAdapter<T> extends ArrayAdapter<T>{

		public InventoryInfoListAdapter(Context context, int resource,
				int textViewResourceId, T[] inventoryInfoList) {
			super(context, resource, textViewResourceId, inventoryInfoList);
			this.context = context;
			this.inventoryInfoList = inventoryInfoList;
			// TODO Auto-generated constructor stub
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent){

			View v = convertView;
			if(v == null){
				LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = li.inflate(R.layout.inventory_info_row_layout,null);
			}

			TextView field = (TextView) v.findViewById(R.id.textView_inventory_info_field);
			//	Log.i(LOG_INFO_TAG, "getView(): InventoryInfoList text at position " + position + ": "
			//			+ inventoryInfoList[position]);		

			field.setText(inventoryInfoList[position].toString());

			TextView value = (TextView)v.findViewById(R.id.textView_inventory_info_value);
			if(value == null){
				Log.e(LOG_INFO_TAG, "textView_inventory_info_value is null");
			}
			if(inventory == null){
				Log.e(LOG_INFO_TAG, "inventory is null");
			}
			else{
				Log.i(LOG_INFO_TAG, "inventory is not null," +
						"\n Name of inventory: " + inventory.getName());
			}

			switch(position){
			case 0:			// Name 
				value.setText(inventory.getName());
				break;
			case 1:			// Id 
				value.setText(Integer.toString(inventory.getId()));
				break;
			case 2:			// Code 
				value.setText(inventory.getCode());
				break;
			case 3:			// Category
				value.setText(inventory.getCategory());
				break;
			case 4:			// Manufacturer
				value.setText(inventory.getManufacturer());
				break;
			case 5:			// Wholesale Price
				value.setText(inventory.getWholesalePrice());
				break;
			case 6:			// Suggested Retail Price
				value.setText(inventory.getMSRP());
				break;
			case 7:			// Availability
				value.setText(inventory.getAvailability());
				break;
			case 8:			// Current Location
				value.setText(inventory.getAddress());
				break;
			case 9:			// Last Location Update
				value.setText(inventory.getLastLocationUpdate());
				break;


			}

			return v;

		}
		private Context context;
		private T[] inventoryInfoList;
	}


	private static final String LOG_INFO_TAG = "InventoryInfoFragment info:";

	private static final String INVENTORY_CURRENT_LONGITUDE_KEY = "longitude";
	private static final String INVENTORY_CURRENT_LATITUDE_KEY = "latitude";
	private static Inventory inventory;
}
