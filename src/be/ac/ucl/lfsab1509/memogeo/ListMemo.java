


package be.ac.ucl.lfsab1509.memogeo;

import java.util.ArrayList;
import java.util.List;

import DataBase.DatabaseHandler;
import DataBase.MemoInformation;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.app.ListActivity;


public class ListMemo extends ListActivity
{
    
	 public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.list_memo);
	    DatabaseHandler db = new DatabaseHandler(this);
	    List<MemoInformation> liste = db.getAllMemoInformation();
	  
	    ArrayAdapter<MemoInformation> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,liste);
	    setListAdapter(adapter);

		    
	 }
	 
	 
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}



    
}
