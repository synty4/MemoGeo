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

	
	List<MemoInformation> memoList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_new_memo);
		
		titleBox=(EditText)findViewById(R.id.editTextTitle);
		descriptionBox=(EditText)findViewById(R.id.editTextDescription);
		
	    save = (Button) findViewById(R.id.buttonSave1);
	  // view=(Button)findViewById(R.id.buttonView);
	    
		this.options = (Button) findViewById(R.id.buttonOption);// Bouton // champ de // l'heure// l'heure													
		this.options.setOnClickListener(this);
		memoList = new ArrayList<MemoInformation>();
			
		
	}	
	
	@Override
	public void onClick(View v) {
			// 1. create intent.
			Intent intent = new Intent(WriteNewMemo.this, OptionsActivity.class);
			// 2. create memo object.
			Memo memo = new Memo();
			// 3. put object in the intent.
			intent.putExtra("memo", memo);
			// 4. Start the next activity.
			startActivity(intent);
	}
	
	public void add(View view){
		DatabaseHandler db = new DatabaseHandler(this);
		
		MemoInformation myMemo= new MemoInformation(titleBox.getText().toString(), descriptionBox.getText().toString());
		
		db.addMemoInformation(myMemo);
	
		myMemo.setTitle("");
		myMemo.setDescription("");
		
		Log.d("Inserting",myMemo.toString());
		
	}
	
    public void view (View view) {
    	DatabaseHandler db = new DatabaseHandler(this);
	
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
