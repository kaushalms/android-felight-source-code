
package com.android.mahiraj.fundooapp;
/**
 * @author Mahesh Rajput
 *
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mahiraj.metadata.CategoryContents;

/**
 * @author mahesh rajput
 */
// TODO: Auto-generated Javadoc
/**
 * The Class CategoryListActivity.
 */
public class CategoryListActivity extends ListActivity {
	
	/** The list view. */
	ListView listView;
	
	/** The metrics. */
	private DisplayMetrics metrics;
	
	/** The progress dialog. */
	private ProgressDialog progressDialog;
	
	/** The json. */
	JSONObject json;
	
	/** The app. */
	FundOOApplication app;
	
	/** The category contents. */
	private List<CategoryContents> categoryContents;
	
	/** The cache map_category. */
	private Map<String, List<CategoryContents>> cacheMap_category;
	
	/** The puzzle ans. */
	private List<String> puzzleAns;
	// contacts JSONArray
	/** The json array obj. */
	JSONArray jsonArrayObj = null;
	
	/** The view id. */
	int viewID;
	
	/** The intent. */
	Intent intent;
	
	/** The con mgr. */
	ConnectivityManager conMgr;
	
	/** The active network. */
	NetworkInfo activeNetwork;

	/**
	 * Called when the activity is first created.
	 *
	 * @param savedInstanceState the saved instance state
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// for animation...
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		intent = getIntent();
		viewID = intent.getIntExtra("ImageView", 0);

		categoryContents = new ArrayList<CategoryContents>();
		cacheMap_category = new HashMap<String, List<CategoryContents>>();
		puzzleAns = new ArrayList<String>();
		app = (FundOOApplication) this.getApplication();
		progressDialog = new ProgressDialog(CategoryListActivity.this);
		progressDialog.setCancelable(false);
		progressDialog
				.setMessage("Getting data from the server, please wait...");

		conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		activeNetwork = conMgr.getActiveNetworkInfo();

		if (app.getCacheMap().size() == 0) {

			chechNetworkConnection();

		} else {

			displayCachedData();
		}

	}

	/**
	 * Chech network connection.
	 */
	private void chechNetworkConnection() {

		if (activeNetwork != null && activeNetwork.isConnected()) {
			// notify user you are online
			new CategoryContentsTask().execute();
		} else {
			// notify user you are not online
			callMobileNetworkSettings();
		}
	}

	/**
	 * Call mobile network settings.
	 */
	private void callMobileNetworkSettings() {

		Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
		startActivity(i);
		Toast.makeText(
				CategoryListActivity.this,
				"User you are not online. Please enable data acess over mobile network... ",
				Toast.LENGTH_LONG).show();

	}

	/**
	 * Display cached data.
	 */
	private void displayCachedData() {

		if (getMapKeyContents() == null) {
			chechNetworkConnection();
		} else {
			resetContents(getMapKeyContents());
		}

	}

	/**
	 * Gets the map key contents.
	 *
	 * @return the map key contents
	 */
	private List<CategoryContents> getMapKeyContents() {

		List<CategoryContents> categoryContent = null;
		switch (viewID) {
		case R.id.love_image:
			cacheMap_category = app.getCacheMap();
			categoryContent = cacheMap_category.get("LOVE");
			break;

		case R.id.frenz_image:
			cacheMap_category = app.getCacheMap();
			categoryContent = cacheMap_category.get("FRENZ");
			break;

		case R.id.greetings_image:
			cacheMap_category = app.getCacheMap();
			categoryContent = cacheMap_category.get("GREETINGS");
			break;

		case R.id.puzzle_image:
			cacheMap_category = app.getCacheMap();
			categoryContent = cacheMap_category.get("PUZZLE");
			break;

		case R.id.quotes_image:
			cacheMap_category = app.getCacheMap();
			categoryContent = cacheMap_category.get("QUOTES");
			break;

		case R.id.jokes_image:
			cacheMap_category = app.getCacheMap();
			categoryContent = cacheMap_category.get("JOKES");
			break;

		default:
			Toast.makeText(getBaseContext(),
					"ImageView reference incorrect..!!!", Toast.LENGTH_SHORT)
					.show();
			break;
		}
		return categoryContent;

	}

	/**
	 * Reset contents.
	 *
	 * @param categoryContents the category contents
	 */
	private void resetContents(List<CategoryContents> categoryContents) {

		setListAdapter(new CustomListAdapter(CategoryListActivity.this,
				metrics, categoryContents));
		listView = getListView();
		listView.setTextFilterEnabled(true);
		listView.setBackgroundResource(R.drawable.gal);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				TextView tv = (TextView) view.findViewById(R.id.listItemText);
				if (viewID == R.id.puzzle_image) {
					Intent i = new Intent(CategoryListActivity.this,
							PuzzleActivity.class);
					i.putExtra("position", position);
					i.putExtra("puzzle", tv.getText());
					startActivity(i);
				} else {
					Intent i = new Intent(CategoryListActivity.this,
							ItemDetailsActivity.class);
					i.putExtra("text", tv.getText());
					startActivity(i);
				}
			}
		});
	}

	/**
	 * The Class CategoryContentsTask.
	 */
	@SuppressLint("UseValueOf")
	private class CategoryContentsTask extends
			AsyncTask<String, Void, List<CategoryContents>> {

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			progressDialog.show();

		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected List<CategoryContents> doInBackground(String... params) {
			// Creating JSON Parser instance
			JSONParser jParser = new JSONParser();

			switch (viewID) {

			case R.id.love_image:
				// url to make request
				String love = "http://192.168.1.11/love.json";
				// getting JSON string from URL
				json = jParser.getJSONFromUrl(love);

				// Getting Array of Contents
				try {
					jsonArrayObj = json.getJSONArray("love");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				break;

			case R.id.frenz_image:
				String frenz = "http://192.168.1.11/frenz.json";
				json = jParser.getJSONFromUrl(frenz);

				try {
					jsonArrayObj = json.getJSONArray("frenz");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				break;

			case R.id.greetings_image:
				String greetings = "http://192.168.1.11/greetings.json";
				json = jParser.getJSONFromUrl(greetings);

				try {
					jsonArrayObj = json.getJSONArray("greets");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				break;

			case R.id.puzzle_image:
				String puzzle = "http://192.168.1.11/puzzle.json";
				json = jParser.getJSONFromUrl(puzzle);

				try {
					jsonArrayObj = json.getJSONArray("puzzle");
					// looping through All Contents
					for (int i = 0; i < jsonArrayObj.length(); i++) {

						JSONArray jArray = jsonArrayObj.getJSONArray(i);
						CategoryContents cc = new CategoryContents();
						cc.setmCategoryContents(jArray.getJSONObject(0)
								.getString("ques"));
						categoryContents.add(cc);
						puzzleAns.add(jArray.getJSONObject(1).getString("ans"));
						app.setPuzzleAns(puzzleAns);

					}
					return categoryContents;

				} catch (JSONException e1) {

					e1.printStackTrace();
				}
				break;

			case R.id.quotes_image:
				String quotes = "http://192.168.1.11/quotes.json";
				json = jParser.getJSONFromUrl(quotes);

				try {
					jsonArrayObj = json.getJSONArray("quotes");
				} catch (JSONException e1) {

					e1.printStackTrace();
				}
				break;

			case R.id.jokes_image:
				String jokes = "http://192.168.1.11/jokes.json";
				json = jParser.getJSONFromUrl(jokes);

				try {
					jsonArrayObj = json.getJSONArray("jokes");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				break;

			default:
				Toast.makeText(getBaseContext(), "Clik on the image..!!!",
						Toast.LENGTH_SHORT).show();
				break;
			}

			try {

				// looping through All Contents
				for (int i = 0; i < jsonArrayObj.length(); i++) {

					JSONObject jObj = jsonArrayObj.getJSONObject(i);
					CategoryContents cc = new CategoryContents();
					cc.setmCategoryContents(jObj.getString("content"));
					categoryContents.add(cc);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return categoryContents;
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(List<CategoryContents> categoryContents) {
			if (progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
			if (!categoryContents.isEmpty()) {
				app.setCategoryContentsList(categoryContents);
				switch (viewID) {
				case R.id.love_image:
					cacheMap_category.put("LOVE", categoryContents);
					app.setCacheMap(cacheMap_category);
					break;
				case R.id.frenz_image:
					cacheMap_category.put("FRENZ", categoryContents);
					app.setCacheMap(cacheMap_category);
					break;
				case R.id.greetings_image:
					cacheMap_category.put("GREETINGS", categoryContents);
					app.setCacheMap(cacheMap_category);
					break;
				case R.id.puzzle_image:
					cacheMap_category.put("PUZZLE", categoryContents);
					app.setCacheMap(cacheMap_category);
					break;
				case R.id.quotes_image:
					cacheMap_category.put("QUOTES", categoryContents);
					app.setCacheMap(cacheMap_category);
					break;
				case R.id.jokes_image:
					cacheMap_category.put("JOKES", categoryContents);
					app.setCacheMap(cacheMap_category);
					break;
				default:
					Toast.makeText(getBaseContext(),
							"Image reference invalid..!!!", Toast.LENGTH_SHORT)
							.show();
					break;
				}
				resetContents(categoryContents);
			}
		}
	}
}
