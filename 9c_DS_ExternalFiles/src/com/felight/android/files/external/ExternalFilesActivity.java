package com.felight.android.files.external;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.felight.android.files.external.R;

public class ExternalFilesActivity extends Activity {

	protected static final int READ_BLOCK_SIZE = 100;
	private EditText textBox;
	private Button saveBtn;
	private Button loadBtn;
	private Button clearBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewsById();

		setSaveButtonHandler();

		setLoadButtonHandler();

		setClearButtonHandler();
	}

	private void findViewsById() {
		textBox = (EditText) findViewById(R.id.editText1);
		saveBtn = (Button) findViewById(R.id.save);
		loadBtn = (Button) findViewById(R.id.load);
		clearBtn = (Button) findViewById(R.id.clear);
	}

	private void setSaveButtonHandler() {
		saveBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = textBox.getText().toString();
				writeToFile(text);
			}

			private void writeToFile(String text) {
				try {
					// -- SD Card Storage --
					File sdCard = Environment.getExternalStorageDirectory();
					// Get the file API's
					File directory = new File(sdCard.getAbsolutePath()
							+ "/MyFiles");
					directory.mkdirs();
					File file = new File(directory, "textfile.txt");
					FileOutputStream fOut = new FileOutputStream(file);

					OutputStreamWriter osw = new OutputStreamWriter(fOut);

					// -- write the string to file --
					osw.write(text);
					osw.flush();
					osw.close();

					// -- display the toast message --
					Toast.makeText(getBaseContext(),
							"File saved successfully!", Toast.LENGTH_SHORT)
							.show();

					// -- clean the edit text --
					textBox.setText("");
				} catch (IOException iOE) {
					iOE.printStackTrace();
				}
			}
		});
	}

	private void setLoadButtonHandler() {
		loadBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					// ---SD Storage---
					File sdCard = Environment.getExternalStorageDirectory();
					File directory = new File(sdCard.getAbsolutePath()
							+ "/MyFiles");
					File file = new File(directory, "textfile.txt");
					FileInputStream fIn = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fIn);

					char[] inputBuffer = new char[READ_BLOCK_SIZE];
					String s = "";

					int charRead;
					while ((charRead = isr.read(inputBuffer)) > 0) {
						// -- convert the chars to a String --
						String readString = String.copyValueOf(inputBuffer, 0,
								charRead);
						s += readString;

						inputBuffer = new char[READ_BLOCK_SIZE];
					}

					// -- set the edit text to the text that has been read --
					textBox.setText(s);

					Toast.makeText(getBaseContext(),
							"File loaded successfully!", Toast.LENGTH_SHORT)
							.show();

				} catch (IOException iOE) {
					iOE.printStackTrace();
				}
			}
		});
	}

	private void setClearButtonHandler() {
		clearBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				textBox.setText("");
			}
		});
	}
}
