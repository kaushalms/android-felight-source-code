package com.felight.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Explore telephony related functionality and APIs in Android.
 * 
 * @author Mahesh Rajput
 * 
 */
public class Telephony1Activity extends Activity {

	// You could change this number to your own number.
	// This number will be used to DIAL in the dial example.

	public static final String NUMBER = "5556";

	private Button dialintent;
	private Button callintent;
	private Button gotoSmsExample;

	@Override
	public void onCreate(final Bundle icicle) {
		super.onCreate(icicle);
		this.setContentView(R.layout.main);

		this.gotoSmsExample = (Button) findViewById(R.id.gotosms_button);
		this.gotoSmsExample.setOnClickListener(new OnClickListener() {

			public void onClick(final View v) {
				Intent intent = new Intent(Telephony1Activity.this,
						SmsExample.class);
				startActivity(intent);
			}
		});

		this.dialintent = (Button) findViewById(R.id.dialintent_button);
		this.dialintent.setOnClickListener(new OnClickListener() {

			public void onClick(final View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
						+ Telephony1Activity.NUMBER));
				startActivity(intent);
			}
		});

		this.callintent = (Button) findViewById(R.id.callintent_button);
		this.callintent.setOnClickListener(new OnClickListener() {

			public void onClick(final View v) {
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ Telephony1Activity.NUMBER));
				startActivity(intent);

			}
		});
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
}
