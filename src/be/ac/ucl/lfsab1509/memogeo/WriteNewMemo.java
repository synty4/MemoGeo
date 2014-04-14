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
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class WriteNewMemo extends Activity implements View.OnClickListener {
	
	private Button options;
 	private EditText titleBox;
 	private EditText descriptionBox;
 	private Button save;
 	private Button buttonNext;
 	private Button buttonBack;
 	List<MemoInformation> memoList;
	
	@Override
 	protected void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.write_new_memo);
 		// Set a custom titlebar.
	    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
	   //Lets set the R.layout.customtitlebar to the window.
	    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);
		
	    memoList = new ArrayList<MemoInformation>();
 		
 		titleBox=(EditText)findViewById(R.id.editTextTitle);
 		descriptionBox=(EditText)findViewById(R.id.editTextDescription);
 		
 		this.save = (Button) findViewById(R.id.buttonSave1);
 		this.save.setOnClickListener(this);
 		
 	    // view=(Button)findViewById(R.id.buttonView);
 	    this.options = (Button) findViewById(R.id.buttonOption);
 		this.options.setOnClickListener(this);
 		
        this.buttonNext = (Button) findViewById(R.id.buttonNext);
        this.buttonNext.setOnClickListener(this);
        
        this.buttonBack = (Button) findViewById(R.id.buttonBack);
        this.buttonBack.setOnClickListener(this); 
 			
 		
 	}	
 	
	
	/**@Override
	public void onClick(View v) {
	
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
		
	}
	*/
	
	@Override
 	public void onClick(View v) {
		 		switch (v.getId()) {

		 		case R.id.buttonSave1:
		 			Intent intent = new Intent(WriteNewMemo.this, OptionsActivity.class);
		 			Memo memo = new Memo();
		 			intent.putExtra("memo", memo);
		 			startActivity(intent);
		 			break;
		 		case R.id.buttonNext:
		 			Intent Options = new Intent(WriteNewMemo.this, OptionsActivity.class);
		 			startActivity(Options);
		 			break;
		 		case R.id.buttonBack:
		 			Intent Main = new Intent(WriteNewMemo.this, MainActivity.class);
	            	startActivity(Main);
                    break;
        		
		 		}

		 	}
	
	public void back(View v) {
		
		//if(v.getId() == R.id.buttonSave1){
			
		//}else if(v.getId() == R.id.buttonOption){
			// code pour lancer les options.
			// 1. create intent.
			Intent intent = new Intent(WriteNewMemo.this, MainActivity.class);

			// 2. create memo object.
			Memo memo = new Memo();
			//editTextMemo = (EditText) findViewById(R.id.editTextMem);
			//memo.setMemo(editTextMemo.getText().toString());

			// 3. put object in the intent.
			intent.putExtra("memo", memo);

			// 4. Start the next activity.
			startActivity(intent);
			
		
		
		
	}
	
	
	public void clear(){

		titleBox.setText("");
		descriptionBox.setText("");
		
	}
	
	public void add(View view){
 		DatabaseHandler db = new DatabaseHandler(this);
 		
 		MemoInformation myMemo= new MemoInformation(titleBox.getText().toString(), descriptionBox.getText().toString());
 		

		db.addMemoInformation(myMemo);
 	
 		myMemo.setTitle("");
 		myMemo.setDescription("");
 		clear();
 		
 		Log.d("Inserting",myMemo.toString());
 		
 	}
	

    public void view (View view) {
     	DatabaseHandler db = new DatabaseHandler(this);
 	
	     //MemoInformation myMemo= db.getMemoInfo(titleBox.getText().toString());
	     MemoInformation myMemo= db.getMemoInformation(titleBox.getText().toString());
 			
			
			db.addMemoInformation(myMemo);
 
 	     if (myMemo != null) {
 		   titleBox.setText(String.valueOf(myMemo.getTitle()));
 		   descriptionBox.setText(String.valueOf(myMemo.getDescription()));
       } else {
 	         titleBox.setText("No Match Found");
       }        	
   }
 }