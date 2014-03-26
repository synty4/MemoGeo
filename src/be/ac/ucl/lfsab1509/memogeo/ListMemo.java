


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
import android.widget.EditText;
import android.app.ListActivity;
import android.content.Intent;



public class ListMemo extends ListActivity  implements View.OnClickListener
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

	 
	 @Override
		public void onClick(View v) {
			// 1. create intent.
						Intent intent = new Intent(ListMemo.this, WriteNewMemo.class);

						// 2. create memo object.
						MemoInformation memo = new MemoInformation();
						

						// 3. put object in the intent.
						//intent.putExtra("memo", memo);

						// 4. Start the next activity.
						startActivity(intent);
			
		}
 
	 
		public void home(View v) {
			
			//if(v.getId() == R.id.buttonSave1){
				
			//}else if(v.getId() == R.id.buttonOption){
				// code pour lancer les options.
				// 1. create intent.
				Intent intent = new Intent(ListMemo.this, MainActivity.class);

				// 2. create memo object.
				Memo memo = new Memo();
				//editTextMemo = (EditText) findViewById(R.id.editTextMem);
				//memo.setMemo(editTextMemo.getText().toString());

				// 3. put object in the intent.
				intent.putExtra("memo", memo);

				// 4. Start the next activity.
				startActivity(intent);
				
			
			
			
		}

		
}