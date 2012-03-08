package controller;



import home.HomeFragment;
import inventory.InventoryFragment;

import java.util.Enumeration;

import employee.Employee;
import employee.EmployeeInfoFragment;
import employee.EmployeesFragment;

import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import approval.ApprovalsFragment;

public class MainActivity extends Activity  implements OnModuleItemSelectedListener {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		FragmentTransaction ft = this.getFragmentManager().beginTransaction();
		
		ft.replace(R.id.fragment_container, new HomeFragment());
		ft.commit();
		this.setContentView(R.layout.main_activity_layout);
	}
	
	
	@Override
	public void onResume()
	{
		super.onResume();
		Log.d(LOG_INFO_TAG, "onResume() called");
	}
	
	
	@Override
	public void onRestart()
	{
		super.onRestart();
		Log.d(LOG_INFO_TAG, "onRestart() called");
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

//The commented out codes below are handled in separate fragments

	
	public void onModuleItemSelectedListener(String module_name) {
		// TODO Auto-generated method stub
		Log.i("Module item clicked", module_name + " clicked");

		Fragment module_fragment = (Fragment) getFragmentManager().findFragmentById(R.id.fragment_container);
		FragmentTransaction ft = getFragmentManager().beginTransaction();


		Fragment newFragment = null;

		if(module_name.equals(modules.EMPLOYEES.getValue())){
			newFragment = new EmployeesFragment();
		//	Intent intent = new Intent(MainActivity.this, EmployeesActivity.class);
		//	startActivity(intent);

		}

		else if(module_name.equals(modules.INVENTORY.getValue())){
	//		Log.i(LOG_INFO_TAG, "onModuleItemSelectedListener(): Inventory module clicked.");
			newFragment = new InventoryFragment();
		}
		else if(module_name.equals(modules.APPROVALS.getValue())){
	//		Log.i(LOG_INFO_TAG, "onModuleItemSelectedListener(): Approvals module clicked");
			newFragment = new ApprovalsFragment(PENDING);
		}
		else if(module_name.equals(modules.APPROVALS_HISTORY.getValue())){
	//		Log.i(LOG_INFO_TAG, "onModuleItemSelectedListener(): ApprovalsHistory module clicked");
			newFragment = new ApprovalsFragment(PROCESSED);
		}
		else
		{

		}


		if(newFragment != null){

			ft.replace(R.id.fragment_container, newFragment);
			ft.addToBackStack(null);
			ft.commit();
		}



	}


/**
	public void onModuleItemSelectedListener(Employee employee) {
		// TODO Auto-generated method stub

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.fragment_container, new EmployeeInfoFragment(employee));
		ft.addToBackStack(null);
		ft.commit();

	}
	 **/


	
	private enum modules{


		HOME("home"),
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

	private static final String LOG_INFO_TAG = "MainActivity Info:";

	private static final String PENDING = "Pending";
	private static final String PROCESSED = "Processed";

}