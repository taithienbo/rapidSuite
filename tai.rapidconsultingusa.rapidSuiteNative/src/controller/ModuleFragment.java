package controller;



import home.HomeFragment;
import inventory.InventoryFragment;

import java.util.ArrayList;
import java.util.List;

import employee.EmployeesFragment;




import settings.SettingsFragment;
import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.os.IInterface;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import approval.Approvals;
import approval.ApprovalsDataRetriever;
import approval.ApprovalsFragment;
import approval.ApprovalsFragment.ApprovalsListAdapter;

public class ModuleFragment extends ListFragment {





	private static final String LOG_INFO_TAG = "ModuleFragment Info";

	private static final String CURRENT_ITEM_POSITION = "current_item_position";

	private static View view;

	private static int current_selected_item_position = 0;

	private OnModuleItemSelectedListener module_item_listener;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState){


		//	 setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List));
		view = inflater.inflate(R.layout.module_list_layout, null);
		ListView lv = (ListView)view.findViewById(android.R.id.list);


		String[] modules = this.getActivity().getResources().getStringArray(R.array.modules_array);

		lv.setAdapter(new ModuleListAdapter<String>(getActivity().getBaseContext(), R.layout.module_row_layout,R.id.textView_module_item2, modules));

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		Log.d(LOG_INFO_TAG, "onActivityCreated() called");
		
		if(savedInstanceState != null )
			current_selected_item_position = savedInstanceState.getInt(CURRENT_ITEM_POSITION);
	}


	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		Log.d(LOG_INFO_TAG, "onSaveInstanceState called");

		// Get the module selected to restore the fragment state 
		// when onActivityCreated() is called again 
		ListView lv = getListView();

		current_selected_item_position = lv.getCheckedItemPosition();


	}




	@Override
	public void onResume()
	{
		super.onResume();

		if(current_selected_item_position != ListView.INVALID_POSITION)

		//	onListItemClick(getListView(), view, current_selected_item_position, 0 );

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
		ListView lv = getListView();
		int pos = getListView().getSelectedItemPosition();
		
		if(pos != ListView.INVALID_POSITION)
			current_selected_item_position = lv.getSelectedItemPosition();
		Log.i(LOG_INFO_TAG, "onStop() the current position selected is: " + pos);
		
		Log.d(LOG_INFO_TAG, "onStop() called");
	}


	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(LOG_INFO_TAG, "onResume() called");
	}


	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		try
		{
			module_item_listener = (OnModuleItemSelectedListener) activity; 
		}
		catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + " must implement OnModuleItemSelectedLister");
		}


	}


	@Override 
	public void onListItemClick(ListView l, View v, int position, long id){
		Log.i(LOG_INFO_TAG, "onListItemCLick() called");
		ListView lv = getListView();
		lv.setItemChecked(position, true);
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv.setItemChecked(position, true);
		lv.setClickable(true);
		lv.setSelection(position);


		// Remember the item selected to restore later if the fragment is 
		// stopped and then resumed 
		current_selected_item_position = position;

		TextView menu_item = (TextView) v.findViewById(R.id.textView_module_item2);

		String item = (String) menu_item.getText().toString();


		//	Fragment module_fragment = (Fragment) getFragmentManager().findFragmentById(R.id.fragment_container);
		FragmentTransaction ft = getFragmentManager().beginTransaction();


		Fragment newFragment = null;
		
		switch(current_selected_item_position)
		{
		case 0:		// Home
			newFragment = new HomeFragment();
			break;
		case 1: 	// Modules
			break;
		case 2:		// Employees
			newFragment = new EmployeesFragment();
			break;
		case 3:		// Inventory
			newFragment = new InventoryFragment();
			break;
		case 4: 	// Approvals 
			newFragment = new ApprovalsFragment(PENDING);
			break;
		case 5: 	// ApprovalsHistory
			newFragment = new ApprovalsFragment(PROCESSED);
			break;
		//case 6:		// Reports	Temporary disabled
		
		//	break;
		case  6://7:
			newFragment = new SettingsFragment();
			break;
		}
		
	
		if(newFragment != null){

			ft.replace(R.id.fragment_container, newFragment);
			ft.addToBackStack(null);
			ft.commit();
		}


		//	module_item_listener.onModuleItemSelectedListener(item);

	}


	private enum modules{


		HOME("Home"),
		EMPLOYEES ("Employees"),
		INVENTORY("Inventory"),
		APPROVALS("Approvals"),
		APPROVALS_HISTORY("Approvals History"),
		REPORTS("Reports"),
		SETTINGS("Settings");


		private final String module;

		public String getValue(){
			return module;
		}

		modules(String name) {
			this.module = name;
		}
	}


	private static final String PENDING = "Pending";
	private static final String PROCESSED = "Processed";

	public class ModuleListAdapter<T> extends ArrayAdapter<T> {



		private ModuleListAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);

		}

		public ModuleListAdapter(Context context, int resource, int textViewResourceId, T[] objects){
			super(context, resource, textViewResourceId, objects);
			this.context = context;
			this.resource = resource;
			this.textViewResourceId = textViewResourceId;
			this.objects =  objects;
		}




		@Override
		public View getView(int position, View convertView, ViewGroup parent){

			View v = convertView;

			if(v == null){
				LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.module_row_layout, null);
			}

			TextView module_item = (TextView) v.findViewById(R.id.textView_module_item2);

			String[] modules = (String[]) objects;

			String module_name = modules[position];

			ImageView module_icon = (ImageView) v.findViewById(R.id.module_icon);

			switch(position){
			case 0:		// Home	
				module_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.home));
				break;
			case 1:		// Modules
				break;
			case 2:
				module_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.employee));
				break;
			case 3:
				module_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.inventory));
				break;
			case 4:
				module_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.approvals_gray));
				break;
			case 5:
				module_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.approval_history));
				break;
			case 6:
				module_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.reports));
				break;
			case 7:
				module_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.settings));
				break;
			}

			module_item.setText(modules[position]);


			return v;
		}




		private Context context;
		private int resource;
		private int textViewResourceId;
		private T[] objects;

	}

}
