package com.felight.android.intent;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.content.Intent;

public class Activity2 extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.active2);

		// ---get the OK button---
		Button btn = (Button) findViewById(R.id.btn_OK);

		// ---event handler for the OK button---
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent data = new Intent();
				// ---get the EditText view---
				EditText txt_username = (EditText) findViewById(R.id.txt_username);
				// ---set the data to pass back---
				data.setData(Uri.parse(txt_username.getText().toString()));
				setResult(RESULT_OK, data);
				// ---closes the activity---
				finish();
			}
		});
	}
}
