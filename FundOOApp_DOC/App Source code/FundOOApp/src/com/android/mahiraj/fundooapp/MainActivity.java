package com.android.mahiraj.fundooapp;
/**
 * @author Mahesh Rajput
 *
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	ImageView love;
	ImageView frenz;
	ImageView puzzle;
	ImageView jokes;
	ImageView greetings;
	ImageView quotes;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		getResourceIDs();

		// Setting onClick event handling Listener to the images...
		setOnClickListener();

		log("onCreate()");
	}

	private void getResourceIDs() {

		love = (ImageView) findViewById(R.id.love_image);
		frenz = (ImageView) findViewById(R.id.frenz_image);
		puzzle = (ImageView) findViewById(R.id.puzzle_image);
		jokes = (ImageView) findViewById(R.id.jokes_image);
		greetings = (ImageView) findViewById(R.id.greetings_image);
		quotes = (ImageView) findViewById(R.id.quotes_image);

		log("getResourceIDs()");
	}

	private void setOnClickListener() {

		log("setOnClickListener()");

		love.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,
						CategoryListActivity.class);
				i.putExtra("ImageView", v.getId());
				startActivity(i);
			}
		});
		frenz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,
						CategoryListActivity.class);
				i.putExtra("ImageView", v.getId());
				startActivity(i);
			}
		});

		puzzle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,
						CategoryListActivity.class);
				i.putExtra("ImageView", v.getId());
				startActivity(i);
			}
		});

		jokes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,
						CategoryListActivity.class);
				i.putExtra("ImageView", v.getId());
				startActivity(i);
			}
		});

		greetings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,
						CategoryListActivity.class);
				i.putExtra("ImageView", v.getId());
				startActivity(i);
			}
		});

		quotes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,
						CategoryListActivity.class);
				i.putExtra("ImageView", v.getId());
				startActivity(i);
			}
		});

	}

	public void log(String msg) {
		Log.d(TAG, msg);
	}

}
