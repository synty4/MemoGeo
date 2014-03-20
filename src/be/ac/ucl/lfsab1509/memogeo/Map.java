package be.ac.ucl.lfsab1509.memogeo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.google.android.maps.MapActivity;

public class Map extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	  protected boolean isRouteDisplayed() {
	    return false;
	  }

	  @Override
	  protected boolean isLocationDisplayed() {
	    return true;
	  }
	  
}
