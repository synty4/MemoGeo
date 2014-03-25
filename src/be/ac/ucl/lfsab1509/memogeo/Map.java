package be.ac.ucl.lfsab1509.memogeo;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.Menu;
import com.google.android.maps.MapActivity;
import com.google.android.maps.Overlay;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class Map extends Activity {

	/**GoogleMap mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	//localisation reaumur hardcode
	private static final LatLng REAUMUR = new LatLng(50.668498, 4.622155);
	float zoom = 15;*/
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);     
        
    }
    
	
	
	/**copié du site android sert à vérifier si on a une map avant de la manipuler
    private void setUpMapIfNeeded() {
        // Vérifier si la map existe
        if (mMap == null) {
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                                .getMap();
            // Vérifier si on a pu récupérer la map
            if (mMap != null) {
            	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(REAUMUR, zoom));
            	mMap.setMyLocationEnabled(true);

            }
        }
    }*/

}
