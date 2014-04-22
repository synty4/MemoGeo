package be.ac.ucl.lfsab1509.memogeo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OptionsActivity extends Activity implements View.OnClickListener {

	private Button chooseTime;
	private Button chooseDate;
	private Button openMap;
	private Button here;
	private EditText selectTime;
	private EditText selectDate;
	private EditText selectAddress;
	//private TextView test;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		this.chooseTime = (Button) findViewById(R.id.buttonTime);//Bouton et champ de l'heure
		this.chooseTime.setOnClickListener(this);
		
		this.selectTime = (EditText) findViewById(R.id.editTextTime);
		
		this.chooseDate = (Button) findViewById(R.id.buttonDate);//Bouton et champ de la date
		this.chooseDate.setOnClickListener(this);
		
		this.selectDate = (EditText) findViewById(R.id.editTextDate);
		
		
		this.openMap = (Button) findViewById(R.id.buttonMap);//Bouton de la map
		this.openMap.setOnClickListener(this);
		
		this.here = (Button) findViewById(R.id.buttonHere);//Bouton et champ de l'heure
		this.here.setOnClickListener(this);
		
		this.selectAddress = (EditText) findViewById(R.id.editTextAddress);
		
		// Getting the intent.
		Intent intent = getIntent();
		
		// Getting the memo object from the intent.
		Memo memo = (Memo) intent.getParcelableExtra("memo");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

	@Override
	// Handles all the buttons.
	public void onClick(View v) {
		
		switch (v.getId())
		{
			case R.id.buttonTime:
				ChooseTime chooseTime = new ChooseTime();
				chooseTime.putParentActivity(1);
				chooseTime.show(getFragmentManager(), "Time");
				break;
			
			case R.id.buttonDate:
				ChooseDate chooseDate = new ChooseDate();
				chooseDate.putParentActivity(1);
				chooseDate.show(getFragmentManager(), "Date");
				break;
				
			case R.id.buttonMap:
				Intent StartMap = new Intent(OptionsActivity.this, Map.class);
             	startActivity(StartMap);
             	break;
			case R.id.buttonHere : 
				
				GPSTracker gps = new GPSTracker(this);
				
				if(gps.canGetLocation()){
	                
	                editTextAddress(gps.getAddress(gps.getLatitude(), gps.getLongitude()));
	                
	            }else{
	                // can't get location
	                // GPS or Network is not enabled
	                // Ask user to enable GPS/network in settings
	                gps.showSettingsAlert();
	            }
		}
		
	}
	
	// Set the time in the EditText.
	public void editTextTime(String time)
	{
		this.selectTime.setText(time);
	}
	
	//Set the date in the EditText.
	public void editTextDate(String date)
	{
		this.selectDate.setText(date);
	}

	//Set the address in the EditText.
	public void editTextAddress(String address)
	{
		this.selectAddress.setText(address);
	}
	
public void backOptions(View v) {
		
		//if(v.getId() == R.id.buttonSave1){
			
		//}else if(v.getId() == R.id.buttonOption){
			// code pour lancer les options.
			// 1. create intent.
			Intent intent = new Intent(OptionsActivity.this, WriteNewMemo.class);

			// 2. create memo object.
			Memo memo = new Memo();
			//editTextMemo = (EditText) findViewById(R.id.editTextMem);
			//memo.setMemo(editTextMemo.getText().toString());

			// 3. put object in the intent.
			intent.putExtra("memo", memo);

			// 4. Start the next activity.
			startActivity(intent);
			
		
		
		
	}
}
