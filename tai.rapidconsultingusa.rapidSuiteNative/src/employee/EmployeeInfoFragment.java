package employee;



import imageDownloader.UrlImageViewHelper;

import java.io.Serializable;



import map.MapViewActivity;





import tai.rapidconsultingusa.rapidSuiteNative.R;
import utility_classes.ListSelector;
import android.app.Activity;
import android.app.ListFragment;

import android.content.Intent;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class EmployeeInfoFragment extends ListFragment implements Serializable
{




	private static final String LOG_INFO_TAG = "EmployeeInfoFragment";

	private  static Employee employee;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public EmployeeInfoFragment(){}



	public Employee getCurrentSelectedEmployee()
	{
		return (Employee) getArguments().getSerializable(Employee.RETRIEVAL_KEY);
	}




	public EmployeeInfoFragment( Employee employee)
	{
		this.employee = employee;
		this.setRetainInstance(true);
	}


	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
	//	Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onAttach() called");

	}




	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		super.setHasOptionsMenu(true);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState)
	{


		//	 setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List));
		View view = inflater.inflate(R.layout.custom_list_layout, null);
		
		ListView lv = (ListView)view.findViewById(android.R.id.list);
	//	lv.setSelector(new ListSelector(lv));	does not have any effect

		lv.setSelector(R.color.transparent);

		String[] employee_info =  view.getResources().getStringArray(R.array.employee_info_array);


		View header_view = inflater.inflate(R.layout.employee_info_list_heading_layout, null);

		ImageView employee_image = (ImageView)header_view.findViewById(R.id.imageView_employee_pic);
		UrlImageViewHelper.setUrlDrawable(employee_image, employee.getPictureLink());

		TextView employee_name = (TextView)header_view.findViewById(R.id.textView_employee_info_name);
		employee_name.setText(employee.getName());

		TextView employee_address = (TextView)header_view.findViewById(R.id.textView_employee_info_address);
		employee_address.setText(employee.getAddress());

		header_view.setMinimumHeight(200);
		lv.addHeaderView(header_view);
		lv.setHeaderDividersEnabled(false);


		lv.setAdapter(new EmployeeInfoListAdapter(getActivity(),  employee_info, employee));

		return view;
	}


	@Override
	public void onActivityCreated( Bundle savedInstanceState)
	{
		super.onActivityCreated (savedInstanceState);

	//	Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onActivityCreated called");
	}


	@Override
	public void onStart()
	{
		super.onStart();
	//	Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onStart() called");
	}



	@Override
	public void onResume()
	{
		super.onResume();
	//	Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onResume() called");
	}


	@Override
	public void onPause()
	{
		super.onPause();
	//	Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onPause() called");
	}


	@Override
	public void onStop()
	{
		super.onStop();
	//	Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onStop() called");
	}


	@Override
	public void onDestroyView()
	{
		super.onDestroyView();
	//	Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onDestroyView() called");
	}



	@Override
	public void onDestroy()
	{

		super.onDestroy();
	//	Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onDestroy() called");
	}




	@Override
	public void onDetach()
	{
		super.onDetach();
	//	Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onDetach() called");
	}


	@Override
	public void onViewCreated (View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		//(LOG_INFO_TAG, "onViewCreated called");
	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_location, menu);

	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		switch(item.getItemId()){
		case R.id.menu_location:
			Intent intent = new Intent(getActivity(), MapViewActivity.class);

			// Use float for precision,
			Float lati = new Float(employee.getCurrentLatitude());
			Float longit = new Float(employee.getCurrentLongitude());


			Bundle bundle = new Bundle();


			bundle.putFloat(MapViewActivity.LATITUDE_KEY, lati);
			bundle.putFloat(MapViewActivity.LONGITUDE_KEY, longit);

			bundle.putSerializable(Employee.RETRIEVAL_KEY, employee);

			bundle.putString(MapViewActivity.INTENT_CALLER_KEY, this.getClass().getName());
			intent.putExtras(bundle);

			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}



}
