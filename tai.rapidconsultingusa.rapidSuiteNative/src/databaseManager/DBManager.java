package databaseManager;


import java.util.ArrayList;



import org.apache.http.NameValuePair;

import org.apache.http.message.BasicNameValuePair;


public class DBManager {

	public static ArrayList<NameValuePair> get_database_settings(){

		nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair(DBUSER, database_user));
		nameValuePairs.add(new BasicNameValuePair(DBPASS, database_pass));
		nameValuePairs.add(new BasicNameValuePair(DBURL, database_host));
		nameValuePairs.add(new BasicNameValuePair(DBNAME, database_name));

		return nameValuePairs;
	}


	// These are the initial database values.  These values are updated
	// by user via LoginActivity.class, and Settings_Activity.class 
	private static String database_name = "mobiledb";
	private static String database_user = "mdbuser";
	private static String database_pass = "welcome45";
	private static String database_host = "rapidcon.startlogicmysql.com";

	

	// Strings as parameters to be passed to PHP_USER_AUTHENTICATION_FILE 
	private static final String DBUSER = "dbuser";
	private static final String DBNAME = "dbname";
	private static final String DBURL = "dburl";
	private static final String DBPASS = "dbpass";

	private static ArrayList<NameValuePair> nameValuePairs;
}
