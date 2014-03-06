package be.ac.ucl.lfsab1509.memogeo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OptionsActivity extends Activity implements View.OnClickListener {

	private Button chooseTime;
	private EditText selectTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		this.chooseTime = (Button) findViewById(R.id.buttonTime);
		this.chooseTime.setOnClickListener(this);
		
		this.selectTime = (EditText) findViewById(R.id.editTextTime);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId())
		{
			case R.id.buttonTime:
				ChooseTime chooseTime = new ChooseTime();
				chooseTime.show(getFragmentManager(), "Time");
				break;
		}
		
	}
	
	public void EditTextTime(String time)
	{
		this.selectTime.setText(time);
	}

}
