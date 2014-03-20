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
	private EditText selectTime;
	private EditText selectDate;
	private TextView test;
	
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
		
		this.openMap = (Button) findViewById(R.id.buttonMap);//Bouton et champ de l'heure
		this.openMap.setOnClickListener(this);
		
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
		/*
		switch (v.getId())
		{
			case R.id.buttonTime:
				ChooseTime chooseTime = new ChooseTime();
				chooseTime.show(getFragmentManager(), "Time");
				break;
			
			case R.id.buttonDate:
				ChooseDate chooseDate = new ChooseDate();
				chooseDate.show(getFragmentManager(), "Date");
				break;
				
			case R.id.buttonMap:
				Intent Map = new Intent(OptionsActivity.this, Map.class);
             	startActivity(Map);
		}
		*/
	}
	
	// Set the time in the EditText.
	public void EditTextTime(String time)
	{
		this.selectTime.setText(time);
	}
	
	//Set the date in the EditText.
	public void EditTextDate(String date)
	{
		this.selectDate.setText(date);
	}

}
