package com.felight.android.services.httpget;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HttpGetDemoTask extends Activity {
	TextView tv;
	EditText eText;
	String page;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// get the views id's
		findViewsById();

	}

	private void findViewsById() {
		tv = (TextView) findViewById(R.id.pagetext);
		eText = (EditText) findViewById(R.id.address);
	}

	// Button's onClick() event happens here
	public void doClick(View v) throws MalformedURLException {
		URL url = new URL(eText.getText().toString());
		new LongOperation().execute(new String[] { url.toString() });

	}

	// Button's onClick() event happens here
	public void clearText(View v) {
		tv.setText("");
	}

	/**
	 * AsyncTask enables proper and easy use of the UI thread. This class allows
	 * to perform background operations and publish results on the UI thread
	 * without having to manipulate threads and/or handlers.
	 */
	private class LongOperation extends AsyncTask<String, Void, String> {
		/*
		 * This doInBackground() step is used to perform background computation
		 * that can take a long time.
		 */
		@Override
		protected String doInBackground(String... urls) {
			String result = null;
			for (String url : urls) {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(url);
				try {
					HttpResponse execute = client.execute(httpGet);
					InputStream content = execute.getEntity().getContent();

					BufferedReader buffer = new BufferedReader(
							new InputStreamReader(content));
					StringBuffer sb = new StringBuffer("");
					String s = "";
					String NL = System.getProperty("line.separator");
					while ((s = buffer.readLine()) != null) {
						sb.append(s + NL);

					}
					result = sb.toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return result;
		}

		/**
		 * invoked on the UI thread after the background computation finishes.
		 * The result of the background computation is passed to this step as a
		 * parameter.
		 */
		@Override
		protected void onPostExecute(String result) {
			tv.setMovementMethod(ScrollingMovementMethod.getInstance());
			tv.setText(result);
		}

	}

}