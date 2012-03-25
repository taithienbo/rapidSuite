package report;



import tai.rapidconsultingusa.rapidSuiteNative.R;
import utility_classes.ListSelector;
import android.app.Activity;
import android.app.Fragment;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class ReportDetailsFragment extends Fragment{



	private static final String LOG_INFO_TAG = "ReportDetailsFragment info:";

	private static Report report;



	public ReportDetailsFragment(){};


	public ReportDetailsFragment(Report report)
	{
		ReportDetailsFragment.report = report;
	}

	
	

	// Called when the fragment is associated with its activity
	@Override
	public void onAttach (Activity activity)
	{
		super.onAttach (activity);
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		super.setHasOptionsMenu(true);
	}


	
	// onCreateView() creates and returns the view hierarchy associated with 
	// the fragment.
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, 
			Bundle savedInstanceState)
	{
		View v = inflater.inflate (R.layout.custom_list_layout, viewgroup, false);
		
		ListView report_detail_list = (ListView) v.findViewById(android.R.id.list);
		report_detail_list.setSelector(R.color.transparent);
		report_detail_list.setSelector(new ListSelector(report_detail_list));
		
		report_detail_list.setAdapter( new ReportDetailsListAdapter (this.getActivity(), report));
		
		return v;
	}


	
	// onActivityCreated() tells the fragment that its activity has completed 
	// its own Activity.onCreaate.
	@Override
	public void onActivityCreated (Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}
	
	
	
	// onResume() makes the fragment interacting with the user (based on its 
	// containing activity being resumed).
	@Override
	public void onResume()
	{
		super.onResume();
	//	Log.d(LOG_INFO_TAG, "onResume() called");
	}



	// onPause() fragment is no longer interacting with the user either 
	// because its activity is being paused or a fragment operation is
	// modifying it in the activity.
	@Override
	public void onPause()
	{
		super.onPause();
		Log.d(LOG_INFO_TAG, "onPause() called");
	}


	// onStop() fragment is no longer visible to the user either because its 
	// activity is being stopped or a fragment operation is modifying it in 
	// the activity.
	@Override
	public void onStop()
	{
		super.onStop();
		Log.d(LOG_INFO_TAG, "onStop() called");
	}



	// onDestroyView() allows the fragment to clean up resources associated 
	// with its View.
	@Override 
	public void onDestroyView()
	{
		super.onDestroyView();
	//	Log.d(LOG_INFO_TAG, "onDestroyView() called");
	}
	
	
// 	onDestroy() called to do final cleanup of the fragment's state.
	@Override
	public void onDestroy()
	{
		super.onDestroy();
//		Log.d(LOG_INFO_TAG, "onDestroy() called");
	}
	

	//onDetach() called immediately prior to the fragment no longer being associated with its activity.
		@Override
		public void onDetach()
		{
			super.onDetach();
		//	Log.d(LOG_INFO_TAG, "onDetach() called");
		}

	/**
	 * Called to ask the fragment to save its current dynamic state, so it 
	 * can later be reconstructed in a new instance of its process is 
	 * restarted. If a new instance of the fragment later needs to be created,
	 * the data you place in the Bundle here will be available in the Bundle 
	 * given to onCreate(Bundle), onCreateView(LayoutInflater, ViewGroup, 
	 * Bundle), and onActivityCreated(Bundle).
	 */
	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{	
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{

		super.onCreateOptionsMenu(menu, inflater);

	}


	@Override
	public void onPrepareOptionsMenu(Menu menu) 
	{
		super.onPrepareOptionsMenu(menu);
	}



}
