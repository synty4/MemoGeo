package be.ac.ucl.lfsab1509.memogeo;

import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
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
	private MediaPlayer mp = new MediaPlayer();
	
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
		
		this.timeTimer = (Button) findViewById(R.id.buttonTimeTimer);//Bouton Time Timer
		this.timeTimer.setOnClickListener(this);
		
		this.geoTimer = (Button) findViewById(R.id.buttonGeoTimer);//Bouton Geo Timer
		this.geoTimer.setOnClickListener(this);
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
             	break;
             	
			case R.id.buttonTimeTimer:
				
				if(mp.isPlaying())//TEST d'une sonnerie ne fonctionne qu'une seule fois.
		        {  
		            mp.stop();
		            mp.reset();
		        } 
		        try {

		            AssetFileDescriptor afd;
		            afd = getAssets().openFd("game over.mp3");
		            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
		            mp.prepare();
		            mp.start();
		        } catch (IllegalStateException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        } 
				
				break;
			case R.id.buttonGeoTimer:
				//TODO
				break;
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
