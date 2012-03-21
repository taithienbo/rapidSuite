package inventory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import databaseManager.DBManager;

import employee.Employee;


import android.util.Log;

public class InventoryDataRetriever extends DBManager{

	private static String LOG_INFO_TAG = "InventoryDataRetriever Info";

	private static final String PHP_INVENTORY_DATA_FILE_LINK = "http://www.rapidconsultingusa.com/html5/tai_bo/rapidSuiteNative/inventory_info.php";


	// Inventory Database fields
	// id, code, name, category, lastUpdate, manufacturer, wholesalePrice,
	// msrp, availability, addrno, street, suite, city, state, zip, 
	// latitude, longitude 

	private static final String ID = "id";
	private static final String CODE = "code";
	private static final String NAME = "name";
	private static final String CATEGORY = "category";
	private static final String LAST_UPDATE = "lastUpdate";
	private static final String MANUFACTURER = "manufacturer";
	private static final String WHOLESALE_PRICE = "wholesalePrice";
	private static final String MSRP = "msrp";
	private static final String AVAILABILITY = "availability";
	private static final String ADDRNO = "addrno";
	private static final String STREET = "street";
	private static final String SUITE = "suite";
	private static final String CITY = "city";
	private static final String STATE = "state";
	private static final String ZIP = "zip";
	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";


	private static ArrayList<Inventory> inventory_list = new ArrayList<Inventory>();


	private static String getInventoryData(){

		ArrayList<NameValuePair> nameValuePairs = get_database_settings();


		InputStream is = null;
		//http post
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(PHP_INVENTORY_DATA_FILE_LINK);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();		
		}
		catch(Exception e)
		{
			Log.e(LOG_INFO_TAG, "getInventoryDate(): An error occured while trying " +
					"to communicate with the database. \n " +
					"Erorr Description: " + e.toString());
		}

		String result = "";

		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");

			}

			is.close();
			result = sb.toString();		
			reader.close();
		//	Log.i(LOG_INFO_TAG, "InventoryDataRetriver.getInventoryData() succedded");
		}
		catch(Exception e)
		{
			Log.e(LOG_INFO_TAG, "Error converting result " + e.toString());
		}

		return result;
	}


	public static ArrayList<Inventory> getListOfInventoriess()
{
		if(!inventory_list.isEmpty())
			return inventory_list;

		try {
			JSONArray inventory_data_array = new JSONArray(getInventoryData());
		
			for(int i = 0; i < inventory_data_array.length(); i++)
			
				inventory_list.add(getInventoryObject(inventory_data_array.getJSONObject(i)));
			

		} catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			Log.e(LOG_INFO_TAG, "An error occurred while trying to parse Inventory Json Data. \n "
					+ "Error Description: " + e.getMessage());
		}
		return inventory_list;
	}


	private static Inventory getInventoryObject(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
		Log.d(LOG_INFO_TAG, "getInventoryObject(): jsonObject details: " + jsonObject.toString());
		Inventory inv = null;
		// Inventory Database fields
		// id, code, name, category, lastUpdate, manufacturer, wholesalePrice,
		// msrp, availability, addrno, street, suite, city, state, zip, 
		// latitude, longitude 
		try
		{

			int id = jsonObject.getInt(ID);

			String code = jsonObject.getString(CODE);
			String name = jsonObject.getString(NAME);
			String category = jsonObject.getString(CATEGORY);
			String lastUpdate = jsonObject.getString(LAST_UPDATE);
			String manufacturer = jsonObject.getString(MANUFACTURER);
			String wholesalePrice = jsonObject.getString(WHOLESALE_PRICE);
			Log.d(LOG_INFO_TAG ,"getInventoryObject(): wholesalePrice parsed as: " + wholesalePrice);
			
			String msrp = jsonObject.getString(MSRP);
			String availability = jsonObject.getString(AVAILABILITY);

			String address = jsonObject.getString(ADDRNO) + " " + jsonObject.getString(STREET)
					+ ", " + jsonObject.getString(CITY)
					+ ", " + jsonObject.getString(STATE) + " " + jsonObject.getString(ZIP);

			String longitude = jsonObject.getString(LONGITUDE);
			String latitude = jsonObject.getString(LATITUDE);
			
			inv = new Inventory(id, code, name, category, lastUpdate, manufacturer, wholesalePrice, 
					msrp, availability,	address, latitude, longitude);
		}
		
		catch(JSONException e)
		{
			Log.e(LOG_INFO_TAG, "An error occurs why trying to create Inventory object. \n" +
					"Error Description: " + e.toString());
		}

		return inv;
	}




}
