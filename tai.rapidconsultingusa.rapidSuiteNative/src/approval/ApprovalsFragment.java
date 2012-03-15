package approval;



import java.util.ArrayList;
import java.util.List;


import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ApprovalsFragment extends ListFragment
{

	public ApprovalsFragment(){}
	
	public ApprovalsFragment(String itemStatus)
	{
		this.itemStatus = itemStatus;
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState){

		
		//	 setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List));
		View view = inflater.inflate(R.layout.approvals_list_layout, null);
		ListView lv = (ListView)view.findViewById(android.R.id.list);

		List<Approvals> approvals_list = ApprovalsDataRetriever.getListOfApprovals(itemStatus);
		
		lv.setAdapter(new ApprovalsListAdapter<Approvals>(getActivity().getBaseContext(), R.layout.approvals_row_layout,
				R.id.textView_approvals_item_name, approvals_list));
		
		return view;
	}

	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		ListView lv = getListView();
		lv.setItemChecked(position, true);
		lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	
		lv.setClickable(true);
		lv.setSelection(position);
		
		FragmentManager fm = this.getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		
		Approvals a = ApprovalsDataRetriever.getListOfApprovals(itemStatus).get(position);
		
		ft.replace(R.id.fragment_container, new ApprovalsInfoFragment(a));
		ft.addToBackStack(null);
		ft.commit();
	}





	public class ApprovalsListAdapter<T> extends ArrayAdapter<T> 
	{

		private List<Approvals> approvals_list;
		private int textViewResourceId;
		private int resource;
		private Context context;



		public ApprovalsListAdapter(Context context, int resource,
				int textViewResourceId, List<T> approvals_list) 
		{
			
			super(context, resource, textViewResourceId, approvals_list);
			// TODO Auto-generated constructor stub
		
			this.context = context;
			this.textViewResourceId = textViewResourceId;
			this.resource = resource;
			this.approvals_list = (ArrayList<Approvals>) approvals_list;
		
		}


		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View v = convertView;

			if(v == null){
				LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = li.inflate(R.layout.approvals_row_layout, null);
			}

			ImageView item_icon = (ImageView) v.findViewById(R.id.imageView_approvals_icon);
			
			String status = approvals_list.get(position).getStatus();		// The status of the item at position
			
			if(status.equals(APPROVED))
				item_icon.setImageDrawable(v.getResources().getDrawable(R.drawable.approvals_green));
			
			else if(status.equals(REJECTED))
			{
				item_icon.setImageDrawable(v.getResources().getDrawable(R.drawable.approvals_red));
			}
			else
				item_icon.setImageDrawable(v.getResources().getDrawable(R.drawable.approvals_gray));
			
		
			TextView item_name = (TextView) v.findViewById(R.id.textView_approvals_item_name);
				
			item_name.setText(approvals_list.get(position).getItemName());
		

			return v;
		}
	}
	
	
	private static final String LOG_INFO_TAG = "ApprovalsFragment Info";
	
	/**
	 * @param itemStatus the status value defined in the database 
	 */
	private String itemStatus;
	
	private static final String APPROVED = "Approved";
	private static final String REJECTED = "Rejected";
	private static final String PENDING = "Pending";
}
