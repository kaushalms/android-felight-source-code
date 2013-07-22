package com.felight.android.locationmap;

import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class DisplayMapActivity extends MapActivity {

	MapView mapView;
	MapController mc;
	GeoPoint p;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Get the reference to the views
		findViewByIDs();
		// Enable Zoom controls
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true);
		mapView.setTraffic(true);

		navigationToSpecificLocation();

	}

	public void findViewByIDs() {
		mapView = (MapView) findViewById(R.id.mapView);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	private void navigationToSpecificLocation() {
		mc = mapView.getController();
		String coordinates[] = { "12.967727", "77.536049" };
		double lat = Double.parseDouble(coordinates[0]);
		double lng = Double.parseDouble(coordinates[1]);
		p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
		mc.animateTo(p);
		mc.setZoom(15);
		mapView.invalidate();

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		MapController mc = mapView.getController();
		switch (keyCode) {
		case KeyEvent.KEYCODE_1:
			mc.zoomIn();
			break;
		case KeyEvent.KEYCODE_3:
			mc.zoomOut();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}