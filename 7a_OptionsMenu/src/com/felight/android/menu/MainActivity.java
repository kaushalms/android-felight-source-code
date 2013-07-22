package com.felight.android.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	// ID for the menu exit option
	private final int ID_MENU_EXIT = 1;
	Toast msg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// the menu option text is defined in resources
		menu.add(R.string.aboutOption);
		// get a SubMenu reference
		SubMenu sm = menu.addSubMenu("Options...");
		// add menu items to the submenu
		sm.add("Theme");
		sm.add("Settings");
		// it is better to use final variables for IDs than constant values
		// menu.add(Menu.NONE,1,Menu.NONE,"Exit");

		// get the MenuItem reference
		MenuItem item = menu.add(Menu.NONE, ID_MENU_EXIT, Menu.NONE,
				R.string.exitOption);
		
		// set the shortcut
		item.setShortcut('5', 'x');

		// the menu option text is defined as constant String
		menu.add("Restart");

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// check selected menu item
		if (item.getItemId() == ID_MENU_EXIT) {

			// close the Activity
			this.finish();
			return true;
		}
		return false;
	}
}