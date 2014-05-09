package be.ac.ucl.lfsab1509.memogeo;

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
 	//private Button save;
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
	    
 		//this.save = (Button) findViewById(R.id.buttonSave1);
 		//this.save.setOnClickListener(this);	
 		
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
	
			Intent intent = new Intent(WriteNewMemo.this, MainActivity.class);
			intent.putExtra("memo", memo);
			startActivity(intent);
	}
	
	
	public void clear(){

		titleBox.setText("");
		descriptionBox.setText("");
		
	}
 }