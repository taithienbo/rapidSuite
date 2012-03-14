package map;


import inventory.Inventory;

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
import employee.EmployeeInfoFragment;

public class MapViewActivity extends MapActivity{


	LinearLayout linearLayout;
	MapView mapView;

	List<Overlay> mapOverlays;
	Drawable drawable;
	MapItemizedOverlay itemizedOverlay;

	ListView lv;

	

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



		String caller = b.getString("caller");

		// Initialize the list to display info
		lv = (ListView) findViewById(R.id.listView_map_info_list);
		
		if( caller.equals(EmployeeInfoFragment.class.getName()) )
		{
			Employee employee;
		
			employee = (Employee) b.getSerializable(Employee.RETRIEVAL_KEY);
		
		
			lv.setAdapter(new MyMapEmployeeListAdapter(this, employee, lv));
		}
		
		else	// The caller is InventoryInfoFragment
		{
			Inventory inventory;
			
			inventory = (Inventory) b.getSerializable(Inventory.INVENTORY_RETRIEVAL_KEY);
			lv.setAdapter(new MyMapInventoryListAdapter(this, inventory, lv));
			
			
		}
		
	
		//	Log.d(LOG_INFO_TAG, "Testing Serialized Employee, The name is: " + employee.getName());


		//	Log.d(LOG_INFO_TAG, "testing employee_fields, it is nulll? " + employee_fields == null ? "yes" : "no");
	}





	private void initializeMap(int latitude, int longitude)
	{
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		itemizedOverlay = new MapItemizedOverlay(drawable);




		GeoPoint point = new GeoPoint(latitude, longitude);
		OverlayItem overlayitem = new OverlayItem(point, "", "");

		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);

	}

	private static final String LOG_INFO_TAG = "MapViewActivity Info";

}
