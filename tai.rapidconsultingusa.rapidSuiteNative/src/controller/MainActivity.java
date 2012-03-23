package controller;



import java.io.Serializable;

import tai.rapidconsultingusa.rapidSuiteNative.R;


import inventory.InventoryFragment;

import employee.EmployeesFragment;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

import android.app.FragmentTransaction;



import android.os.Bundle;
import android.util.Log;

import approval.ApprovalsFragment;

public class MainActivity extends Activity  implements OnItemSelectedListener 
{

	// Variables for restoring fragment after a configuration change


	private static final String FRAGMENT_RETRIEVAL_KEY = "myFragment";


	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.main_activity_layout);
		Log.d(LOG_INFO_TAG, "MainActivity.onCreate() called");
		
		ActionBar mab = getActionBar();
	
	
	}



	@Override
	public void onStart()
	{
		super.onStart();
		Log.d(LOG_INFO_TAG, "MainActivity.onStart() called");
	}


	@Override
	public void onSaveInstanceState (Bundle outState)
	{
	
		super.onSaveInstanceState(outState);
	}


	@Override
	public void onRestoreInstanceState (Bundle bundle)
	{
		super.onRestoreInstanceState(bundle);
		Log.d(LOG_INFO_TAG, "MainActivity.onRestoreInstanceState called");

	
	}


	@Override
	public void onResume()
	{
		super.onResume();
		Log.d(LOG_INFO_TAG, "MainActivity.onResume() called");

	
	}


	@Override
	public void onPause()
	{
		super.onPause();
		Log.d(LOG_INFO_TAG, "MainActivity.onPause() called");
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
		Log.d(LOG_INFO_TAG, "onDestroy() called");
	}


	@Override
	public void onRestart()
	{
		super.onRestart();
		Log.d(LOG_INFO_TAG, "MainActivity.onCRestart() called");
	}




	//The commented out codes below are handled in separate fragments


	public void onModuleItemSelectedListener(String module_name) 
	{
		// TODO Auto-generated method stub
		Log.i("Module item clicked", module_name + " clicked");

		Fragment module_fragment = (Fragment) getFragmentManager().findFragmentById(R.id.fragment_container);
		FragmentTransaction ft = getFragmentManager().beginTransaction();


		Fragment newFragment = null;

		if(module_name.equals(modules.EMPLOYEES.getValue()))
		{
			newFragment = new EmployeesFragment();
		}

		else if(module_name.equals(modules.INVENTORY.getValue()))
		{
			newFragment = new InventoryFragment();
		}
		else if(module_name.equals(modules.APPROVALS.getValue()))
		{
			newFragment = new ApprovalsFragment(PENDING);
		}
		else if(module_name.equals(modules.APPROVALS_HISTORY.getValue()))
		{
			newFragment = new ApprovalsFragment(PROCESSED);
		}
		else {}



		if(newFragment != null)
		{

			ft.replace(R.id.fragment_container, newFragment);
			ft.addToBackStack(null);
			ft.commit();
		}



	}


	public void onFragmentSelectedListener(Fragment fragment) 
	{
		FragmentManager fm = this.getFragmentManager();
		Fragment current_fragment = fm.findFragmentById(R.id.fragment_container);

		String cur_frag_class_name;
		
		if (current_fragment == null 
				||  (cur_frag_class_name = current_fragment.getClass().getName()).equals
						(ApprovalsFragment.class.getName()) 
				|| !cur_frag_class_name.
						equals(fragment.getClass().getName()))
		{
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.fragment_container, fragment);
			ft.addToBackStack(null);
			ft.commit();
		}

	}



	private enum modules
	{

		HOME("home"),
		EMPLOYEES ("Employees"),
		INVENTORY("Inventory"),
		APPROVALS("Approvals"),
		APPROVALS_HISTORY("Approvals History"),
		REPORTS("Reports"),
		SETTINGS("Settings");


		private final String module;

		public String getValue()
		{
			return module;
		}


		modules(String name)
		{
			this.module = name;
		}
	}

	private static final String LOG_INFO_TAG = "MainActivity Info:";

	private static final String PENDING = "Pending";
	private static final String PROCESSED = "Processed";


}
