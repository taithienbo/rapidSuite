package map;




import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;

import android.widget.ListView;
import android.widget.TextView;



import employee.Employee;

public class MyMapEmployeeListAdapter extends BaseAdapter
{

	
	
		private Employee 			employee;
		private String[] 			employee_fields;
		private LayoutInflater 		mInflater;
		private Context 			context;
		private ListView 			lv;

		
		
		public MyMapEmployeeListAdapter ( Context context, Employee employee, ListView lv)
		{
			mInflater = LayoutInflater.from (context );
			this.context = context;
			this.employee = employee;
			employee_fields = this.context.getResources().getStringArray(R.array.employee_info_for_map);
			this.lv = lv;
		}

		public int getCount() 
		{
			return employee_fields.length;
		}

		
		public Object getItem(int position) 
		{
			// TODO Auto-generated method stub
			return employee_fields[position];
		}

		
		public long getItemId(int position) 
		{
			// TODO Auto-generated method stub
			return employee.getEmployeeId();
		}

		public View getView(int position, View convertView, ViewGroup parent)
		{
			// TODO Auto-generated method stub
			View v = convertView;
			
			ViewHolder holder;
			
			if(v == null)
			{
				v = mInflater.inflate(R.layout.employee_info_with_map_row_layout, null);
				holder = new ViewHolder ( (TextView) v.findViewById(R.id.textView_map_employee_field),
						(TextView) v.findViewById(R.id.textView_map_employee_value));
			}
			else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.field.setText(employee_fields[position]);
			
			String value;
			
			switch(position)
			{
			case 0:		// status
				value = employee.getStatus();
				
				lv.setDividerHeight(1);
				break;
			case 1:		// last status update
				value = employee.getLastStatusUpdate();
				if(value == null)
				{
					value = "Unknown";
				}
				break;
			default:
				value = "error";
			}
			
			holder.value.setText(value);
			return v;
		}

		public Filter getFilter() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		class ViewHolder
		{
			public ViewHolder (TextView field, TextView value)
			{
				this.field = field;
				this.value = value;
			}
			
			
			private TextView field;
			private TextView value;
		}

	}
	
	
	


