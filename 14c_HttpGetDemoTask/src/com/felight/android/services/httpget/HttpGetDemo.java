package com.felight.android.services.httpget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class HttpGetDemo extends Activity {
	TextView tv;
	String page;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewsById();
		getDataFromHTTP();
		setValue();

	}

	private void findViewsById() {
		tv = (TextView) findViewById(R.id.pagetext);
	}

	private void getDataFromHTTP() {
		BufferedReader in = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet("http://code.google.com/android/");
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();

			page = sb.toString();
			System.out.println(page);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void setValue() {

		// Display the loaded values from HTTP
		// This will not work from 3.0 onwards
		// you need to use AsyncTask to do any kind of
		// network, or operations which takes more than 5 seconds
		// other Android Not Responding message will pop up.
		tv.setMovementMethod(ScrollingMovementMethod.getInstance());
		tv.setText(page);
	}
}