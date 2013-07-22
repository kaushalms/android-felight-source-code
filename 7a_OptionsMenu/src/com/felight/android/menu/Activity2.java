package com.felight.android.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Activity2 extends Activity {

	/** Called when the activity is first created. */
	private final int about = R.id.about;
	private final int Options = R.id.Options;
	private final int themes = R.id.themes;
	private final int settings =R.id.settings;
	private final int exit = R.id.exit;
	private final int restart = R.id.restart;
	Toast msg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// creates a menu inflater
		MenuInflater inflater = getMenuInflater();
		// generates a Menu from a menu resource file
		// R.menu.main_menu represents the ID of the XML resource file
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// check selected menu item
		// R.id.exit is @+id/exit
		switch (item.getItemId()) {
		case about : {
			msg = Toast.makeText(getBaseContext(), "you clicked on About Item",
					3000);
			msg.show();
			break;
		}

		case Options: {
			msg = Toast.makeText(getBaseContext(), "you clicked on Options Item",
					3000);
			msg.show();
			break;
		}
		case themes: {
			msg = Toast.makeText(getBaseContext(), "you clicked on themes Item",
					3000);
			msg.show();
			break;
			
		}
		case settings: {
			msg = Toast.makeText(getBaseContext(), "you clicked on settings Item",
					3000);
			msg.show();
			break;
		}
		case exit: {
			msg = Toast.makeText(getBaseContext(), "you clicked on exit Item",
					3000);
			msg.show();
			this.finish();
			break;
		}
		case restart: {
			msg = Toast.makeText(getBaseContext(), "you clicked on restart Item",
					3000);
			msg.show();
			break;
		}

		}
		return true;
	}
}
