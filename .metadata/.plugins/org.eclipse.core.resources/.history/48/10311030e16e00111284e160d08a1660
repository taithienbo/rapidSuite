package report;




import java.util.List;

import tai.rapidconsultingusa.rapidSuiteNative.R;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class ReportListAdapter extends BaseAdapter
{
	
	private Context context;
	private LayoutInflater mInflater;
	private List<Report> report_list;
	
	private static final String LOG_INFO_TAG = "ReportListAdapter";
	
	public ReportListAdapter (Context context, List<Report> report_list) 
	{
		this.context = context;
		this.mInflater = LayoutInflater.from( context );
		this.report_list = report_list;
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

		// TODO Auto-generated method stub
		return v;
	}

	
	
	private class ViewHolder 
	{
		private TextView reportItem;

		public ViewHolder (TextView reportItem)
		{
			this.reportItem = reportItem;
		}
	}

}
