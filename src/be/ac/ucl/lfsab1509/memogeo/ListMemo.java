


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
import android.app.ListActivity;
import android.content.Intent;


public class ListMemo extends ListActivity implements View.OnClickListener
{
    private Button deleteListMemo;
    private Button chooseDate;
    
	 public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.list_memo);
	    DatabaseHandler db = new DatabaseHandler(this);
	    List<MemoInformation> liste = db.getAllMemoInformation();
	  
	    ArrayAdapter<MemoInformation> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,liste);
	    setListAdapter(adapter);

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
		    
	// delete 2
	    final Button deleteListMemo = (Button) findViewById(R.id.delete_list_memo);
		this.deleteListMemo = (Button) findViewById(R.id.delete_list_memo);//Bouton et champ de la date
		this.deleteListMemo.setOnClickListener(this);
	 
	 
		
	 }
	 public void onClick(View v) {
			
		}
	 
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}



    
}
