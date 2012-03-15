package approval;

import java.io.InputStream;
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

public class ApprovalsItemStatusUpdater extends ApprovalsDataRetriever{




	private static final String PARMATER_ID = "id";

	private static final String LOG_INFO_TAG = "ApprovalsItemStatusUpdater Info:";

	/**
	 * 
	 * @param item_id
	 * @param action : reject, approve, or set_to_pending
	 * @return
	 */
	public static boolean updateItem(int item_id, String action){

		ArrayList<NameValuePair> nameValuePairs = get_database_settings();
		nameValuePairs.add(new BasicNameValuePair(REQUEST, action));
		nameValuePairs.add(new BasicNameValuePair(PARMATER_ID,  Integer.toString(item_id)));

		//http post
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(PHP_APPROVALS_DATA_FILE_LINK);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			httpclient.execute(httppost);

		}
		catch(Exception e){
			Log.e(LOG_INFO_TAG, "An error occured while trying to " + action + " the item which has the id " + item_id + "\n"
					+ "Error description: " + e.toString());
		}

		return true;
	}
}
