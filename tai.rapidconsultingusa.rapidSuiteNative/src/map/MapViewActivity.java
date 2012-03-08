package map;


import java.util.List;

import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

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
			
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		itemizedOverlay = new MapItemizedOverlay(drawable);
		
		Bundle b = getIntent().getExtras();
		
		double latitude = 0;
		double longitude = 0;
		if(b != null){
			latitude =  b.getFloat(LATITUDE_KEY) * Math.pow(10,6);
			longitude = b.getFloat(LONGITUDE_KEY) * Math.pow(10, 6);
		}
		
	
		
		Log.i(LOG_INFO_TAG, "value of latitude received is: " + latitude);
		Log.i(LOG_INFO_TAG, "value of longitude received is: " + longitude);
		
	
		
		GeoPoint point = new GeoPoint((int)latitude, (int) longitude);
		OverlayItem overlayitem = new OverlayItem(point, "", "");
	
		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
	}
	
	
	
	
	private static final String LOG_INFO_TAG = "MapViewActivity Info";

}
