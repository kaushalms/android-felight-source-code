package com.felight.android.sharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferencesActivity extends Activity {

	private SharedPreferences prefs;
	private String prefName = "MyPref1";
	private TextView tv;
	private EditText editText;
	private SeekBar seekBar;
	private Button saveBtn;

	private static final String FONT_SIZE_KEY = "fontsize";
	private static final String TEXT_VALUE_KEY = "textvalue";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// get reference to all the views in the layout
		findViewsById();

		// handle the button click
		buttonClickHandler();

		work();
	}

	private void findViewsById() {
		editText = (EditText) findViewById(R.id.et01);
		seekBar = (SeekBar) findViewById(R.id.seekBar01);
		saveBtn = (Button) findViewById(R.id.btn01);
		tv = (TextView) findViewById(R.id.tv01);
	}

	private void buttonClickHandler() {
		saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// -- get the shared preferences object --
				prefs = getSharedPreferences(prefName, MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();

				// -- save the values in the EditText view to preferences --
				float size = editText.getTextSize();
				editor.putFloat(FONT_SIZE_KEY, size);
				editor.putString(TEXT_VALUE_KEY, editText.getText().toString());

				// -- saves the value in preferences --
				editor.commit();

				// -- display file saved message --
				toast("Font size saved successfully.");
			}

			public void toast(String text) {
				Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	private void work() {
		// -- load shared preferences object --
		SharedPreferences prefs = getSharedPreferences(prefName, MODE_PRIVATE);

		// ---set the TextView font size to the previously saved values---
		float fontSize = prefs.getFloat(FONT_SIZE_KEY, 20);

		// ---init the SeekBar and EditText---
		seekBar.setProgress((int) fontSize);
		editText.setText(prefs.getString(TEXT_VALUE_KEY, ""));
		editText.setTextSize(seekBar.getProgress());
		tv.setText("The value of the font size is " + fontSize);

		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// -- change the font size of the edit text --
				editText.setTextSize(progress);
				tv.setText("The value of the font size is " + progress);
			}
		});
	}

	public void toast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
}