package be.ac.ucl.lfsab1509.memogeo;

import DataBase.DatabaseHandler;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WriteNewMemo extends Activity implements View.OnClickListener {
	
	private Button options;
 	private EditText titleBox;
 	private EditText descriptionBox;
 	private Button save;
 	Memo memo;
	
	@Override
 	protected void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
 		setContentView(R.layout.write_new_memo);
 		
 		titleBox=(EditText)findViewById(R.id.editTextTitle);
 		descriptionBox=(EditText)findViewById(R.id.editTextDescription);
 		
 		Intent memoReceiver = getIntent();
 		memo = (Memo) memoReceiver.getSerializableExtra("memo");
 		
 		titleBox.setText(memo.getTitle());
 		descriptionBox.setText(memo.getMemo());
 		
 	    this.options = (Button) findViewById(R.id.buttonOption);
 		this.options.setOnClickListener(this);
	    
 		this.save = (Button) findViewById(R.id.buttonSave1);
 		this.save.setOnClickListener(this);	
 		
 	}	
 	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonOption:
			Intent intent = new Intent(WriteNewMemo.this, OptionsActivity.class);
			memo.setTitle(titleBox.getText().toString());
			memo.setMemo(descriptionBox.getText().toString());
			intent.putExtra("memo", memo);
			startActivity(intent);
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
 		
 		Memo myMemo= new Memo();
 		myMemo.setTitle(titleBox.getText().toString());
 		myMemo.setMemo(descriptionBox.getText().toString());
 		

		db.addMemoInformation(myMemo);	
 	}
	

    public void view (View view) {
     	DatabaseHandler db = new DatabaseHandler(this);
 	
	     //MemoInformation myMemo= db.getMemoInfo(titleBox.getText().toString());
	     Memo myMemo= db.getMemoInformation(titleBox.getText().toString());
 			
			
			db.addMemoInformation(myMemo);
 
 	     if (myMemo != null) {
 		   titleBox.setText(String.valueOf(myMemo.getTitle()));
 		   descriptionBox.setText(String.valueOf(myMemo.getMemo()));
       } else {
 	         titleBox.setText("No Match Found");
       }        	
   }
 }