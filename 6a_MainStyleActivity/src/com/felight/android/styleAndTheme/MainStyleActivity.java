package com.felight.android.styleAndTheme;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainStyleActivity extends Activity {
	/** Called when the activity is first created. */
	Button b;
	EditText edt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViewsById();
	}

	private void findViewsById() {
		b = (Button) findViewById(R.id.Button_Show);
		b = (Button) findViewById(R.id.Button_Change);
		b = (Button) findViewById(R.id.Button_click);
		edt = (EditText) findViewById(R.id.edt_username);
	}

	public void btnHandler(View view) {
		String text = "Hello Android...";
		Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
		startActivity(new Intent(this,Activity2.class));
	}
}