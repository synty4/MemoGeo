package be.ac.ucl.lfsab1509.memogeo;

import java.util.Calendar;

import com.google.android.gms.common.GooglePlayServicesUtil;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TimersActivity extends Activity implements View.OnClickListener {
	private Button chooseTime;
	private Button chooseDate;
	private Button openMap;
	private Button geoTimer;
	private Button timeTimer;
	private EditText selectTime;
	private EditText selectDate;
	private MediaPlayer mp = new MediaPlayer();

	AlarmManager manager;
	PendingIntent pendingIntent;
	Context mContext;
	BroadcastReceiver br;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timers);
		
		mp = MediaPlayer.create(this, R.raw.gameover);

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

	private void setup(String memo) {
		final String title = memo;

		br = new BroadcastReceiver() {
			@Override
			public void onReceive(Context c, Intent i) {
				Toast.makeText(getApplicationContext(), "" + title,
						Toast.LENGTH_LONG).show();
				//alarmSound();
				mp.start();
				c.unregisterReceiver(this);
			}
		};
		registerReceiver(br, new IntentFilter("be.ac.ucl.lfsab1509.memogeo"));
		pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(
				"be.ac.ucl.lfsab1509.memogeo"), 0);
		manager = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));
	}

	@Override
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

			Toast.makeText(getApplicationContext(), "Launch timer",
					Toast.LENGTH_LONG).show();
			Memo timedMemo = new Memo();// Créé un objet mémo

			timedMemo.setTime("" + selectTime.getText().toString());// Ajoute le
																	// string de
																	// l'heure
																	// au mémo
			timedMemo.setDate("" + selectDate.getText().toString());// Ajoute le
																	// string de
																	// la date
																	// au mémo
			timedMemo.setMemo("Test Timed Memo");// Permet de tester le mémo

			Calendar calendar = Calendar.getInstance();

			int year,
			month,
			day,
			hour,
			minut;// Récupère les infos du mémo pour les passer à la création de
					// l'alarme.
			String date[] = timedMemo.getDate().split("/");
			String time[] = timedMemo.getTime().split(":");

			year = Integer.parseInt(date[2]);
			month = Integer.parseInt(date[1]) - 1;// car les mois en android
													// commence à 0.
			day = Integer.parseInt(date[0]);
			hour = Integer.parseInt(time[0]);
			minut = Integer.parseInt(time[1]);

			calendar.set(year, month, day, hour, minut, 0); // set(int year, int
															// month, int day,
															// int hourOfDay,
															// int minute, int
															// second)

			setup(timedMemo.getMemo());

			manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
					pendingIntent);

			break;

		case R.id.buttonGeoTimer:
			// TODO
			GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext);
			
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
