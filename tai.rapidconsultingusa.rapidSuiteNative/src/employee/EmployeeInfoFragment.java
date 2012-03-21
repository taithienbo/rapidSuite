package employee;



import imageDownloader.UrlImageViewHelper;

import java.io.Serializable;
import java.util.ArrayList;


import map.MapViewActivity;





import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class EmployeeInfoFragment extends ListFragment implements Serializable
{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public EmployeeInfoFragment(){}



	public Employee getCurrentSelectedEmployee()
	{
		return (Employee) getArguments().getSerializable(Employee.RETRIEVAL_KEY);
	}


	public static EmployeeInfoFragment getEmployeesFragmentInstance (Employee employee)
	{
		EmployeeInfoFragment f = new EmployeeInfoFragment( employee );
		//this.current_item_position_selected = position;
		Bundle args = new Bundle();
		args.putSerializable(Employee.RETRIEVAL_KEY, employee);
		f.setArguments(args);

		return f;
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
		Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onAttach() called");


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


		lv.setAdapter(new EmployeeInfoListAdapter(getActivity(),  employee_info));

		return view;
	}


	@Override
	public void onActivityCreated( Bundle savedInstanceState)
	{
		super.onActivityCreated (savedInstanceState);

		Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onActivityCreated called");
	}


	@Override
	public void onStart()
	{
		super.onStart();
		Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onStart() called");
	}



	@Override
	public void onResume()
	{
		super.onResume();
		Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onResume() called");
	}


	@Override
	public void onPause()
	{
		super.onPause();
		Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onPause() called");
	}


	@Override
	public void onStop()
	{
		super.onStop();
		Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onStop() called");
	}


	@Override
	public void onDestroyView()
	{
		super.onDestroyView();
		Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onDestroyView() called");
	}



	@Override
	public void onDestroy()
	{

		super.onDestroy();
		Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onDestroy() called");
	}




	@Override
	public void onDetach()
	{
		super.onDetach();
		Log.d(LOG_INFO_TAG, "EmployeesInfoFragment.onDetach() called");
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


			bundle.putFloat(CURRENT_EMPLOYEE_LATITUDE_KEY, lati);
			bundle.putFloat(CURRENT_EMPLOYEE_LONGITUDE_KEY, longit);

			bundle.putSerializable(Employee.RETRIEVAL_KEY, employee);


			bundle.putString("caller", this.getClass().getName());
			intent.putExtras(bundle);

			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}



	}



	public class EmployeeInfoListAdapter extends BaseAdapter {

		private String[] employee_info;
		
		private Context context;

		private LayoutInflater mInflater;

		private static final int NUMBER_OF_VIEW_TYPE = 2;


		public EmployeeInfoListAdapter(Context context, String[] employee_info) {


			// TODO Auto-generated constructor stub

			this.context = context;

			mInflater = LayoutInflater.from(context);
			this.employee_info = employee_info;
			//		Log.d(LOG_INFO_TAG, "EmployeeInfoListAdapter.constructor called");
		}


		public View getView(int position, View convertView, ViewGroup parent){
			//	Log.d(LOG_INFO_TAG, "EmployeeInfoListAdapter.getView() called");
			View v = convertView;

			ViewHolder holder;

			if(v == null)
			{

				v = mInflater.inflate(R.layout.employee_info_row_layout, null);

				holder = new ViewHolder
						(
								(TextView) v.findViewById(R.id.textView_employee_field), 
								(TextView) v.findViewById(R.id.textView_employee_value),
								context.getResources().getDrawable(R.drawable.rounded_corner_top),
								context.getResources().getDrawable(R.drawable.rounded_corner_bottom),
								context.getResources().getDrawable(R.drawable.rounded_corner)
								);


				v.setTag(holder);

			}
			else
				holder = (ViewHolder) v.getTag();

			Intent intent = null;

			switch(position){
			case 0:		// Employee Id
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(" " + employee.getEmployeeId());
				v.setBackgroundDrawable(holder.corners);
				break;


			case 2:		// Email
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getEmail());
				v.setBackgroundDrawable(holder.corners);

				intent = new Intent (android.content.Intent.ACTION_CHOOSER);

				Intent send_intent = new Intent (Intent.ACTION_SEND);
				send_intent.setType("plain/text");

				send_intent.putExtra(Intent.EXTRA_EMAIL, new String[]{employee.getEmail()});
				intent.putExtra(android.content.Intent.EXTRA_INTENT, send_intent);
				break;



			case 4:		// Phone
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getPhoneNumber());

				v.setBackgroundDrawable(holder.corners);

				intent = new Intent (android.content.Intent.ACTION_CHOOSER);

				Intent dial_intent = new Intent (Intent.ACTION_DIAL);

				dial_intent.setData(Uri.parse("tel:" + employee.getPhoneNumber()));
				intent.putExtra(android.content.Intent.EXTRA_INTENT, dial_intent);

				break;

			case 6:		// Position
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getPosition());
				v.setBackgroundDrawable(holder.top_corner);
				break;

			case 7:		// Department
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getDepartment());
				v.setBackgroundDrawable(holder.bottom_corner);
				break;  

			case 9:		// Status
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getStatus());
				v.setBackgroundDrawable(holder.top_corner);
				break;
				
			case 10: 	// Last Status Update
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getLastStatusUpdate());
				v.setBackgroundDrawable(holder.bottom_corner);
				break;

			default:	//Separator
				v = mInflater.inflate(R.layout.separator_layout, null);
				v.setVisibility(View.INVISIBLE);

				break;


			}

			if (intent != null)
				v.setOnClickListener(new EmployeeOnClickListener(intent, context));

			return v;
		}


		public int getCount() {
			// TODO Auto-generated method stub
			return employee_info.length;
		}


		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return employee_info[arg0];
		}


		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		
		@Override
		public int getViewTypeCount()
		{
			return NUMBER_OF_VIEW_TYPE;
		}
		
		@Override
		public int getItemViewType(int position)
		{
			return BaseAdapter.IGNORE_ITEM_VIEW_TYPE;
		}
		




	}


	private static class ViewHolder
	{

		private TextView field;
		private TextView value;
		private Drawable top_corner;
		private Drawable bottom_corner;
		private Drawable corners;

		public ViewHolder(TextView field, TextView value,
				Drawable top_corner, Drawable bottom_corner,
				Drawable corners)
		{
			this.field = field;
			this.value = value;
			this.top_corner = top_corner;
			this.bottom_corner = bottom_corner;
			this.corners = corners;

		}
	}


	private class EmployeeOnClickListener implements OnClickListener
	{
		private Intent intent;

		public EmployeeOnClickListener (Intent intent, Context context)
		{
			this.intent = intent;
		}

		public void onClick(View v) 
		{
			startActivity(intent);

		}

	}


	private static final String LOG_INFO_TAG = "EmployeeInfoFragment";

	/**
	 * @param itemStatus the status value defined in the database 
	 */

	private  static Employee employee;

	private static final String CURRENT_EMPLOYEE_LONGITUDE_KEY = "longitude";
	private static final String CURRENT_EMPLOYEE_LATITUDE_KEY = "latitude";


	private static final String CALLER = "caller";
}
