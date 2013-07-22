package com.felight.android.contextmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int MENU_ITEM_TOAST = 1;
	private int MENU_ITEM_SEND = 2;
	private int MENU_ITEM_EDIT = 3;
	private int MENU_ITEM_REMOVE = 4;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView tv = (TextView) findViewById(R.id.txt1);
		registerForContextMenu(tv);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		menu.setHeaderTitle("Sample Context Menu");
		menu.add(Menu.NONE, MENU_ITEM_TOAST, 1, R.string.toast_msg);
		menu.add(Menu.NONE, MENU_ITEM_SEND, 2, "send");
		menu.add(Menu.NONE, MENU_ITEM_EDIT, 3, "edit");
		menu.add(Menu.NONE, MENU_ITEM_REMOVE, 4, "remove");

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == MENU_ITEM_TOAST) {
			String text = "ContextMenu MENU_ITEM_TOAST selected";
			Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG).show();
			return true;
		}
		return false;
	}
}