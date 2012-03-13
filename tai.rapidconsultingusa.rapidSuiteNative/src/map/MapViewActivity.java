package map;


import java.util.List;

import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapViewActivity extends MapActivity{

	
	LinearLayout linearLayout;
	MapView mapView;
	
	List<Overlay> mapOverlays;
	Drawable drawable;
	MapItemizedOverlay itemizedOverlay;
	
	
	public static final String LONGITUDE_KEY = "longitude";
	public static final String LATITUDE_KEY = "latitude";
	
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_layout);
			
		
		// Initialize the map 
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		itemizedOverlay = new MapItemizedOverlay(drawable);
		
		Bundle b = getIntent().getExtras();
		
		double latitude = 0;
		double longitude = 0;
	
		if(b != null)
		{
			latitude =  b.getFloat(LATITUDE_KEY) * Math.pow(10,6);
			longitude = b.getFloat(LONGITUDE_KEY) * Math.pow(10, 6);
		}
		
		
		GeoPoint point = new GeoPoint((int)latitude, (int) longitude);
		OverlayItem overlayitem = new OverlayItem(point, "", "");
	
		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
		
		
		// Initialize the list to display info
		ListView lv = (ListView) findViewById(R.id.listView_map_info);
		lv.setAdapter(new MyMapInfoListAdapter());
	
		
		
	}
	
	
	private class MyMapInfoListAdapter extends BaseAdapter
	{

		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	private static final String LOG_INFO_TAG = "MapViewActivity Info";

}
