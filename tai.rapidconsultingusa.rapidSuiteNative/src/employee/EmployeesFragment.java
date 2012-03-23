package employee;



import imageDownloader.UrlImageViewHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import controller.OnItemSelectedListener;




import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


public class EmployeesFragment extends ListFragment implements Serializable
{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OnItemSelectedListener module_item_listener;

	private static final String CURRENT_SELECTED_EMPLOYEE = "current_selected_employee";

	private static int current_item_position_selected = -1;

	private static final String LOG_INFO_TAG = "EmployeesFragment";


	private OnItemSelectedListener mListener;

	@Override
	public void onViewCreated (View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view,  savedInstanceState);
		Log.d(LOG_INFO_TAG, "EmployeesFragment.onViewCreated() called");
	}


	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);

		mListener = (OnItemSelectedListener) activity;

		Log.d(LOG_INFO_TAG, "EmployeesFragment.onAttach() called");

	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);
	
	//	SearchView emp_search_view = (SearchView) this.getActivity().findViewById(R.id.menu_search);
	//	emp_search_view.setIconified(false);
	
		inflater.inflate(R.menu.menu_search, menu);
		
		MenuItem menuItem = menu.findItem(R.id.menu_search);
		SearchView emp_search_view = (SearchView) menuItem.getActionView();
	//	emp_search_view.setIconifiedByDefault(false);
	

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		return false;
	}
	
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		super.setHasOptionsMenu(true);
		Log.d(LOG_INFO_TAG, "EmployeesFragment.onCreate() called");

	}
	
	



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, viewgroup, savedInstanceState);

		Log.d (LOG_INFO_TAG, "EmployeesFragment.onCreateView() called");

		//	 setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List));
		View view = inflater.inflate(R.layout.custom_list_layout, null);
		ListView lv = (ListView) view.findViewById(android.R.id.list);

		List<Employee> employee_list = EmployeeDataRetriever.getListOfEmployees();



		lv.setAdapter(new EmployeeListAdapter(getActivity(), employee_list));

		if (savedInstanceState != null)
		{
			lv.setItemChecked(savedInstanceState.getInt(CURRENT_SELECTED_EMPLOYEE), true);
		}
		return view;
	}


	@Override
	public void onActivityCreated (Bundle savedInstanceState)
	{
		super.onActivityCreated (savedInstanceState);

		Log.d(LOG_INFO_TAG, "EmployeesFragment.onActivityCreated called");
	}


	@Override
	public void onStart()
	{
		super.onStart();
		Log.d(LOG_INFO_TAG, "EmployeesFragment.onStart() called");
	}



	@Override
	public void onResume()
	{
		super.onResume();

		int temp = current_item_position_selected;
		Log.d(LOG_INFO_TAG, "EmployeesFragment.onResume() called");

	}



	@Override
	public void onPause()
	{
		super.onPause();
		Log.d(LOG_INFO_TAG, "EmployeesFragment.onPause() called");
	}


	@Override
	public void onStop()
	{
		super.onStop();
		Log.d(LOG_INFO_TAG, "EmployeesFragment.onStop() called");


	}



	@Override
	public void onDestroyView()
	{
		super.onDestroyView();
		Log.d(LOG_INFO_TAG, "EmployeesFragment.onDestroyView() called");
	}



	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(LOG_INFO_TAG, "EmployeesFragment.onDestroy() called");

	}





	@Override
	public void onDetach()
	{
		super.onDetach();
		Log.d(LOG_INFO_TAG, "EmployeesFragment.onDetach() called");

	}




	@Override 
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		//	showDetails(position);
		Employee e = EmployeeDataRetriever.getListOfEmployees().get(position);
		mListener.onFragmentSelectedListener(new EmployeeInfoFragment (e));

	}




	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		Log.d(LOG_INFO_TAG, "onSaveInstanceState called");


		outState.putInt(CURRENT_SELECTED_EMPLOYEE, current_item_position_selected);

	}




	public class EmployeeListAdapter extends BaseAdapter 
	{


		private Context context;
		private List<Employee> employee_list;
		private LayoutInflater mInflater;


		public EmployeeListAdapter(Context context, List<Employee> employee_list)
		{
			this.context = context;
			this.employee_list = employee_list;
			this.mInflater = LayoutInflater.from(context);
		}


		public int getCount() {
			// TODO Auto-generated method stub
			return employee_list.size();
		}


		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return employee_list.get(position);
		}


		public long getItemId(int position) 
		{
			// TODO Auto-generated method stub
			return employee_list.get(position).getEmployeeId();
		}


		public View getView(int position, View convertView, ViewGroup parent) 
		{
			// TODO Auto-generated method stub
			ViewHolder holder;

			View v = convertView;

			if ( v == null )
			{

				v = mInflater.inflate ( R.layout.employee_row_layout, null );
				holder = new ViewHolder ( (TextView) v.findViewById(R.id.textView_employee_row_name));

				v.setTag ( holder );
			}

			else
				holder = (ViewHolder) v.getTag ( );

			holder.employee_name.setText( employee_list.get(position).getName());

			holder.top_edges = context.getResources().getDrawable(R.drawable.rounded_corner_top);

			holder.bottom_edges = context.getResources().getDrawable(R.drawable.rounded_corner_bottom);

			if (position == 0)
				v.setBackgroundDrawable(holder.top_edges);

			else if (position == employee_list.size() - 1)
				v.setBackgroundDrawable(holder.bottom_edges);


			return v;

		}


	}




	private class ViewHolder 
	{
		private TextView employee_name;
		private Drawable top_edges;
		private Drawable bottom_edges;

		public ViewHolder (TextView employee_name)
		{
			this.employee_name = employee_name;
		}
	}



}