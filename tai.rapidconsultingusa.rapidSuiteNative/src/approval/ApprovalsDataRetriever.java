package approval;

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
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import databaseManager.DBManager;

import android.util.Log;



public class ApprovalsDataRetriever extends DBManager{

	private static final String LOGINFOTAG = "ApprovalsDataRetriever";
	
	
	protected static final String PHP_APPROVALS_DATA_FILE_LINK = 
			"http://www.rapidconsultingusa.com/html5/tai_bo/rapidSuiteNative/approvalsInfo.php";
	
	
	// Approvals Database fields:
	/**
	 *  id, date, code, item, requestorFirstName, requestorLastName, quantity, 
		 cost, note, status   
	 */
	
	private static final String ID = "id";
	private static final String DATE = "date";
	private static final String CODE = "code";
	private static final String ITEM = "item";
	private static final String REQUESTOR_FIRST_NAME = "requestorFirstName";
	private static final String REQUESTOR_LAST_NAME = "requestorLastName";
	private static final String QUANTITY = "quantity";
	private static final String COST = "cost";
	private static final String NOTE = "note";
	private static final String STATUS = "status";
	
	protected static final String REQUEST = "request";
	
	private static ArrayList<Approvals> approvals_list = new ArrayList<Approvals>();
	
	
	private static String getApprovalsData(String itemStatus)
	{
		
		Log.d(LOGINFOTAG, "getApprovalsData(): iemStatus passed is: " + itemStatus);
		ArrayList<NameValuePair> nameValuePairs = get_database_settings();
		nameValuePairs.add(new BasicNameValuePair(REQUEST, itemStatus));


		InputStream is = null;
		//http post
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(PHP_APPROVALS_DATA_FILE_LINK);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();		
		}
		catch(Exception e){
			Log.e("log_tag", "Error getting result " + e.toString());
		}

		String result = "";

		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null){
				sb.append(line + "\n");
				Log.i(LOGINFOTAG, "getApprovalsData().line value: " + line);
			}

			is.close();
			result = sb.toString();		
			reader.close();
			Log.i(LOGINFOTAG, "getApprovalseData() succedded");
		}
		catch(Exception e)
		{
			Log.e(LOGINFOTAG, "Error converting result " + e.toString());
		}

		return result;
	}
	
	
	public static ArrayList<Approvals> getListOfApprovals(String itemStatus)
	{
	
		Log.d(LOGINFOTAG, "getListOfAPpprovals(): itemStatus passed: " + itemStatus);
		approvals_list.clear();
		
		
		try{
			JSONArray approvals_data_array = new JSONArray(getApprovalsData(itemStatus));
		
			for(int i = 0; i < approvals_data_array.length(); i++)
			
				approvals_list.add(getApprovalsObject(approvals_data_array.getJSONObject(i)));
			
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			Log.e(LOGINFOTAG, "An error occurred while trying to parse Approvals Json Data. \n "
					+ "Error Description: " + e.getMessage());
		}
		
		return approvals_list;
	}


	private static Approvals getApprovalsObject(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		Approvals approvals = null;
		Log.d(LOGINFOTAG, "getApprovalsObject(): jsonObject details: " + jsonObject.toString());
		try {
			// Approvals Database fields:
			/**
			 *  id, date, code, item, requestorFirstName, requestorLastName, quantity, 
				 cost, note, status   
			 */
			approvals = new Approvals(jsonObject.getInt(ID), jsonObject.getString(DATE), 
					jsonObject.getString(CODE), jsonObject.getString(ITEM), jsonObject.getString(REQUESTOR_FIRST_NAME)
					+ " " + jsonObject.getString(REQUESTOR_LAST_NAME), jsonObject.getString(QUANTITY),
					jsonObject.getString(COST), jsonObject.getString(NOTE), jsonObject.getString(STATUS));
			
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e(LOGINFOTAG, "An error occurs why trying to create approval object. \n" +
					"Error Description: " + e.toString());
		}

		
	

		return approvals;
	}
	
	
	
}
