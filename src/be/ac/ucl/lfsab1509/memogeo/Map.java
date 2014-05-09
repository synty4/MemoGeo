
package be.ac.ucl.lfsab1509.memogeo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends Activity implements View.OnClickListener {

	private GoogleMap mapObject;
	private Marker marker;
	private Circle circle;
	private Button selectPos;

	Memo memo;

	double lat;
	double lng;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		this.selectPos = (Button) findViewById(R.id.buttonSelectPos);
		this.selectPos.setOnClickListener(this);

		Intent memoReceiver = getIntent();
		memo = (Memo) memoReceiver.getSerializableExtra("memo");
		
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

			GPSTracker gps = new GPSTracker(this);

			if (-90.0 <= memo.getLatitude() && memo.getLatitude() <= 90.0//essai pour centrer la map sur une position de memo déjà connue.
					&& -180.0 <= memo.getLongitude()
					&& memo.getLongitude() <= 180.0 && memo.getLatitude() != 0.0 && memo.getLongitude() != 0.0) {
				mapObject
						.animateCamera(CameraUpdateFactory.newLatLngZoom(
								new LatLng(memo.getLatitude(), memo
										.getLongitude()), 20));
				marker = mapObject.addMarker(new MarkerOptions().position(
						new LatLng(memo.getLatitude(), memo.getLongitude()))
						.draggable(true));
				circle = mapObject.addCircle(new CircleOptions()
						.center(new LatLng(memo.getLatitude(), memo
								.getLongitude())).radius(10).strokeWidth(5));
				Toast.makeText(getApplicationContext(),
						"lat : "+memo.getLatitude()+" long : "+memo.getLongitude(), Toast.LENGTH_LONG)
						.show();
			} else {
				mapObject.animateCamera(CameraUpdateFactory.newLatLngZoom(
						new LatLng(gps.getLatitude(), gps.getLongitude()), 12));
			}

			mapObject.setOnMapClickListener(new OnMapClickListener() {

				@Override
				public void onMapClick(LatLng point) {
					if (marker == null) {
						marker = mapObject.addMarker(new MarkerOptions()
								.position(point).draggable(true));
						circle = mapObject.addCircle(new CircleOptions()
								.center(point).radius(10).strokeWidth(5));
						lat = (marker.getPosition().latitude);
						lng = (marker.getPosition().longitude);

					} else {
						marker.setPosition(point);
						circle.setCenter(point);

						lat = (point.latitude);
						lng = (point.longitude);

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

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.buttonSelectPos:

			memo.setLatitude(lat);
			memo.setLongitude(lng);
			
			lat = 0.0;
			lng = 0.0;

			Intent options = new Intent(Map.this, OptionsActivity.class);
			options.putExtra("memo", memo);
			startActivity(options);

			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initializeMap();
	}

}
