package inventory;



import java.util.ArrayList;
import java.util.List;


import tai.rapidconsultingusa.rapidSuiteNative.R;
import utility_classes.ListSelector;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
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
import android.widget.SearchView;
import android.widget.TextView;


public class InventoryFragment extends ListFragment{




	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState)
	{
		//	 setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List));
		View view = inflater.inflate(R.layout.custom_list_layout, null);
		ListView lv = (ListView)view.findViewById(android.R.id.list);

		List<Inventory> inventory_list = InventoryDataRetriever.getListOfInventoriess();
		lv.setAdapter(new InvventoryListAdapter<Inventory>(getActivity().getBaseContext(), R.layout.inventory_row_layout,
				R.id.textView_inventory_name,inventory_list));
		
		lv.setSelector(new ListSelector(lv));
		
		return view;
	}
	
	
	@Override 
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		super.setHasOptionsMenu(true);
	}

	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);


		inflater.inflate(R.menu.menu_search, menu);

		MenuItem menuItem = menu.findItem(R.id.menu_search);
/**
		// Get the SearchView and set the searchable configuration
		SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
	//	searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
		searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

		searchView.setQueryHint("Search Inventories");
		**/
	}



	@Override
	public void onViewCreated (View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
	//	Log.d(LOG_INFO_TAG, "onViewCreated() called");

	}
	
	

	@Override
	public void onResume()
	{
		super.onResume();
	//	Log.d(LOG_INFO_TAG, "onResume() called");
	}
	
	

	@Override
	public void onPause()
	{
		super.onPause();
	//	Log.d(LOG_INFO_TAG, "onPause() called");
	}
	
	
	@Override
	public void onStop()
	{
		super.onStop();
//		Log.d(LOG_INFO_TAG, "onStop() called");
	}
	
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	//	Log.d(LOG_INFO_TAG, "onResume() called");
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
	//	Log.i(LOG_INFO_TAG, "onView: inventory item clicked is: " + i.getName());
		
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

			ViewHolder holder;
			if(v == null)
			{
				LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				v = li.inflate(R.layout.inventory_row_layout, null);
				
				holder = new ViewHolder ((TextView) v.findViewById(R.id.textView_inventory_name),
						context.getResources().getDrawable(R.drawable.rounded_corner_top),
						context.getResources().getDrawable(R.drawable.rounded_corner_bottom));
				v.setTag(holder);
			}
			else
				holder = (ViewHolder) v.getTag();
		
			holder.inventory_name.setText(inventory_list.get(position).getName());
			
			if (position == 0)
				v.setBackgroundDrawable(holder.top_corner);
			else if (position == inventory_list.size() -1)
				v.setBackgroundDrawable(holder.bottom_corner);
			return v;
		}
	}
	
	
	private static class ViewHolder
	{
		private TextView inventory_name;
		private Drawable top_corner;
		private Drawable bottom_corner;
		public ViewHolder (TextView inventory_name, Drawable top_corner, Drawable bottom_corner)
		{
			this.inventory_name = inventory_name;
			this.top_corner = top_corner;
			this.bottom_corner = bottom_corner;
			
		}
	}

	
	
	private static final String LOG_INFO_TAG = "InventoryFragment Info";
}
