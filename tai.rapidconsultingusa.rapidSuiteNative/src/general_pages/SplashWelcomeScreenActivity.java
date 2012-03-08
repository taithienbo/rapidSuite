package general_pages;


import tai.rapidconsultingusa.rapidSuiteNative.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashWelcomeScreenActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen_layout);

		start_login_activity();
	



	}
	
	



	private void start_login_activity() {
		// TODO Auto-generated method stub
		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
			public void run() { 
				Intent intent = new Intent(SplashWelcomeScreenActivity.this, LoginActivity.class);
				startActivity(intent);
			} 
		}, 2000); 
	}


	@Override
	public void onRestart(){
		super.onRestart();
	}

	@Override 
	public void onStart(){
		super.onStart();
	}


	@Override
	public void onResume(){
		super.onResume();


	}


	@Override
	public void onPause(){
		super.onPause();
	}


	@Override
	public void onStop(){
		super.onStop();
	}


	@Override
	public void onDestroy(){
		super.onDestroy();
	}


	@Override
	public void onWindowFocusChanged(boolean hasFocus){
		if(hasFocus){
			start_login_activity();
		}
	}

}