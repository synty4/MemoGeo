


package be.ac.ucl.lfsab1509.memogeo;

import java.util.ArrayList;
import java.util.List;

import DataBase.DatabaseHandler;
import DataBase.MemoInformation;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;


public class ListMemo extends Activity implements View.OnClickListener
{
    private Button deleteListMemo;
    private Button chooseDate;
	private CustomCursorAdapter customAdapter;
	DatabaseHandler db;
	private ListView listdata;

    
	 public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.list_memo);
	   
	    db = new DatabaseHandler(this);
		listdata = (ListView)findViewById(R.id.list_data);
		Cursor c = db.getAllTable1();
	    customAdapter = new CustomCursorAdapter(this.getApplicationContext(),c);
    	listdata.setAdapter(customAdapter);
    	
	   // DatabaseHandler db = new DatabaseHandler(this);
	   // List<MemoInformation> liste = db.getAllMemoInformation();
	  
	 //   ArrayAdapter<MemoInformation> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,liste);
	  //  setListAdapter(adapter);

	 // button to delete  list memo  in list memo activity
		/*final Button ListMemo = (Button) findViewById(R.id.delete_list_memo);// A changer par méthode plus propre
		ListMemo.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {               
            	Intent WriteNewMemo = new Intent(ListMemo.this, WriteNewMemo.class);
            	startActivity(WriteNewMemo);
            }            	
        });*/
		    
 
		
	 }
	
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}



    
}
