package be.ac.ucl.lfsab1509.memogeo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.maps.MapActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity {

	private GoogleMap mapObject;
	private Marker marker;
	private Circle circle;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		try {
			initializeMap();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void initializeMap() {
		if (mapObject == null) {
			mapObject = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			mapObject.setOnMapClickListener(new OnMapClickListener() {

				@Override
				public void onMapClick(LatLng point) {
					if (marker == null) {
						marker = mapObject.addMarker(new MarkerOptions()
								.position(point).draggable(true));
						circle = mapObject.addCircle(new CircleOptions()
						.center(point)
						.radius(10)
						.strokeWidth(5)
						);
					} else {
						marker.setPosition(point);
						circle.setCenter(point);
					}
				}
			});

			// check if map is created successfully or not
			if (mapObject == null) {
				Toast.makeText(getApplicationContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	
	

	@Override
	protected void onResume() {
		super.onResume();
		initializeMap();
	}

}
