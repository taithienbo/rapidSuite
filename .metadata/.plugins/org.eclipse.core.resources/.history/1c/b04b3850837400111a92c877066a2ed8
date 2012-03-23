package map;


import tai.rapidconsultingusa.rapidSuiteNative.R;

import inventory.Inventory;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;

import android.widget.ListView;
import android.widget.TextView;

public class MyMapInventoryListAdapter extends BaseAdapter 
{

	private Inventory 			inventory;
	private String[] 			inventory_fields;
	private LayoutInflater 		mInflater;
	private Context 			context;
	private ListView 			lv;

	private static final String LOG_INFO = "MyMapInventoryListAdapter";
	
	public MyMapInventoryListAdapter(Context context,
			Inventory inventory, ListView lv) 
	{
		this.context = context;

		
		mInflater = LayoutInflater.from ( this.context );
	
		this.inventory = inventory;
		inventory_fields = this.context.getResources().getStringArray(R.array.inventory_info_for_map);
		
		this.lv = lv;	}


	public int getCount() 
	{
		return inventory_fields.length;
	}

	
	public Object getItem(int position) 
	{
		// TODO Auto-generated method stub
		return inventory_fields[position];
	}

	
	public long getItemId(int position) 
	{
		// TODO Auto-generated method stub
		return inventory.getId();
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		View v = convertView;
		
		ViewHolder holder;
		
		if(v == null)
		{
			v = mInflater.inflate(R.layout.inventory_info_with_map_row_layout, null);
			holder = new ViewHolder ( (TextView) v.findViewById(R.id.textView_map_inventory_field),
					(TextView) v.findViewById(R.id.textView_map_inventory_value));
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
	
		holder.field.setText(inventory_fields[position]);
		
		String value;
		
		switch(position)
		{
		case 0:		// Availability
			value = inventory.getAvailability();
			lv.setDividerHeight(10);
			break;
		case 1:		// last location update
			value = inventory.getLastLocationUpdate();
			
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
