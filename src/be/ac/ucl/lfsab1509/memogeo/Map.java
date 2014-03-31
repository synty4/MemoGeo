package be.ac.ucl.lfsab1509.memogeo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.google.android.maps.MapActivity;

public class Map extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

}
