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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_new_memo);
		
		
		this.options = (Button) findViewById(R.id.buttonOption);// Bouton // champ de // l'heure// l'heure
																
		this.options.setOnClickListener(this);
		
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
	
	

}
