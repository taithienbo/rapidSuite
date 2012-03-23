package report;






import tai.rapidconsultingusa.rapidSuiteNative.R;

import android.content.Context;
import android.graphics.drawable.Drawable;

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

	
	@Override
	public int getItemViewType(int position)
	{
		return BaseAdapter.IGNORE_ITEM_VIEW_TYPE;
	}
	
	@Override
	public int getViewTypeCount ()
	{
		return 2;		// has 2 type of view for all items
						// One for regular item and one for separator
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder;

		View v = convertView;

		if ( v == null )
		{

			v = mInflater.inflate ( R.layout.report_details_row_layout, null );
			holder = new ViewHolder ( (TextView) v.findViewById(R.id.textView_report_detail_field), 
					(TextView) v.findViewById(R.id.textView_report_details_value),
					(Drawable) context.getResources().getDrawable(R.drawable.rounded_corner_top),
					(Drawable) context.getResources().getDrawable(R.drawable.rounded_corner_bottom),
					(Drawable) context.getResources().getDrawable(R.drawable.rounded_corner));

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
			v.setBackgroundDrawable(holder.top_corner);
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
			v.setBackgroundDrawable(holder.bottom_corner);
			break;
		case 12:	// Separator
			v = mInflater.inflate(R.layout.separator_layout,null);
			v.setVisibility(View.INVISIBLE);
			break;
		case 13:	// Total Revenue 
			value.append(report.getTotalrev());
			v.setBackgroundDrawable(holder.all_corners);
			break;
		}
		
		if (position != 12)
			holder.value.setText(value);

		// TODO Auto-generated method stub
		return v;
	}


	
	private class ViewHolder 
	{
		private TextView field;
		private TextView value;
		private Drawable top_corner;
		private Drawable bottom_corner;
		private Drawable all_corners;

		public ViewHolder ( TextView field, TextView value,
				Drawable top_corner, Drawable bottom_corner,
				Drawable all_corners)
		{
			this.field = field;
			this.value = value;
			this.top_corner = top_corner;
			this.bottom_corner = bottom_corner;
			this.all_corners = all_corners;
		}
	}

}
