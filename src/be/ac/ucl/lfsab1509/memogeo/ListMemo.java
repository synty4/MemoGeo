


package be.ac.ucl.lfsab1509.memogeo;

import java.util.ArrayList;
import java.util.List;

import DataBase.DatabaseHandler;
import DataBase.MemoInformation;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.app.ListActivity;
import android.content.Intent;



public class ListMemo extends ListActivity  implements View.OnClickListener
{    
	
private Button edit;
private Button delete;
private Button view;
private Button add;
private EditText titleBox;
private EditText descriptionBox;
List<MemoInformation> memoList;

    
	 public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.list_memo);
	    
	    DatabaseHandler db = new DatabaseHandler(this);
	    List<MemoInformation> liste = db.getAllMemoInformation();
	    ArrayAdapter<MemoInformation> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,liste);
	    setListAdapter(adapter);
	    
	    titleBox=(EditText)findViewById(R.id.editTextTitle);
		descriptionBox=(EditText)findViewById(R.id.editTextDescription);
		
		add=(Button)findViewById(R.id.buttonAdd);
	    edit=(Button)findViewById(R.id.buttonEdit);
	    delete=(Button)findViewById(R.id.buttonDelete);
		view=(Button)findViewById(R.id.buttonView);
		
		this.add = (Button) findViewById(R.id.buttonAdd);// Bouton // champ de // l'heure// l'heure
		
		this.add.setOnClickListener(this);
		
		//memoList = new ArrayList<MemoInformation>();
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
 

		
}
