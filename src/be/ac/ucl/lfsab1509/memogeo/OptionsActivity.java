package be.ac.ucl.lfsab1509.memogeo;

import DataBase.DatabaseHandler;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OptionsActivity extends Activity implements View.OnClickListener {

	private Button chooseTime;
	private Button chooseDate;
	private Button openMap;
	private Button here;
	private Button save;
	private Button back;
	private EditText selectTime;
	private EditText selectDate;
	private EditText selectAddress;
	Memo memo;
	//private TextView test;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		this.back = (Button) findViewById(R.id.back2);
		this.back.setOnClickListener(this);
		
		this.chooseTime = (Button) findViewById(R.id.buttonTime);
		this.chooseTime.setOnClickListener(this);
		
		this.selectTime = (EditText) findViewById(R.id.editTextTime);
		
		this.chooseDate = (Button) findViewById(R.id.buttonDate);
		this.chooseDate.setOnClickListener(this);
		
		this.selectDate = (EditText) findViewById(R.id.editTextDate);
		
		
		this.openMap = (Button) findViewById(R.id.buttonMap);
		this.openMap.setOnClickListener(this);
		
		this.here = (Button) findViewById(R.id.buttonHere);
		this.here.setOnClickListener(this);
		
		this.selectAddress = (EditText) findViewById(R.id.editTextAddress);
		
		this.save = (Button) findViewById(R.id.buttonSave);
		this.save.setOnClickListener(this);
		
		Intent memoReceiver = getIntent();
 		memo = (Memo) memoReceiver.getSerializableExtra("memo");
 		
 		if(memo.getLatitude() !=0.0 && memo.getLongitude() !=0.0)
 		{	
 			GPSTracker gps = new GPSTracker(this);
 			editTextAddress(gps.getAddress(memo.getLatitude(), memo.getLongitude()));
 		}
 		else editTextAddress(memo.getAddress());
 		    
 		editTextTime(memo.getTime());
 		editTextDate(memo.getDate());
 		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

	@Override
	// Handles all the buttons.
	public void onClick(View v) throws NullPointerException{
		
		switch (v.getId())
		{
			case R.id.back2:
				Intent back = new Intent(OptionsActivity.this, WriteNewMemo.class);
				memo.setTitle("");
				memo.setMemo("");
				back.putExtra("memo", memo);
				startActivity(back);
				break;
				
		
			case R.id.buttonTime:
				ChooseTime chooseTime = new ChooseTime();
				chooseTime.show(getFragmentManager(), "Time");
				break;
			
			case R.id.buttonDate:
				ChooseDate chooseDate = new ChooseDate();
				chooseDate.show(getFragmentManager(), "Date");
				break;
				
			case R.id.buttonMap:
                
				Intent StartMap = new Intent(OptionsActivity.this, Map.class);
				StartMap.putExtra("memo", memo);
             	startActivity(StartMap);
             	break;
             	
			case R.id.buttonHere : 
				
				GPSTracker gps = new GPSTracker(this);
				
				if(gps.canGetLocation()){
	                
	                editTextAddress(gps.getAddress(gps.getLatitude(), gps.getLongitude()));
	                memo.setLatitude(gps.getLatitude());
	                memo.setLongitude(gps.getLongitude());
	                
	            }else{
	                // can't get location
	                // GPS or Network is not enabled
	                // Ask user to enable GPS/network in settings
	                gps.showSettingsAlert();
	            }
				break;
			
			case R.id.buttonSave:
				if(selectAddress.length() <6){
				//	Toast.makeText(getApplicationContext(), "Pease enter an address", Toast.LENGTH_SHORT).show();
					
					Builder alertDialogBuilder = new AlertDialog.Builder(OptionsActivity.this);
		    	 	alertDialogBuilder.setTitle("Address error");
		    	 	alertDialogBuilder.setMessage("Please enter an address");
		    	 	
		        		alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
		        			public void onClick(DialogInterface dialog,int id) {
		        				dialog.cancel();
		        			}
		        		});
		        	 
		        		AlertDialog alertDialog = alertDialogBuilder.create();
		        		//Show the dialog
		        		alertDialog.show();
			
				return;
				
				}
				
				else{
					if(selectDate.length() <1 ){
						//	Toast.makeText(getApplicationContext(), "Pease enter an address", Toast.LENGTH_SHORT).show();
							
							Builder alertDialogBuilder = new AlertDialog.Builder(OptionsActivity.this);
				    	 	alertDialogBuilder.setTitle("date error");
				    	 	alertDialogBuilder.setMessage("Please enter a day");
				    	 	
				        		alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
				        			public void onClick(DialogInterface dialog,int id) {
				        				dialog.cancel();
				        			}
				        		});
				        	 
				        		AlertDialog alertDialog = alertDialogBuilder.create();
				        		//Show the dialog
				        		alertDialog.show();
					
						return;}
					else{
						if(selectTime.length() <1){
							//	Toast.makeText(getApplicationContext(), "Pease enter an address", Toast.LENGTH_SHORT).show();
								
								Builder alertDialogBuilder = new AlertDialog.Builder(OptionsActivity.this);
					    	 	alertDialogBuilder.setTitle("time error");
					    	 	alertDialogBuilder.setMessage("Please enter an hour");
					    	 	
					        		alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
					        			public void onClick(DialogInterface dialog,int id) {
					        				dialog.cancel();
					        			}
					        		});
					        	 
					        		AlertDialog alertDialog = alertDialogBuilder.create();
					        		//Show the dialog
					        		alertDialog.show();
						
							return;}
				memo.setAddress(selectAddress.getText().toString());
				memo.setTime(selectTime.getText().toString());
				memo.setDate(selectDate.getText().toString());
				}
				}
				
				DatabaseHandler db = new DatabaseHandler(this);
				try
				{db.updateMemoInformation(memo, memo.getTitle());}
				catch (NullPointerException e){throw e;}
				db.addMemoInformation(memo);
				
				Intent returnToMenu = new Intent(OptionsActivity.this, MainActivity.class);
				startActivity(returnToMenu);
		}
		
	}
	
	
	//Set the address in the EditText.
		public void editTextAddress(String address)
		{
			this.selectAddress.setText(address);
		}
	
		//Set the date in the EditText.
	public void editTextDate(String date)
	{
		this.selectDate.setText(date);
	}

	
	// Set the time in the EditText.
		public void editTextTime(String time)
		{
			this.selectTime.setText(time);
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
