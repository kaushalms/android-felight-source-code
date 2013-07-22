package com.felight.android.intent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends Activity {
	String tag = "Events";
	int request_Code = 1;
	TextView tv;
    Button btn;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(tag, "In the onCreate() event");
		// ---hides the title bar---
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		tv = (TextView) findViewById(R.id.txt);
		btn=(Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// startActivity(new Intent(this, Activity2.class));
				startActivityForResult(
						new Intent(getBaseContext(), Activity2.class), request_Code);
			}
		});
		
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == request_Code) {
			if (resultCode == RESULT_OK) {
				/*
				 * Toast.makeText(this, data.getData().toString(),
				 * Toast.LENGTH_SHORT).show();
				 */
				tv.setText(data.getData().toString());

			}
		}
	}

}