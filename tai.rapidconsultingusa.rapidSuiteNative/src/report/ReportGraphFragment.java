package report;



import java.util.ArrayList;


import tai.rapidconsultingusa.rapidSuiteNative.R;
import utility_classes.ListSelector;
import android.app.Activity;
import android.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageView;
import android.widget.ListView;



public class ReportGraphFragment extends Fragment
{


	// Called when the fragment is associated with its activity
	@Override
	public void onAttach (Activity activity)
	{
		super.onAttach (activity);
	}
	
	
	// onCreate() called to do initial creation of the fragment.
	@Override
	public void onCreate ( Bundle savedInstanceState )
	{
		super.onCreate (savedInstanceState);
		
	//	Log.d(LOG_INFO_TAG, "onCreate() called");
		//setContentView (R.layout.report_list_layout);
	}

	
	// onCreateView() creates and returns the view hierarchy associated with
	// the fragment.
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
        

		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.bar_graph_layout, container, false);
		
	
		ImageView barGraph = (ImageView) v.findViewById(R.id.imageView_barGraph);
		barGraph.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.zero_six_bargraph_gradient));
        return v;
    }
	
	
	// onActivityCreated() tells the fragment that its activity has completed 
	// its own Activity.onCreaate.
	@Override
	public void onActivityCreated (Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}
	
	
	// makes the fragment visible to the user (based on its containing activity being started).
	@Override
	public void onStart()
	{
		super.onStart();
	}
	
	
	// onResume() makes the fragment interacting with the user (based on its 
	// containing activity being resumed).
	@Override
	public void onResume()
	{
		super.onResume();
		Log.d(LOG_INFO_TAG, "onResume() called");
	}
	

	
	// onPause() fragment is no longer interacting with the user either because its activity 
	// is being paused or a fragment operation is modifying it in the activity.
	@Override
	public void onPause()
	{
		super.onPause();
		Log.d(LOG_INFO_TAG, "onPause() called");
	}
	
	
	// onStop() fragment is no longer visible to the user either because its activity 
	// is being stopped or a fragment operation is modifying it in the activity.
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
		Log.d(LOG_INFO_TAG, "onDestroyView() called");
	}
	
	
	// 	onDestroy() called to do final cleanup of the fragment's state.
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d(LOG_INFO_TAG, "onDestroy() called");
	}
	
	
	//onDetach() called immediately prior to the fragment no longer being associated with its activity.
	@Override
	public void onDetach()
	{
		super.onDetach();
		Log.d(LOG_INFO_TAG, "onDetach() called");
	}
	
	private static final String LOG_INFO_TAG = "ReportFragment Info";
}
