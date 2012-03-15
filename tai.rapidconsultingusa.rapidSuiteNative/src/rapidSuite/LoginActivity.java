package rapidSuite;





import controller.MainActivity;

import databaseManager.Authentication;
import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;



public class LoginActivity extends Activity
{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);


		Button loginButton =  (Button) findViewById(R.id.loginPage_button_login);
		loginButton.setOnClickListener(submitButtonClistener);
	}
	

	
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

	
	public String getUserPassWord()
	{
		EditText userPassword = (EditText) findViewById(R.id.loginPage_exitText_user_pass);
		return userPassword == null ? null : userPassword.getText().toString().trim();
	}


	public String getUserName()
	{
		EditText userName = (EditText) findViewById(R.id.loginPage_exitText_user_name);
		return userName == null ? null : userName.getText().toString().trim();
	}
}


