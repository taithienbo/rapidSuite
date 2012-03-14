package map;


import java.util.List;

import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import employee.Employee;

public class MapViewActivity extends MapActivity{


	LinearLayout linearLayout;
	MapView mapView;

	List<Overlay> mapOverlays;
	Drawable drawable;
	MapItemizedOverlay itemizedOverlay;
	
	ListView lv;
	
	private static Employee employee;
	private static String[] employee_fields;


	public static final String LONGITUDE_KEY = "longitude";
	public static final String LATITUDE_KEY = "latitude";


	@Override
	protected boolean isRouteDisplayed() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);

		Bundle b = getIntent().getExtras();

		// Initialize the map 
		
		int latitude = 0;
		int longitude = 0;

		if(b != null)
		{
			latitude =   (int) (b.getFloat(LATITUDE_KEY) * Math.pow(10,6));
			longitude = (int) (b.getFloat(LONGITUDE_KEY) * Math.pow(10, 6));
		}

		initializeMap(latitude, longitude);
		
		employee = (Employee) b.getSerializable(Employee.RETRIEVAL_KEY);
		employee_fields = this.getResources().getStringArray(R.array.employee_info_for_map);
		
		// Initialize the list to display info
		lv = (ListView) findViewById(R.id.listView_map_info_list);
		lv.setAdapter(new MyMapInfoListAdapter (this));
		
		Log.d(LOG_INFO_TAG, "Testing Serialized Employee, The name is: " + employee.getName());

		
		Log.d(LOG_INFO_TAG, "testing employee_fields, it is nulll? " + employee_fields == null ? "yes" : "no");
	}




	private class MyMapInfoListAdapter extends BaseAdapter implements Filterable
	{
		private LayoutInflater 		mInflater;
		private Context 			context;
		
		
		public MyMapInfoListAdapter ( Context context )
		{
			mInflater = LayoutInflater.from (context );
			this.context = context;
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

		public View getView(int position, View convertView, ViewGroup parent) {
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
	

	private void initializeMap(int longitude, int latitude)
	{
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		itemizedOverlay = new MapItemizedOverlay(drawable);

		Bundle b = getIntent().getExtras();



		GeoPoint point = new GeoPoint(latitude, longitude);
		OverlayItem overlayitem = new OverlayItem(point, "", "");

		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);

	}

	private static final String LOG_INFO_TAG = "MapViewActivity Info";

}
