

package be.ac.ucl.lfsab1509.memogeo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class WriteNewMemo extends Activity 
{

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_new_memo);
        
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {               
            	Intent OptionsActivity = new Intent(WriteNewMemo.this, OptionsActivity.class);
            	startActivity(OptionsActivity);
            }            	
        });
    }
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
      //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.writenewmemo, menu);
        
        
        return true;
    }
    
}
