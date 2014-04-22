package be.ac.ucl.lfsab1509.memogeo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.widget.Toast;

public class TimersActivity extends Activity implements View.OnClickListener{

	private Button chooseTime;
	private Button chooseDate;
	private Button openMap;
	private Button geoTimer;
	private Button timeTimer;
	private EditText selectTime;
	private EditText selectDate;
	private MediaPlayer mp = new MediaPlayer();

	AlarmManager manager;
	PendingIntent timePendingIntent, geoPendingIntent;
	Context mContext;
	BroadcastReceiver br;
	
	double latTest = 50.768229;// Arbre de la maison
	double longTest = 4.647869;
	double latTestHome = 50.768219;// Ma maison
	double longTestHome = 4.648526;
	double latTestBarbe = 50668491;
	double longTestBarbe = 4.621887;
	float radius = 5;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timers);

		mp = MediaPlayer.create(this, R.raw.gameover);// Crée un MediaPlayer qui
														// lira le fichier audio
														// du dossier raw

		this.chooseTime = (Button) findViewById(R.id.buttonTime);// Bouton et
																	// champ de
																	// l'heure

		this.chooseTime.setOnClickListener(this);

		this.selectTime = (EditText) findViewById(R.id.editTextTime);

		this.chooseDate = (Button) findViewById(R.id.buttonDate);// Bouton et
																	// champ de
																	// la date

		this.chooseDate.setOnClickListener(this);

		this.selectDate = (EditText) findViewById(R.id.editTextDate);

		this.openMap = (Button) findViewById(R.id.buttonMap);// Bouton de la map
		this.openMap.setOnClickListener(this);

		this.timeTimer = (Button) findViewById(R.id.buttonTimeTimer);// Bouton
																		// Time
																		// Timer
		this.timeTimer.setOnClickListener(this);

		this.geoTimer = (Button) findViewById(R.id.buttonGeoTimer);// Bouton Geo
																	// Timer
		this.geoTimer.setOnClickListener(this);

	}

	// Prépare the alarm.
	private void setup(String memo) {
		final String title = memo;

		br = new BroadcastReceiver() {
			@Override
			public void onReceive(Context c, Intent i) {
				Toast.makeText(getApplicationContext(), "" + title,
						Toast.LENGTH_LONG).show();
				mp.start();
				c.unregisterReceiver(this);
			}
		};
		registerReceiver(br, new IntentFilter("be.ac.ucl.lfsab1509.memogeo"));
		// timePendingIntent = PendingIntent.getBroadcast(this, 0, new
		// Intent("be.ac.ucl.lfsab1509.memogeo"), 0);
		// manager = (AlarmManager)
		// (this.getSystemService(Context.ALARM_SERVICE));
	}

	// OnClick listeners
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonTime:
			ChooseTime chooseTime = new ChooseTime();
			chooseTime.putParentActivity(2);
			chooseTime.show(getFragmentManager(), "Time");
			break;
		case R.id.buttonDate:
			ChooseDate chooseDate = new ChooseDate();
			chooseDate.putParentActivity(2);
			chooseDate.show(getFragmentManager(), "Date");
			break;

		case R.id.buttonMap:
			Intent Map = new Intent(TimersActivity.this, Map.class);
			startActivity(Map);
			break;

		case R.id.buttonTimeTimer:

			Toast.makeText(getApplicationContext(), "Launch time timer",
					Toast.LENGTH_LONG).show();

			Memo timedMemo = new Memo();// Cr�� un objet m�mo

			timedMemo.setTime("" + selectTime.getText().toString());// Ajoute le
																	// string de
																	// l'heure
																	// au m�mo
			timedMemo.setDate("" + selectDate.getText().toString());// Ajoute le
																	// string de
																	// la date
																	// au m�mo
			timedMemo.setMemo("Test Timed Memo");// Permet de tester le m�mo

			Calendar calendar = Calendar.getInstance();

			int year,
			month,
			day,
			hour,
			minut;// R�cup�re les infos du m�mo pour les passer � la cr�ation de
					// l'alarme.
			String date[] = timedMemo.getDate().split("/");
			String time[] = timedMemo.getTime().split(":");

			year = Integer.parseInt(date[2]);
			month = Integer.parseInt(date[1]) - 1;// car les mois en android
													// commence � 0.
			day = Integer.parseInt(date[0]);
			hour = Integer.parseInt(time[0]);
			minut = Integer.parseInt(time[1]);

			calendar.set(year, month, day, hour, minut, 0); // set(int year, int
															// month, int day,
															// int hourOfDay,
															// int minute, int
															// second)
			setup(timedMemo.getMemo());
			timePendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(
					"be.ac.ucl.lfsab1509.memogeo"), 0);
			manager = (AlarmManager) (this
					.getSystemService(Context.ALARM_SERVICE));
			manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
					timePendingIntent);

			break;

		case R.id.buttonGeoTimer:

			
			Toast.makeText(getApplicationContext(), "Launch géo timer",
					Toast.LENGTH_LONG).show();

			Memo geoMemo = new Memo();// Cr�� un objet m�mo
			geoMemo.setMemo("Test Geo Memo");// Permet de tester le m�mo

			setup(geoMemo.getMemo());

			geoPendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(
					"be.ac.ucl.lfsab1509.memogeo"), 0);
			// On ajoute une alerte de proximité si on est à 15m du point.
			LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			
			GPSTracker gps = new GPSTracker(this);
			
			lm.addProximityAlert(latTestBarbe, longTestBarbe, radius, -1,
					geoPendingIntent);
					
			
			
			/*
			//Test pour montrer la position actuelle avec un toast
			GPSTracker gps = new GPSTracker(this);
			
			if(gps.canGetLocation()){
                
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                 
                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
            }else{
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }*/

			break;
		}
	}

	// Set the time in the EditText.

	public void EditTextTime(String time) {
		this.selectTime.setText(time);
	}

	// Set the date in the EditText.
	public void EditTextDate(String date) {
		this.selectDate.setText(date);
	}
}
