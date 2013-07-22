package com.felight.android.bc;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {
	private static final String tag = "Notification Receiver";
	private int YOUR_PI_REQ_CODE = 123;
	private int YOUR_NOTIF_ID = 1;

	@Override
	public void onReceive(Context context, Intent intent) {

		Log.d(tag, "intent=" + intent);
		String message = intent.getStringExtra("message");
		Log.d(tag, message);
		this.sendNotification(context, message);
	}

	private void sendNotification(Context ctx, String message) {
		// Get a reference to the NotificationManager
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager nm = (NotificationManager) ctx.getSystemService(ns);
		// Create Notification Object
		int icon = R.drawable.robot;
		CharSequence tickerText = "Hello";
		long when = System.currentTimeMillis();

		Intent notificationIntent = new Intent(Intent.ACTION_VIEW);
		notificationIntent.setData(Uri.parse("http://www.google.com"));

		PendingIntent contentIntent = PendingIntent.getActivity(ctx,
				YOUR_PI_REQ_CODE, notificationIntent,
				PendingIntent.FLAG_CANCEL_CURRENT);

		Notification.Builder builder = new Notification.Builder(ctx);

		builder.setContentIntent(contentIntent).setSmallIcon(icon)
				.setTicker(tickerText).setWhen(when).setAutoCancel(true)
				.setContentTitle("title")
				.setContentText("Hello user, don't sleep...!!!");
		Notification n = builder.getNotification();

		nm.notify(YOUR_NOTIF_ID, n);

	}
}
