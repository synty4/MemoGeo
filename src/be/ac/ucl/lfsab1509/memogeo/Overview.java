package be.ac.ucl.lfsab1509.memogeo;

import java.util.ArrayList;

import DataBase.DatabaseHandler;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Overview extends Activity implements View.OnClickListener {

	private GoogleMap mapObject;

	private String selectTitle;
	private Button selectMemo;
	Memo memo;
	DatabaseHandler db = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_overview);

		this.selectMemo = (Button) findViewById(R.id.buttonSelectMemo);
		this.selectMemo.setOnClickListener(this);

		try {
			initializeMap();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addmarkers() {

		ArrayList<Memo> memos = new ArrayList<Memo>(db.getAllMemoInformation());
		// goes trough the database to retrieve the locations
		for (int i = 0; i < memos.size(); i++) {
			Marker marker = mapObject.addMarker(new MarkerOptions()
					.position(
							new LatLng(memos.get(i).getLatitude(), memos.get(i)
									.getLongitude())).draggable(false)
					.title(memos.get(i).getTitle()));
		}

	}

	private void initializeMap() {

		GPSTracker gps = new GPSTracker(this);

		// check if map exists
		if (mapObject == null) {
			mapObject = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.mapOverview)).getMap();

			// camera is centered around the gps location of the device
			mapObject.animateCamera(CameraUpdateFactory.newLatLngZoom(
					new LatLng(gps.getLatitude(), gps.getLongitude()), 8));

			this.addmarkers();

			mapObject.setOnMarkerClickListener(new OnMarkerClickListener() {

				@Override
				public boolean onMarkerClick(Marker markerClick) {

					selectTitle = markerClick.getTitle();

					return false;
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
		case R.id.buttonSelectMemo: {
			memo = db.getMemoInformation(selectTitle);
			Intent memoEdit = new Intent(Overview.this, WriteNewMemo.class);
			memoEdit.putExtra("memo", memo);
			startActivity(memoEdit);

			break;
		}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		// initializeMap();
	}

}
