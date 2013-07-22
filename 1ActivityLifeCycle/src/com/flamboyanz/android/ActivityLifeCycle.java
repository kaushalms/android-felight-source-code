package com.flamboyanz.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ActivityLifeCycle extends Activity {
	private static final String TAG = "ActivityLifeCycle";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		log("onCreate() called");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		log("onSaveInstanceState() called");
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		log("onDestroy() called");
	}

	@Override
	protected void onRestart() {

		super.onRestart();
		log("onRestart() called");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {

		super.onRestoreInstanceState(savedInstanceState);
		log("onRestoreInstanceState() called");
	}

	@Override
	protected void onStop() {

		super.onStop();
		log("onStop() called");
	}

	@Override
	protected void onPause() {

		super.onPause();
		log("onPause() called");
	}

	@Override
	protected void onResume() {

		super.onResume();
		log("onResume() called");
	}

	@Override
	protected void onStart() {

		super.onStart();
		log("onStart() called");
	}

	private void log(String msg) {
		Log.d(TAG, msg);
	}
}