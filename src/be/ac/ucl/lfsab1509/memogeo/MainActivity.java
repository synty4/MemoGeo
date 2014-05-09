package be.ac.ucl.lfsab1509.memogeo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DataBase.DatabaseHandler;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	private Button newMemo;
	private Button map;
	private Button memoList;
	
	private Builder alertDialogBuilder;
	
	private MediaPlayer mp = new MediaPlayer();
	
	AlarmManager manager;
	PendingIntent timePendingIntent, geoPendingIntent;
	Context mContext;
	BroadcastReceiver br;
	
	DatabaseHandler db = new DatabaseHandler(mContext);
	
	public static final String EVENT_ID_INTENT_EXTRA = "EventIDIntentExtraKey";
	
	float radius = 15f;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/* test affichage dans l'onglet log */
		mp = MediaPlayer.create(this, R.raw.gameover);

		this.newMemo = (Button) findViewById(R.id.new_memo);
		this.newMemo.setOnClickListener(this);

		this.map = (Button) findViewById(R.id.open_map);
		this.map.setOnClickListener(this);

		this.memoList = (Button) findViewById(R.id.list_memo);
		this.memoList.setOnClickListener(this);
		
		setup();
		createMemoList();	
	}

	@Override
	public void onClick(View v) {
		Memo memo = new Memo();
		switch (v.getId()) {

		case R.id.new_memo:
			Intent writeNewMemo = new Intent(MainActivity.this,
					WriteNewMemo.class);
			memo.setTitle("");
			memo.setMemo("");
			writeNewMemo.putExtra("memo", memo);
			startActivity(writeNewMemo);
			break;
		case R.id.open_map:
			memo.setTitle("");
			memo.setMemo("");
			Intent map = new Intent(MainActivity.this, Map.class);
			map.putExtra("memo", memo);
			startActivity(map);
			break;
		case R.id.list_memo:
			Intent ListMemo = new Intent(MainActivity.this, ListMemo.class);
			startActivity(ListMemo);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	public void createMemoList() {
		DatabaseHandler db = new DatabaseHandler(this);
		ArrayList<Memo> memos = new ArrayList<Memo>(db.getAllMemoInformation());
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
			setTimedAlert(getHour(memos.get(i)), i+1 , i , memos);
    	}
    }
	
	private long getTime()
	{
		final Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        
        Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hour, minute, 0);
		
		return calendar.getTimeInMillis();
	}
	
	private long getHour(Memo memo)
	{
		int year,
		month,
		day,
		hour,
		minut;// R�cup�re les infos du m�mo pour les passer � la cr�ation de
				// l'alarme.
		String date[] = memo.getDate().split("/");
		String time[] = memo.getTime().split(":");

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
		
		return calendar.getTimeInMillis();
	}
	
	private void setTimedAlert(long hour ,final long eventID, int requestCode, List<Memo> memos)
	{	    	
    	Intent intent = new Intent("be.ac.ucl.lfsab1509.memogeo");
    	intent.putExtra("memo", memos.get(requestCode));
    	PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

		manager = (AlarmManager) (this
				.getSystemService(Context.ALARM_SERVICE));
		manager.set(AlarmManager.RTC_WAKEUP, hour,
				pendingIntent);
    }
	
	// Prépare the alarm.
	public void setup() {

		br = new BroadcastReceiver() {
			@Override
			public void onReceive(Context c, Intent i) {
				
		        Memo memoToShow = new Memo();
				memoToShow = (Memo) i.getSerializableExtra("memo");
				
				if(getHour(memoToShow)!=getTime())
				{
					Toast.makeText(getApplicationContext(), "alarm passed", Toast.LENGTH_SHORT).show();
				}
				else
				{
				Intent editMemo = new Intent(MainActivity.this, WriteNewMemo.class);
				editMemo.putExtra("memo", memoToShow);
				startActivity(editMemo);
				mp.start();
				}
			}
		};
		registerReceiver(br, new IntentFilter("be.ac.ucl.lfsab1509.memogeo"));
	}
}