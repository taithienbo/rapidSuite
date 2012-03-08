package employee;



import imageDownloader.UrlImageViewHelper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import controller.OnModuleItemSelectedListener;




import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
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

public class EmployeesFragment extends ListFragment {


	
	private OnModuleItemSelectedListener module_item_listener;





	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState){


		//	 setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List));
		View view = inflater.inflate(R.layout.employee_list_layout, null);
		ListView lv = (ListView)view.findViewById(android.R.id.list);
		
	
		List<Employee> employee_list = EmployeeDataRetriever.getListOfEmployees();
		
		lv.setAdapter(new EmployeeListAdapter<Employee>(getActivity().getBaseContext(), R.layout.employee_row_layout, R.id.textView_employee_row, 
				employee_list));
	
		return view;
	}
	

	@Override 
	public void onListItemClick(ListView l, View v, int position, long id){

		ListView lv = getListView();
		lv.setItemChecked(position, true);
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lv.setItemChecked(position, true);
		lv.setClickable(true);
		lv.setSelection(position);
		
		Employee e = EmployeeDataRetriever.getListOfEmployees().get(position);
		
	//	module_item_listener.onModuleItemSelectedListener(e);
		FragmentManager fm = this.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
	//	Fragment fragment_container = (Fragment)fm.findFragmentById(R.id.fragment_container);
		
		ft.replace(R.id.fragment_container, new EmployeeInfoFragment(e));
		ft.addToBackStack(null);
		ft.commit();
		
	//	Toast.makeText(getActivity(), e.getName(), Toast.LENGTH_LONG).show();

	}


	@Override
	public void onViewCreated (View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view,  savedInstanceState);
		Log.d(LOG_INFO_TAG, "onViewCreated() called");

	}
	
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		Log.d(LOG_INFO_TAG, "onAttach() called");
		try{
			module_item_listener = (OnModuleItemSelectedListener) activity;
		}
		catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + " must implement OnModuleItemSelectedLister");
		}

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
	

	public class EmployeeListAdapter<T> extends ArrayAdapter<T> {

		private List<Employee> employee_list;
		private int textViewResourceId;
		private int resource;
		private Context context;



		public EmployeeListAdapter(Context context, int resource,
				int textViewResourceId, List<T> employee_list) {
			super(context, resource, textViewResourceId, employee_list);
			// TODO Auto-generated constructor stub
		
			this.context = context;
			this.textViewResourceId = textViewResourceId;
			this.resource = resource;
			this.employee_list = (ArrayList<Employee>) employee_list;
			Log.i("EmployeeFragment Info", "EmployeeListAdapter.constructor called");
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent){
		//	Log.i("EmployeeFragment Info", "EmployeeListAdapter.getView() called");
			View v = convertView;

			if(v == null){
				LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = li.inflate(R.layout.employee_row_layout, null);
			}

		
			TextView employee_name = (TextView) v.findViewById(R.id.textView_employee_row);
				
			employee_name.setText(employee_list.get(position).getName());
			
			ImageView employee_image = (ImageView)v.findViewById(R.id.imageView_employee);
			UrlImageViewHelper.setUrlDrawable(employee_image, employee_list.get(position).getPictureLink());

			return v;
		}
		
	//	private static final String LOG_INFO_TAG = "EmploeesFragmentInfo";
		
	}
	private static final String LOG_INFO_TAG = "EmployeesFragmentInfo";
}