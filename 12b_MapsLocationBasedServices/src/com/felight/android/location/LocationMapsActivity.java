package com.felight.android.location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class LocationMapsActivity extends MapActivity {

	MapView mapView;
	MapController mc;
	GeoPoint gp;
	MapOverlay mapOverlay;

	private LocationManager lm;
	private LocationListener locationListener;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Get the reference to the view in the layout
		mapView = (MapView) findViewById(R.id.mapView);

		// Enable Zoom controls
		enableZoomControls();

		// Navigate to a specific location
		navigateTo();
	}

	private void navigateTo() {
		mc = mapView.getController();

		String coordinates[] = { "12.967727", "77.536049" };
		double lat = Double.parseDouble(coordinates[0]);
		double lng = Double.parseDouble(coordinates[1]);

		// GeoPoint object is used to represent a geographical location.
		// the latitude and longitude of a location are represented in micro
		// degrees.
		// they are stored in integer values.
		// For a latitude value of 12.453331, for example, you need to multiply
		// it by 1e6 (which is one million) to obtain 12453331.

		gp = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));

		// To navigate the map to a particular location,
		// you use the animateTo() method of the MapController class
		mc.animateTo(gp);

		// setZoom() enables you to specify the zoom level at which the map is
		// displayed
		// The bigger the number, the more details you see on the map.
		mc.setZoom(13);

		// -- Add a location marker --
		mapOverlay = new MapOverlay();
		List<Overlay> listOfOverlays = mapView.getOverlays();
		listOfOverlays.clear();
		// adding the overlay to the list of overlays available to the
		// mapView
		listOfOverlays.add(mapOverlay);

		// Forces the MapView to be redrawn
		mapView.invalidate();

		// -- Use the location manager class to obtain locations data --
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new MyLocationListener();
		// To be notified whenever there is a change in location, you need to
		// register a
		// request for location changes so that your program can be notified
		// periodically
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, /*
																 * use
																 * LocationManager
																 * .
																 * NETWORK_PROVIDER
																 * - If you want
																 * to use
																 * Cell-ID and
																 * Wi-Fi
																 * triangulation
																 */
				0, 0, locationListener);
	}

	private class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {
				String text = "Location changed : Lat : "
						+ location.getLatitude() + " : Lng: "
						+ location.getLongitude();
				toast(text);
			}
			gp = new GeoPoint((int) (location.getLatitude() * 1E6),
					(int) (location.getLongitude() * 1E6));
			mc.animateTo(gp);
			mc.setZoom(18);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		private void toast(String text) {
			Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
		}
	}

	

	private void enableZoomControls() {
		mapView.setBuiltInZoomControls(true);
	}



	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	/**
	 * An overlay represents an individual item that you can draw on the map You
	 * can add as many overlays as you want. In the MapOverlay class, override
	 * the draw() method so that you can draw the pushpin image on the map.
	 * 
	 * @author mahesh
	 * 
	 */
	class MapOverlay extends com.google.android.maps.Overlay {
		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
				long when) {
			super.draw(canvas, mapView, shadow, when);

			// -- translate the GeoPoint to screen pixels --
			Point screenPts = new Point();
			mapView.getProjection().toPixels(gp, screenPts);

			// -- add the marker on the map --
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.pushpin);
			canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 50, null);

			return true;
		}

		@Override
		public boolean onTouchEvent(MotionEvent event, MapView mapView) {

			if (event.getAction() == 1) {

				// getProjection() returns a projection for converting between
				// screen-pixel coordinates
				// and latitude/longitude coordinates
				// fromPixels() method then converts the screen coordinates
				// into a GeoPoint object
				GeoPoint p = mapView.getProjection().fromPixels(
						(int) event.getX(), (int) event.getY());

				String text = "Location: = " + p.getLatitudeE6() / 1E6 + ","
						+ p.getLongitudeE6() / 1E6;
				toast(text);

				/*
				 * // Reverse Geocoding Geocoder geoCoder = new
				 * Geocoder(getBaseContext(), Locale.getDefault());
				 * 
				 * try { List<Address> addresses = geoCoder.getFromLocation(
				 * p.getLatitudeE6() / 1E6, p.getLongitudeE6() / 1E6, 1); String
				 * add = ""; if (addresses.size() > 0) { for (int i = 0; i <
				 * addresses.get(0) .getMaxAddressLineIndex(); i++) add +=
				 * addresses.get(0).getAddressLine(i) + "\n"; } toast(add); }
				 * catch (IOException e) { e.printStackTrace(); }
				 */

				/*
				 * // Geocoding Geocoder geoCoder = new
				 * Geocoder(getBaseContext(), Locale.getDefault()); try {
				 * List<Address> addresses = geoCoder.getFromLocationName(
				 * "Vijaynagar Bangalore", 5); String add = ""; if
				 * (addresses.size() > 0) { p = new GeoPoint( (int)
				 * (addresses.get(0).getLatitude() * 1E6), (int)
				 * (addresses.get(0).getLongitude() * 1E6)); mc.animateTo(p);
				 * mapView.invalidate(); } } catch (IOException e) {
				 * e.printStackTrace(); }
				 */

				return true;
			}
			return false; // super.onTouchEvent(e, mapView);
		}

		private void toast(String text) {
			Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();
		}
	}
}

/*
 * 1. Displaying Maps - done 2. Displaying the Zoom Control ( Using Zoom
 * controls or programmatically ) 3. Changing Views 4. Navigating to a Specific
 * Location 5. Adding Markers 6. Getting the location that was touched 7.
 * Geocoding 8. Getting location data
 */