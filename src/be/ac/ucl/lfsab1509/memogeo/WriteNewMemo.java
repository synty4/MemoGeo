package be.ac.ucl.lfsab1509.memogeo;

import java.util.ArrayList;
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
import android.widget.EditText;

public class WriteNewMemo extends Activity implements View.OnClickListener {
	
	private Button options;
	private EditText titleBox;
	private EditText descriptionBox;
	private Button save;
	private Button delete;
	private Button view;
	//DatabaseAdapter dbAdapter;
	//private ArrayList memoList;
	//private DatabaseHandler db;
	//private MemoInformation myMemo;
	List<MemoInformation> memoList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_new_memo);
		
		titleBox=(EditText)findViewById(R.id.editTextTitle);
		descriptionBox=(EditText)findViewById(R.id.editTextDescription);
		
	    save=(Button)findViewById(R.id.buttonSave1);
	  //  save.setOnClickListener(this);
	    
	    delete=(Button)findViewById(R.id.buttonDelete);
	  //  delete.setOnClickListener(this);
	    
	    view=(Button)findViewById(R.id.buttonView);
	  //  view.setOnClickListener(this);
	    
		this.options = (Button) findViewById(R.id.buttonOption);
																
		this.options.setOnClickListener(this);
		
		//memoList = new ArrayList()
		//myMemo = new MemoInformation();
		memoList = new ArrayList<MemoInformation>();
			
		
	}	
	
	@Override
	public void onClick(View v) {
	
		//if(v.getId() == R.id.buttonSave1){
			
		//}else if(v.getId() == R.id.buttonOption){
			// code pour lancer les options.
			// 1. create intent.
			Intent intent = new Intent(WriteNewMemo.this, OptionsActivity.class);

			// 2. create memo object.
			Memo memo = new Memo();
			//editTextMemo = (EditText) findViewById(R.id.editTextMem);
			//memo.setMemo(editTextMemo.getText().toString());

			// 3. put object in the intent.
			intent.putExtra("memo", memo);

			// 4. Start the next activity.
			startActivity(intent);
			
		//}
		//else if(v.getId() == R.id.buttonDelete){}
		//else{}
		
		
	}
	
	public void add(View view){
		DatabaseHandler db = new DatabaseHandler(this);
		
		MemoInformation myMemo= new MemoInformation(titleBox.getText().toString(), descriptionBox.getText().toString());
		
		db.addMemo(myMemo);
		
		//Get the values provided by the user via the UI
		//String title = titleBox.getText().toString();
		//String description= descriptionBox.getText().toString();
		
		// Pass above values to the setter methods in memoInformation class
		//myMemo.setTitle(title);
		//myMemo.setDescription(description);

		// Add a memo with its all details to an ArrayList
		//memoList.add(myMemo);

		// Inserting undergraduate details to the database is doing in a separate method
		
		//db.addMemo(myMemo);	
		//db.close();
		myMemo.setTitle("");
		myMemo.setDescription("");
		
		Log.d("Inserting",myMemo.toString());
		
		//DatabaseHandler dbHandler = new DatabaseHandler(this);  	
  	  // MemoInformation myMemo = new MemoInformation(titleBox.getText().toString(), descriptionBox.getText().toString());
  	  // db.addMemo(myMemo);
  	  // titleBox.setText("");
  	   //descriptionBox.setText("");
	}
	
    public void view (View view) {
    	DatabaseHandler db = new DatabaseHandler(this);
	
	     MemoInformation myMemo= db.getMemoInfo(titleBox.getText().toString());
			
			db.addMemo(myMemo);

	     if (myMemo != null) {
		   titleBox.setText(String.valueOf(myMemo.getTitle()));
		   descriptionBox.setText(String.valueOf(myMemo.getDescription()));
      } else {
	         titleBox.setText("No Match Found");
      }        	
  }
	
	
	/**public void view(){
	
		
		MemoInformation memoFromDb = db.getMemoInfo(myMemo.getId());
		String titleFromDb = memoFromDb.getTitle().toString();
		String descriptionFromDb = memoFromDb.getDescription().toString();
		titleBox.setText(titleFromDb);
		descriptionBox.setText(descriptionFromDb);
	    //Si un memo est retourn� (donc si le memo � bien �t� ajout� � la BDD)
	        if(memoFromDb != null){
	        	//On affiche les infos du memo dans les editText du titre et de la description
	        	titleBox.setText(titleFromDb);
	    		descriptionBox.setText(descriptionFromDb);
	        	
	        }
	        
	        db.close();
	}
	
	public void delete(){}
	
	public void update(){
		
		//On modifie le titre du livre
    	//memoFromDb.setTitle("J'ai modifi� le titre du livre");
    	//Puis on met � jour la BDD
       // db.updateMemo(memoFromDb);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.writenewmemo, menu);

		return true;
	}
**/
}
