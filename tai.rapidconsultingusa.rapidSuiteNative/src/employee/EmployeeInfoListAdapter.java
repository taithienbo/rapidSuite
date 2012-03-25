package employee;

import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;



	public class EmployeeInfoListAdapter extends BaseAdapter {

		private String[] employee_info;
		
		private Context context;

		private LayoutInflater mInflater;

		private static final int NUMBER_OF_VIEW_TYPE = 2;
		
		private Employee employee;


		public EmployeeInfoListAdapter(Context context, String[] employee_info, Employee employee) {

		
			// TODO Auto-generated constructor stub

			this.context = context;

			mInflater = LayoutInflater.from(context);
			this.employee_info = employee_info;
			this.employee = employee;
			//		Log.d(LOG_INFO_TAG, "EmployeeInfoListAdapter.constructor called");
		}


		public View getView(int position, View convertView, ViewGroup parent){
			//	Log.d(LOG_INFO_TAG, "EmployeeInfoListAdapter.getView() called");
			View v = convertView;

			ViewHolder holder;

			if(v == null)
			{

				v = mInflater.inflate(R.layout.employee_info_row_layout, null);

				holder = new ViewHolder
						(
								(TextView) v.findViewById(R.id.textView_employee_field), 
								(TextView) v.findViewById(R.id.textView_employee_value),
								context.getResources().getDrawable(R.drawable.rounded_corner_top),
								context.getResources().getDrawable(R.drawable.rounded_corner_bottom),
								context.getResources().getDrawable(R.drawable.rounded_corner)
								);


				v.setTag(holder);

			}
			else
				holder = (ViewHolder) v.getTag();

			Intent intent = null;

			switch(position){
			case 0:		// Employee Id
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(Integer.toString(employee.getEmployeeId()));
				v.setBackgroundDrawable(holder.corners);
				break;


			case 2:		// Email
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getEmail());
				v.setBackgroundDrawable(holder.corners);

				intent = new Intent (android.content.Intent.ACTION_CHOOSER);

				Intent send_intent = new Intent (Intent.ACTION_SEND);
				send_intent.setType("plain/text");

				send_intent.putExtra(Intent.EXTRA_EMAIL, new String[]{employee.getEmail()});
				intent.putExtra(android.content.Intent.EXTRA_INTENT, send_intent);
				break;



			case 4:		// Phone
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getPhoneNumber());

				v.setBackgroundDrawable(holder.corners);

				intent = new Intent (android.content.Intent.ACTION_CHOOSER);

				Intent dial_intent = new Intent (Intent.ACTION_DIAL);

				dial_intent.setData(Uri.parse("tel:" + employee.getPhoneNumber()));
				intent.putExtra(android.content.Intent.EXTRA_INTENT, dial_intent);

				break;

			case 6:		// Position
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getPosition());
				v.setBackgroundDrawable(holder.top_corner);
				break;

			case 7:		// Department
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getDepartment());
				v.setBackgroundDrawable(holder.bottom_corner);
				break;  

			case 9:		// Status
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getStatus());
				v.setBackgroundDrawable(holder.top_corner);
				break;
				
			case 10: 	// Last Status Update
				holder.field.setText(employee_info[position].toString());
				holder.value.setText(employee.getLastStatusUpdate());
				v.setBackgroundDrawable(holder.bottom_corner);
				break;

			default:	//Separator
				v = mInflater.inflate(R.layout.separator_layout, null);
				v.setVisibility(View.INVISIBLE);

				break;


			}

			if (intent != null)
				v.setOnClickListener(new EmployeeOnClickListener(intent, context));

			return v;
		}


		public int getCount() {
			// TODO Auto-generated method stub
			return employee_info.length;
		}


		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return employee_info[arg0];
		}


		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		
		@Override
		public int getViewTypeCount()
		{
			return NUMBER_OF_VIEW_TYPE;
		}
		
		@Override
		public int getItemViewType(int position)
		{
			return BaseAdapter.IGNORE_ITEM_VIEW_TYPE;
		}
		

		private class EmployeeOnClickListener implements OnClickListener
		{
			private Intent intent;

			public EmployeeOnClickListener (Intent intent, Context context)
			{
				this.intent = intent;
			}

			public void onClick(View v) 
			{
				context.startActivity(intent);

			}

		}


	private static class ViewHolder
	{

		private TextView field;
		private TextView value;
		private Drawable top_corner;
		private Drawable bottom_corner;
		private Drawable corners;

		public ViewHolder(TextView field, TextView value,
				Drawable top_corner, Drawable bottom_corner,
				Drawable corners)
		{
			this.field = field;
			this.value = value;
			this.top_corner = top_corner;
			this.bottom_corner = bottom_corner;
			this.corners = corners;

		}
	}
	}