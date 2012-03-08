package databaseManager;

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

import android.util.Log;



public class Authentication extends DBManager {


	public static boolean passAuthentication(String userName, String userPassword){

		ArrayList<NameValuePair> nameValuePairs = prepare_values_for_authentication_check(userName, userPassword);

		InputStream is = null;
		//http post
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(PHP_USER_AUTHENTICATION_FILE_LINK);
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
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null){
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();		
		}

		catch(Exception e){
			Log.e("log_tag", "Error converting result " + e.toString());
		}
		return result.startsWith("PERMIT") ? true : false;

	}


	public static ArrayList<NameValuePair> prepare_values_for_authentication_check(String userName, String userPassword) {
		// TODO Auto-generated method stub
		ArrayList<NameValuePair> result = get_database_settings();
		result.add(new BasicNameValuePair(LOGIN_NAME, userName));
		result.add(new BasicNameValuePair(LOGIN_PASS, userPassword));

		return result;
	}

	private static final String LOGIN_NAME = "login_name";
	private static final String LOGIN_PASS = "login_pass";
	
	private static final String PHP_USER_AUTHENTICATION_FILE_LINK = "http://www.rapidconsultingusa.com/html5/tai_bo/login.php";
}
