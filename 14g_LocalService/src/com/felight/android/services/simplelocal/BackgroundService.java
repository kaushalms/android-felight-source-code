/*
 * 
 */
package com.felight.android.services.simplelocal;

// BackgroundService.java
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class BackgroundService.
 */
public class BackgroundService extends Service
{
    
    /** The Constant TAG. */
    private static final String TAG = "BackgroundService";
	
	/** The notification mgr. */
	private NotificationManager notificationMgr;
    
    /** The my threads. */
    private ThreadGroup myThreads = new ThreadGroup("ServiceWorker");
    
    /* (non-Javadoc)
     * @see android.app.Service#onCreate()
     */
    @Override
    public void onCreate() {
        super.onCreate();

        Log.v(TAG, "in onCreate()");
        notificationMgr =(NotificationManager)getSystemService(
               NOTIFICATION_SERVICE);
        Toast.makeText(getBaseContext(), "Email Service started successfully in a new background thread", Toast.LENGTH_SHORT).show();
        displayNotificationMessage("Email Service started successfully");
    }
    
    /* (non-Javadoc)
     * @see android.app.Service#onStartCommand(android.content.Intent, int, int)
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        
        Bundle bundle = intent.getExtras();
        int counter = bundle.getInt("counter");
        
        Log.v(TAG, "in onStartCommand(), counter = " + counter +
        		", startId = " + startId);
        
        ServiceWorker worker = new ServiceWorker(counter);
        Thread thread = new Thread(myThreads, worker, "BackgroundService");
        thread.start();
        
        return START_NOT_STICKY;
    }

    /**
     * The Class ServiceWorker, to create a separate thread as an execution in the Service
     */
    class ServiceWorker implements Runnable
    {
    	
	    /** The counter. */
	    private int counter = -1;
		
		/**
		 * Instantiates a new service worker.
		 *
		 * @param counter the counter
		 */
		public ServiceWorker(int counter) {
			this.counter = counter;
		}

		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
	        final String TAG2 = "ServiceWorker:" + Thread.currentThread().getId();
            // do background processing here...
            try {
				Log.v(TAG2, "sleeping for 10 seconds. counter = " + counter);
				Thread.sleep(10000);
				Log.v(TAG2, "... waking up");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Log.v(TAG2, "... sleep interrupted");
			}
        }
    }

    /* (non-Javadoc)
     * @see android.app.Service#onDestroy()
     */
    @Override
    public void onDestroy()
    {
        Log.v(TAG, "in onDestroy(). Interrupting threads and cancelling notifications");
        String text = "Email Service being stopped and cancelling notifications";
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

        // Interrupts all the threads
        myThreads.interrupt();
        
        // Cancel all previously displayed notifications 
        notificationMgr.cancelAll();
        super.onDestroy();
    }

    /* (non-Javadoc)
     * @see android.app.Service#onBind(android.content.Intent)
     */
    @Override
    public IBinder onBind(Intent intent) {
    	// Since we are not binding to a service
        Log.v(TAG, "in onBind()");
        return null;
    }
    
    /**
     * Display notification message.
     *
     * @param message the message
     */
    private void displayNotificationMessage(String message)
    {
        Notification notification = new Notification(R.drawable.emo_im_winking, 
                message, System.currentTimeMillis());
        
        notification.flags = Notification.FLAG_NO_CLEAR;

        PendingIntent contentIntent = 
                PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        notification.setLatestEventInfo(this, TAG, message, contentIntent);

        notificationMgr.notify(0, notification);
    }
}
