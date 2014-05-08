package be.ac.ucl.lfsab1509.memogeo;

import DataBase.DatabaseHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.location.GpsStatus;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	private ArrayList<Memo> memos;

	DatabaseHandler db;
	
	AlarmManager manager;
	PendingIntent timePendingIntent, geoPendingIntent;
	Context mContext;
	BroadcastReceiver br;
	
	public static final String EVENT_ID_INTENT_EXTRA = "EventIDIntentExtraKey";
	
	float radius = 15f;

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
		
		setup("intent");
		createMemoList();		
	}

	private void createMemoList() {
		Memo memo1 =new Memo("1", "test", "", 40.0, 80.0,"14:47","08/05/2014");
		Memo memo2 =new Memo("2", "testing", "", 30.0, 80.0,"14:48","08/05/2014");
		memos = new ArrayList<Memo>();
		memos.add(memo1);
		memos.add(memo2);
		geoRegisterIntents(memos);
		timeRegisterIntents(memos);
		
    }
	
	private void geoRegisterIntents(List<Memo> memos) {
    	for(int i = 0; i < memos.size(); i++) {
    		
    		setProximityAlert(memos.get(i).getLatitude(), 
    				memos.get(i).getLongitude(), 
    				i+1, 
    				i, memos);
    	}
    }
	
	private void setProximityAlert(double lat, double lon, final long eventID, int requestCode, List<Memo> memos)
	{	
    	LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	
    	Intent intent = new Intent("be.ac.ucl.lfsab1509.memogeo");
    	intent.putExtra("EventId", memos.get(requestCode).getTitle());
    	intent.putExtra("memo", memos.get(requestCode));
    	PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    	
    	locManager.addProximityAlert(lat, lon, radius, -1, pendingIntent);
    }
	
	private void timeRegisterIntents(ArrayList<Memo> memos) {
    	
		for(int i = 0; i < memos.size(); i++) {
    			
    		int year,
			month,
			day,
			hour,
			minut;// R�cup�re les infos du m�mo pour les passer � la cr�ation de
					// l'alarme.
			String date[] = memos.get(i).getDate().split("/");
			Toast.makeText(getApplicationContext(), "memo values :" + date[0]+ date[1]+ date[2], Toast.LENGTH_SHORT).show();
			String time[] = memos.get(i).getTime().split(":");

			year = Integer.parseInt(date[2]);
			month = Integer.parseInt(date[1]) - 1;// car les mois en android
												 // commence � 0.
			day = Integer.parseInt(date[0]);
			hour = Integer.parseInt(time[0]);
			minut = Integer.parseInt(time[1]);

			Calendar calendar = Calendar.getInstance();
			
			calendar.set(year, month, day, hour, minut, 0); // set(int year, int
															// month, int day,
															// int hourOfDay,
															// int minute, int
															// second
			setTimedAlert(calendar.getTimeInMillis(), i+1, i, memos);
    	}
    }
	
	private void setTimedAlert(long hour ,final long eventID, int requestCode, List<Memo> memos)
	{	    	
    	Intent intent = new Intent("be.ac.ucl.lfsab1509.memogeo");
    	intent.putExtra("EventId", memos.get(requestCode).getTitle());
    	intent.putExtra("memo", memos.get(requestCode));
    	Toast.makeText(getApplicationContext(), "memo values :"+memos.get(requestCode).getTitle()+" "+memos.get(requestCode).getTime(), Toast.LENGTH_SHORT).show();
    	PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

		manager = (AlarmManager) (this
				.getSystemService(Context.ALARM_SERVICE));
		manager.set(AlarmManager.RTC_WAKEUP, hour,
				pendingIntent);
    }
	
	// Prépare the alarm.
	private void setup(String memo) {
		final String title = memo;

		br = new BroadcastReceiver() {
			@Override
			public void onReceive(Context c, Intent i) {
				Toast.makeText(getApplicationContext(), title+ " : " + i.getStringExtra("EventId"), Toast.LENGTH_LONG).show();
				mp.start();
			}
		};
		registerReceiver(br, new IntentFilter("be.ac.ucl.lfsab1509.memogeo"));
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// non pertinent pour le moment 
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
			//timeRegisterIntents();
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
			
			//lm.addProximityAlert(latTestBarbe, longTestBarbe, radius, -1,
					//geoPendingIntent);

			break;
		}
	}

	// Set the time in the EditText.

	public void editTextTime(String time) {
		this.selectTime.setText(time);
	}

	// Set the date in the EditText.
	public void editTextDate(String date) {
		this.selectDate.setText(date);
	}
}
