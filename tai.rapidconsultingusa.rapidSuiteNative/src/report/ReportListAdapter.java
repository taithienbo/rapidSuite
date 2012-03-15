package report;




import java.util.List;

import tai.rapidconsultingusa.rapidSuiteNative.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ReportListAdapter extends BaseAdapter
{
	
	private Context context;
	private LayoutInflater mInflater;
	private List<Report> report_list;
	
	private Fragment fragment;
	
	private static final String LOG_INFO_TAG = "ReportListAdapter";
	
	public ReportListAdapter (Fragment fragment, List<Report> report_list) 
	{
		this.context = fragment.getActivity();
		this.mInflater = LayoutInflater.from( context );
		this.report_list = report_list;
		this.fragment = fragment;
	}
	

	public int getCount() 
	{
		// TODO Auto-generated method stub
		return report_list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return report_list.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder;
		
		View v = convertView;
		
		if ( v == null )
		{

			v = mInflater.inflate ( tai.rapidconsultingusa.rapidSuiteNative.R.layout.report_row_layout, null );
			holder = new ViewHolder ( (TextView) v.findViewById(R.id.textView_report_item_name));

			v.setTag ( holder );
		}
		else
		{
			holder = (ViewHolder) v.getTag ( );
		}
		
		Log.d(LOG_INFO_TAG, "reportItem is null ? " + (holder.reportItem == null ? 
				"yes" : "no"));
		
		Log.d(LOG_INFO_TAG, "reportList is null ? " + (report_list == null ? 
				"yes" : "no"));
		
		Log.d(LOG_INFO_TAG, "report_list is empty ? " + (report_list.isEmpty() ? 
				"yes" : "no"));
		
		holder.reportItem.setText(Integer.toString(report_list.get(position).getYear()));
		
		
		v.setOnClickListener(new ReportOnClickListener(report_list.get(position)));

		// TODO Auto-generated method stub
		return v;
	}
	
	
	private class ReportOnClickListener implements OnClickListener
	{
		private Report report;
		
		public ReportOnClickListener (Report report)
		{
			this.report = report;
		}
		
		public void onClick(View v) {
			FragmentManager fm = fragment.getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			
			Fragment current_fragment = fm.findFragmentById(R.id.fragment_container);
			
			if (current_fragment == null || 
					current_fragment.getClass().getName() != ReportDetailsFragment.class.getName())
			{
				current_fragment = new ReportDetailsFragment(report);
				ft.replace(R.id.fragment_container, current_fragment);
				ft.addToBackStack(null);
				ft.commit();
			}
			
		}
		
	}

	
	
	private class ViewHolder 
	{
		private TextView reportItem;

		public ViewHolder (TextView reportItem)
		{
			this.reportItem = reportItem;
		}
	}



	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
