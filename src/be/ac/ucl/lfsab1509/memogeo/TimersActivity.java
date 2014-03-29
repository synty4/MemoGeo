package be.ac.ucl.lfsab1509.memogeo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TimersActivity extends Activity implements View.OnClickListener
{
	private Button chooseTime;
	private Button chooseDate;
	private Button openMap;
	private Button geoTimer;
	private Button timeTimer;
	private EditText selectTime;
	private EditText selectDate;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timers);
		
		this.chooseTime = (Button) findViewById(R.id.buttonTime);//Bouton et champ de l'heure
		this.chooseTime.setOnClickListener(this);
		
		this.selectTime = (EditText) findViewById(R.id.editTextTime);
		
		this.chooseDate = (Button) findViewById(R.id.buttonDate);//Bouton et champ de la date
		this.chooseDate.setOnClickListener(this);
		
		this.selectDate = (EditText) findViewById(R.id.editTextDate);
		
		this.openMap = (Button) findViewById(R.id.buttonMap);//Bouton de la map
		this.openMap.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) 
	{
		switch (v.getId())
		{
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
		}
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
