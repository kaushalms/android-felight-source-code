package com.felight.android.contentprovider;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContentUserDemo extends Activity {
	private static final String TAG = "ContentUserDemo";
	private ArrayList<String> list;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);

		Uri uri = Uri
				.parse("content://com.androidbook.provider.BookProvider/books");

		// getting the instance of the ContentResolver
		ContentResolver cr = getContentResolver();
		// getting the instance of the Cursor
		Cursor managedCursor = cr.query(uri, null, null, null,"name asc");

		// accessing data by using Cursor object
		managedCursor.moveToFirst();
		list = new ArrayList<String>();
		do {
			int booknameFieldColumnIndex = managedCursor
					.getColumnIndexOrThrow("name");
			String bookname = managedCursor.getString(booknameFieldColumnIndex);

			int authorFieldColumnIndex = managedCursor
					.getColumnIndexOrThrow("author");
			String author = managedCursor.getString(authorFieldColumnIndex);
			list.add(bookname + ": " + author);
		} while (managedCursor.moveToNext());

		Log.d(TAG, "cursor.getCount()=" + managedCursor.getCount());

		// Get the list view
		ListView listView = (ListView) findViewById(R.id.listView);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		listView.setAdapter(aa);
	}
}
