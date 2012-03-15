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


public class ReportDetailsListAdapter extends BaseAdapter
{
	
	private Context context;
	private LayoutInflater mInflater;
	private String[] report_fields;
	
	private Report report;
	
	private static final String LOG_INFO_TAG = "ReportDetailsAdapter";
	
	public ReportDetailsListAdapter (Context context, Report report) 
	{
		this.context = context;
		this.mInflater = LayoutInflater.from( context );
		this.report_fields = context.getResources().getStringArray(R.array.reports_details_array);
		this.report = report;
	}
	

	public int getCount() 
	{
		// TODO Auto-generated method stub
		return report_fields.length;
	}

	public Object getItem(int position) 
	{
		// TODO Auto-generated method stub
		return report_fields[position];
	}

	public long getItemId(int position) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder;
		
		View v = convertView;
		
		if ( v == null )
		{

			v = mInflater.inflate ( tai.rapidconsultingusa.rapidSuiteNative.R.layout.report_details_row_layout, null );
			holder = new ViewHolder ( (TextView) v.findViewById(R.id.textView_report_detail_field), 
					(TextView) v.findViewById(R.id.textView_report_details_value));

			v.setTag ( holder );
		}
		else
		{
			holder = (ViewHolder) v.getTag ( );
		}
		
		
		holder.field.setText(report_fields[position]);
		
		StringBuilder value = new StringBuilder ("$");
		
		switch (position)
		{
		case 0:		// January 
			value.append(report.getJanRev());
			break;
		case 1:		// February 
			value.append(report.getFebRev());
			break;
		case 2:		// March 
			value.append(report.getMarRev());
			break;
		case 3:		// April 
			value.append(report.getAprRev());
			break;
		case 4:		// May
			value.append(report.getMayRev());
			break;
		case 5:		// Jun 
			value.append(report.getJunRev());
			break;
		case 6:		// July 
			value.append(report.getJulRev());
			break;
		case 7:		// August 
			value.append(report.getAugRev());
			break;
		case 8:		// September 
			value.append(report.getSepRev());
			break;
		case 9:		// October 
			value.append(report.getOctRev());
			break;
		case 10:	// November 
			value.append(report.getNovRev());
			break;
		case 11:	// Dec 
			value.append(report.getDecRev());
			break;
		case 12:	// Total Revenue 
			value.append(report.getTotalrev());
			break;
		}

		holder.value.setText(value);
		
		// TODO Auto-generated method stub
		return v;
	}

	
	
	private class ViewHolder 
	{
		private TextView field;
		private TextView value;

		public ViewHolder ( TextView field, TextView value )
		{
			this.field = field;
			this.value = value;
		}
		
		
	
	}

}