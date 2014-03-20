package be.ac.ucl.lfsab1509.memogeo;

import java.util.List;

import DataBase.DatabaseHandler;
import DataBase.MemoInformation;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*test affichage dans l'onglet log*/
		DatabaseHandler db;
		
		 db = new DatabaseHandler(this);
		 
		Log.d("Insert: ", "Inserting .."); 
		MemoInformation memoInfoUn = new MemoInformation("Title: acheter medicament","description: pour mal de tete ","Traverse d'Esope 5, 1348");
	    db.addMemoInformation(memoInfoUn); 
	    Log.d("ONE ************************** ", "**********************"); 
		
	    MemoInformation memoInfoDeux = new MemoInformation("livre calculabilité","biblio INGI "," Place Sainte Barbe 2, 1348");
	    db.addMemoInformation(memoInfoDeux); 
	    Log.d("TWO ************************** ", "**********************"); 
    
	    /*test suppression MemoInformation dans l'onglet log:  suppression et affichage après suppression*/
	   /* Log.d("************ DELETE ************** ", "**********************"); 
	    Log.d("idd",String.valueOf(memoInfoUn.getId()));
	    Log.d("titles",memoInfoUn.getTitle());*/
	    
	    Log.d("Reading: ", "Reading all memoInfo.."); 
        List<MemoInformation> memoInfo = db.getAllMemoInformation(); 
         	for (MemoInformation i : memoInfo) {
         		String log = i.toString();
	            
         		// Writing MemoInformation to log
	            Log.d("Title: ", log);
         	}
	    
	   
	   
		final Button NewMemo = (Button) findViewById(R.id.new_memo);// A changer par méthode plus propre
		NewMemo.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {               
            	Intent WriteNewMemo = new Intent(MainActivity.this, WriteNewMemo.class);
             	startActivity(WriteNewMemo);
            }            	
        });
		
		
		final Button OpenMap = (Button) findViewById(R.id.open_map);// A changer par méthode plus propre
		OpenMap.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {               
            	Intent Map = new Intent(MainActivity.this, Map.class);
             	startActivity(Map);
            }            	
        });
		
		// button to list memo  in the main activity
					final Button ListMemo = (Button) findViewById(R.id.list_memo);// A changer par méthode plus propre
					ListMemo.setOnClickListener(new View.OnClickListener() 
			        {
			            public void onClick(View v) 
			            {               
			            	Intent ListMemo = new Intent(MainActivity.this, ListMemo.class);
			            	startActivity(ListMemo);
			            }            	
			        });
		
		     
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}