package com.android.mahiraj.fundooapp;

/**
 * @author Mahesh Rajput
 *
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.SessionStore;

public class ItemDetailsActivity extends Activity {
	TextView textView;
	Button btn_fb;
	ImageView share;
	String text;
	private static final String APP_ID = "440372259361917";
	private static final String[] PERMISSIONS = new String[] {
			"publish_stream", "read_stream", "offline_access" };
	Facebook mFacebook;
	ProgressDialog mProgress;
	Handler runonUI;
	/** The con mgr. */
	ConnectivityManager conMgr;

	/** The active network. */
	NetworkInfo activeNetwork;

	/** Called when the activity is first created. */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.item_detail_layout);
		getResourceIDs();

		conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		activeNetwork = conMgr.getActiveNetworkInfo();

		mFacebook = new Facebook(APP_ID);
		mProgress = new ProgressDialog(this);
		runonUI = new Handler();

		Intent intent = getIntent();
		text = (String) intent.getCharSequenceExtra("text");
		textView.setText(text);

		// Button onClick event handling....
		doClickEventHandling();

	}

	private void getResourceIDs() {
		textView = (TextView) findViewById(R.id.item_detail);
		btn_fb = (Button) findViewById(R.id.fb);
		share = (ImageView) findViewById(R.id.share);
	}

	private void doClickEventHandling() {
		btn_fb.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				if (mFacebook.isSessionValid()) {
					SessionStore.restore(mFacebook, getApplicationContext());
					postTofacebook(text);
					Log.d("SESSION", "VALID");
				} else {
					
					if (activeNetwork != null && activeNetwork.isConnected()) {
						toast("Please LogIn to Facebook...");
						shareClick();
						SessionStore.save(mFacebook, getApplicationContext());
					} else {
						Intent i = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
						startActivity(i);
						Toast.makeText(
								ItemDetailsActivity.this,
								"User you are not online. Please enable data acess over mobile network... ",
								Toast.LENGTH_LONG).show();
					}

				}
			}
		});

		share.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setType("vnd.android-dir/mms-sms");
				intent.putExtra("sms_body", text);
				startActivity(intent);

			}
		});

	}

	@SuppressWarnings("deprecation")
	private void postTofacebook(String review) {
		mProgress.setMessage("Posting ...");
		mProgress.show();
		@SuppressWarnings("deprecation")
		AsyncFacebookRunner asycnkRunner = new AsyncFacebookRunner(mFacebook);
		Bundle params = new Bundle();

		params.putString("message", review);

		asycnkRunner.request("me/feed", params, "POST", new RequestListener() {

			@Override
			public void onComplete(final String response, Object state) {
				runonUI.post(new Runnable() {

					@Override
					public void run() {
						mProgress.cancel();

						toast("Posted to Facebook Successfully..!");
						Log.i("RESPONSE", response);
					}
				});

			}

			@Override
			public void onIOException(IOException e, Object state) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
				// TODO Auto-generated method stub

			}
		}, null);

	}

	@SuppressWarnings("deprecation")
	private void shareClick() {

		mFacebook.authorize(ItemDetailsActivity.this, PERMISSIONS, -1,
				new DialogListener() {

					@Override
					public void onFacebookError(FacebookError e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onError(DialogError e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onComplete(Bundle values) {

						toast("Logged in Successully...!");
						Log.d("SESSION", "AUTORIZING-LoggingIn");
					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub

					}
				});

	}

	public void toast(String msg) {
		Toast.makeText(ItemDetailsActivity.this, msg, Toast.LENGTH_SHORT)
				.show();
	}
}
