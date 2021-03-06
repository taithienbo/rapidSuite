package general_pages;





import controller.MainActivity;
import controller.ModuleFragment;
import databaseManager.Authentication;
import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Button loginButton =  (Button) findViewById(R.id.loginPage_button_login);
		loginButton.setOnClickListener(submitButtonClistener);
	}
	
	/**
	@Override
	public void onPause(){
		super.onPause();
		this.finish();
		
	}
**/
	
	private OnClickListener submitButtonClistener = new OnClickListener(){
		public void onClick(View v){

			String user_name = getUserName();
			String user_password = getUserPassWord();
			
			if(Authentication.passAuthentication(user_name, user_password)){
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
			}
			else{
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
			}
		}

	};

	
	public String getUserPassWord(){
		EditText userPassword = (EditText) findViewById(R.id.loginPage_exitText_user_pass);
		return userPassword == null ? null : userPassword.getText().toString().trim();
	}


	public String getUserName(){
		EditText userName = (EditText) findViewById(R.id.loginPage_exitText_user_name);
		return userName == null ? null : userName.getText().toString().trim();
	}
}


