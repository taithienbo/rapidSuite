package inventory;



import java.util.ArrayList;
import java.util.List;


import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;


public class InventoryFragment extends ListFragment{




	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState)
	{
		//	 setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List));
		View view = inflater.inflate(R.layout.inventory_list_layout, null);
		ListView lv = (ListView)view.findViewById(android.R.id.list);

		List<Inventory> inventory_list = InventoryDataRetriever.getListOfInventoriess();
		lv.setAdapter(new InvventoryListAdapter<Inventory>(getActivity().getBaseContext(), R.layout.inventory_row_layout,
				R.id.textView_inventory_name,inventory_list));
		
		return view;
	}




	@Override
	public void onViewCreated (View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		Log.d(LOG_INFO_TAG, "onViewCreated() called");

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
	public void onListItemClick(ListView l, View v, int position, long id){
		ListView lv = getListView();
		lv.setItemChecked(position, true);
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv.setClickable(true);
		lv.setSelection(position);
		
		FragmentManager fm = this.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
		Inventory i = InventoryDataRetriever.getListOfInventoriess().get(position);
		Log.i(LOG_INFO_TAG, "onView: inventory item clicked is: " + i.getName());
		
		ft.replace(R.id.fragment_container, new InventoryInfoFragment(i));
		ft.addToBackStack(null);
		ft.commit();
	}


	public class InvventoryListAdapter<T> extends ArrayAdapter<T> {

		private List<Inventory> inventory_list;
		private int textViewResourceId;
		private int resource;
		private Context context;



		public InvventoryListAdapter(Context context, int resource,
				int textViewResourceId, List<T> inventory_list) {
			
			super(context, resource, textViewResourceId, inventory_list);
			// TODO Auto-generated constructor stub
		
			this.context = context;
			this.textViewResourceId = textViewResourceId;
			this.resource = resource;
			this.inventory_list = (ArrayList<Inventory>) inventory_list;

		}

	


		@Override
		public View getView(int position, View convertView, ViewGroup parent){
	
			View v = convertView;

			if(v == null)
			{
				LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = li.inflate(R.layout.inventory_row_layout, null);
			}

		
			TextView inventory_name = (TextView) v.findViewById(R.id.textView_inventory_name);
				
			inventory_name.setText(inventory_list.get(position).getName());
		

			return v;
		}
	}
	
	
	private static final String LOG_INFO_TAG = "InventoryFragment Info";
}
