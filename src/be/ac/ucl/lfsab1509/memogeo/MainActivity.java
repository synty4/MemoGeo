package be.ac.ucl.lfsab1509.memogeo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button NewMemo = (Button) findViewById(R.id.new_memo);
		NewMemo.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {               
            	Intent WriteNewMemo = new Intent(MainActivity.this, WriteNewMemo.class);
            	startActivity(WriteNewMemo);
            }            	
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		return true;
	}

}