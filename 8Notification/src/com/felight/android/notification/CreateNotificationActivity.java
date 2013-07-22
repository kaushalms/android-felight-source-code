package com.felight.android.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class CreateNotificationActivity extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void createNotification(View v) {
		// Get a reference to the NotificationManager
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager nm = (NotificationManager) this
				.getSystemService(ns);
		// Create Notification Object
		int icon = R.drawable.robot;
		CharSequence tickerText = "Hello";
		long when = System.currentTimeMillis();

		/*
		 * Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
		 * notificationIntent.setData(Uri.parse("http://www.google.com"));
		 */

		Intent notificationIntent = new Intent(this,
				NotificationReceiverActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

		Notification.Builder builder = new Notification.Builder(this);

		builder.setContentIntent(contentIntent).setSmallIcon(icon)
				.setTicker(tickerText).setWhen(when).setAutoCancel(true)
				.setContentTitle("Robot")
				.setContentText("Hello user,select it to go to second activity!");
		Notification n = builder.getNotification();

		nm.notify(0, n);
	}
}