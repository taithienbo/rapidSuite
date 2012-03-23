package settings;

import controller.OnItemSelectedListener;
import map.MapViewActivity;
import employee.Employee;
import tai.rapidconsultingusa.rapidSuiteNative.R;
import about.AboutFragment;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SettingsFragment extends PreferenceFragment
{
	
	private OnItemSelectedListener mListener;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.layout.settings_layout);
		super.setHasOptionsMenu(true);
	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_about, menu);

	}

	
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);

		mListener = (OnItemSelectedListener) activity;

	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		switch(item.getItemId()){
		case R.id.menu_about:
			
			Fragment about_fragment = new AboutFragment();
			mListener.onFragmentSelectedListener(about_fragment);
		
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
