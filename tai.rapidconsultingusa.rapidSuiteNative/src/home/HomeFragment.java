package home;

import controller.MainActivity;
import rapidSuite.LoginActivity;
import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment{

	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		super.setHasOptionsMenu(true);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View view = inflater.inflate(R.layout.home_layout, null);
		
	//	View view = inflater.inflate(R.layout., root) 
				return view;
		
	}
	
	

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
	
		inflater.inflate(R.menu.menu_logout, menu);
	
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.menu_logout:
			Intent intent = new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}


	}
}
