package employee;

import inventory.Inventory;
import inventory.InventoryDataRetriever;
import inventory.InventoryFragment.InvventoryListAdapter;

import java.util.ArrayList;
import java.util.List;

import map.MapViewActivity;


import controller.OnModuleItemSelectedListener;



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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import approval.Approvals;
import approval.ApprovalsDataRetriever;

public class EmployeeInfoFragment extends ListFragment{



	public EmployeeInfoFragment(){}

	public EmployeeInfoFragment(Employee employee){
		this.employee = employee;
	}


	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		super.setHasOptionsMenu(true);
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
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState){


		//	 setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List));
		View view = inflater.inflate(R.layout.employee_info_list_layout, null);
		ListView lv = (ListView)view.findViewById(android.R.id.list);

		String[] employee_info =  view.getResources().getStringArray(R.array.employee_info_array);

		lv.setAdapter(new EmployeeInfoListAdapter<String>(getActivity().getBaseContext(), R.layout.employee_info_row_layout,
				R.id.textView_employee_field, employee_info));

		return view;
	}



	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_location, menu);

	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item){

		switch(item.getItemId()){
		case R.id.menu_location:
			Intent intent = new Intent(getActivity(), MapViewActivity.class);

			// Use float for precision,
			Float lati = new Float(employee.getCurrentLatitude());
			Float longit = new Float(employee.getCurrentLongitude());
			
			Log.i(LOG_INFO_TAG, "current lati as float: " + lati);
			Log.i(LOG_INFO_TAG, "currnet lonit as float: " + longit);


			Bundle bundle = new Bundle();

			//bundle.putDouble(CURRENT_EMPLOYEE_LATITUDE_KEY, latitude * Math.pow(10, 6));
			//	bundle.putDouble(CURRENT_EMPLOYEE_LONGITUDE_KEY, longitude * Math.pow(10,6));
			bundle.putFloat(CURRENT_EMPLOYEE_LATITUDE_KEY, lati);
			bundle.putFloat(CURRENT_EMPLOYEE_LONGITUDE_KEY, longit);
			intent.putExtras(bundle);

			startActivity(intent);
			return true;
	
		default:
			return super.onOptionsItemSelected(item);
		}



	}
	@Override
	public void onViewCreated (View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);

	}


	public class EmployeeInfoListAdapter<T> extends ArrayAdapter<T> {

		private T[] employee_info;
		private int textViewResourceId;
		private int resource;
		private Context context;



		public EmployeeInfoListAdapter(Context context, int resource,
				int textViewResourceId, T[] employee_info) {

			super(context, resource, textViewResourceId, employee_info);
			// TODO Auto-generated constructor stub

			this.context = context;
			this.textViewResourceId = textViewResourceId;
			this.resource = resource;
			this.employee_info = employee_info;
	//		Log.d(LOG_INFO_TAG, "EmployeeInfoListAdapter.constructor called");
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			Log.d(LOG_INFO_TAG, "EmployeeInfoListAdapter.getView() called");
			View v = convertView;

			if(v == null){
				LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = li.inflate(R.layout.employee_info_row_layout, null);
			}

			TextView employee_info_field = (TextView)v.findViewById(R.id.textView_employee_field);
			employee_info_field.setText((CharSequence) employee_info[position]);



			Log.i(LOG_INFO_TAG, "EmployeeInfoFragment.getView(): employee_info_field text is: " + employee_info_field.getText().toString());
			TextView employee_info_value = (TextView)v.findViewById(R.id.textView_employee_value);

			switch(position){
			case 0:		// Employee Id
				employee_info_value.setText(" " + employee.getEmployeeId());//employee.getEmployeeId());
				break;
			case 1:		// Name
				employee_info_value.setText(employee.getName());
				break;
			case 2:		// Status
				employee_info_value.setText(employee.getStatus());
				break;
			case 3:		// Last Status Update
				employee_info_value.setText(employee.getLastStatusUpdate());
				break;
			case 4:		// Gender
				employee_info_value.setText(employee.getGender());
				break;
			case 5:		// Date of Birth
				employee_info_value.setText(employee.getBirthday());
				break;
			case 6: 	// Department
				employee_info_value.setText(employee.getDepartment());
				break;
			case 7: 	// Position
				employee_info_value.setText(employee.getPosition());
				break;
			case 8: 	// Phone Number
				employee_info_value.setText(employee.getPhoneNumber());
				break;
			case 9: 	// Email
				employee_info_value.setText(employee.getEmail());
				break;
			case 10: 	// Address
				employee_info_value.setText(employee.getAddress());
				break;
			case 11: 	// Current Location
				employee_info_value.setText("Click here to find current location");
				break;
			}


			return v;
		}
	}


	private static final String LOG_INFO_TAG = "EmployeeInfoFragment Info";

	/**
	 * @param itemStatus the status value defined in the database 
	 */

	private  static Employee employee;

	private static final String CURRENT_EMPLOYEE_LONGITUDE_KEY = "longitude";
	private static final String CURRENT_EMPLOYEE_LATITUDE_KEY = "latitude";
}