package report;

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




import android.util.Log;

public class ReportDataRetriever extends DBManager{

	private static String LOG_INFO_TAG = "ReportDataRetriever";

	private static final String PHP_Report_DATA_FILE_LINK = "http://www.rapidconsultingusa.com/html5/tai_bo/rapidSuiteNative/revenueInfo.php";



	private static final String YEAR = "year";
	private static final String JANUARY = "january";
	private static final String FEBUARY = "february";
	private static final String MARCH = "march";
	private static final String APRIL = "april";
	private static final String MAY = "may";
	private static final String JUNE = "june";
	private static final String JULY = "july";
	private static final String AUGUST = "august";
	private static final String SEPTEMBER = "september";
	private static final String OCTOBER= "october";
	private static final String NOVEMBER = "november";
	private static final String DECEMBER = "december";



	private static ArrayList<Report> report_list = new ArrayList<Report>();


	private static String getReportData(){

		ArrayList<NameValuePair> nameValuePairs = get_database_settings();


		InputStream is = null;
		//http post
		try
		{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(PHP_Report_DATA_FILE_LINK);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();		
		}
		catch(Exception e)
		{
			Log.e(LOG_INFO_TAG, "getReportDate(): An error occured while trying " +
					"to communicate with the database. \n " +
					"Erorr Description: " + e.toString());
		}

		String result = "";

		//convert response to string
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
				Log.i(LOG_INFO_TAG, "getReportData().line value: " + line);
			}

			is.close();
			result = sb.toString();		
			reader.close();
			Log.i(LOG_INFO_TAG, "ReportDataRetriver.getReportData() succedded");
		}
		catch(Exception e){
			Log.e(LOG_INFO_TAG, "Error converting result " + e.toString());
		}

		return result;
	}


	public static ArrayList<Report> getReportList() {
		if(!report_list.isEmpty())
			return report_list;

		try 
		{
			JSONArray report_data_array = new JSONArray(getReportData());
			//	Log.i(LOG_INFO_TAG, "getListOfReport(): Number of Report found: " + Report_data_array.length());

			for(int i = 0; i < report_data_array.length(); i++)

				report_list.add(getReportObject(report_data_array.getJSONObject(i)));



		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			Log.e(LOG_INFO_TAG, "An error occurred while trying to parse Report Json Data. \n "
					+ "Error Description: " + e.getMessage());
		}
		return report_list;
	}


	private static Report getReportObject(JSONObject jsonObject) {
		// TODO Auto-generated method stub

		Report rep = null;
		
		Log.i(LOG_INFO_TAG, "examing jsonObject. " + jsonObject.toString());

		try{

			int year = Integer.parseInt(jsonObject.getString(YEAR));

			float jan_rev = Float.parseFloat(jsonObject.getString(JANUARY));
			float feb_rev = Float.parseFloat(jsonObject.getString(FEBUARY));
			float mar_rev = Float.parseFloat(jsonObject.getString(MARCH));
			float apr_rev = Float.parseFloat(jsonObject.getString(APRIL));
			float may_rev = Float.parseFloat(jsonObject.getString(MAY));
			float jun_rev = Float.parseFloat(jsonObject.getString(JUNE));
			float jul_rev = Float.parseFloat(jsonObject.getString(JULY));
			float aug_rev = Float.parseFloat(jsonObject.getString(AUGUST));
			float sep_rev = Float.parseFloat(jsonObject.getString(SEPTEMBER));
			float oct_rev = Float.parseFloat(jsonObject.getString(OCTOBER));
			float nov_rev = Float.parseFloat(jsonObject.getString(NOVEMBER));
			float dec_rev = Float.parseFloat(jsonObject.getString(DECEMBER));



			//		Log.i(LOG_INFO_TAG, "getReportObject: longitude parsed as tring: " + longitude);
			//		Log.i(LOG_INFO_TAG, "getReportObject: latitude parsed as tring: " + latitude);

			rep = new Report(year, jan_rev, feb_rev, mar_rev, apr_rev, may_rev, jun_rev, 
					jul_rev, aug_rev, sep_rev, oct_rev, nov_rev, dec_rev);
		}
		catch(JSONException e){
			Log.e(LOG_INFO_TAG, "An error occurs why trying to create Report object. \n" +
					"Error Description: " + e.toString());
		}

		return rep;
	}




}
