package be.ac.ucl.lfsab1509.memogeo;

import DataBase.DatabaseHandler;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

//public class ListMemo extends ListActivity  implements View.OnClickListener
public class ListMemo extends Activity implements View.OnClickListener {

	private CustomCursorAdapter customAdapter;
	DatabaseHandler db;
	private ListView listData;
	private Button delete;
	private Button add;
	private Button view;
	private int index;
	private Cursor c;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_memo);
		
		this.delete = (Button) findViewById(R.id.delete_list_memo);
		this.delete.setOnClickListener(this);
		
		this.add = (Button) findViewById(R.id.buttonAdd);
		this.add.setOnClickListener(this);
		
		this.view = (Button) findViewById(R.id.buttonView);
		this.view.setOnClickListener(this);
		
		db = new DatabaseHandler(this);

		listData = (ListView) findViewById(R.id.list_data);
		listData.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listData.getItemsCanFocus();//Pas sûr que cela fonctionne.
		
		c = db.getAllTable();
		customAdapter = new CustomCursorAdapter(this.getApplicationContext(), c);

		listData.setAdapter(customAdapter);


		
		listData.setOnItemClickListener(new OnItemClickListener()
	    {
	      public void onItemClick(AdapterView<?> parent, View view,
	          int position, long id) {

	    	  index = position+1;
	    	  Toast.makeText(getApplicationContext(), "Item Clicked "+ index, Toast.LENGTH_SHORT).show();
	         
	      }
	    });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	
	@Override
	public void onClick(View v) {
		
		Intent intent = new Intent(ListMemo.this, WriteNewMemo.class);
		Memo memo = new Memo();
		
		switch (v.getId()) {

		case R.id.delete_list_memo:
			if(c.moveToPosition(index-1))
			{
				db.deleteMemoInformation(c.getString(c.getColumnIndex(c.getColumnName(1))));
				Intent refresh = getIntent();
				finish();
				startActivity(refresh);
			}
			break;
		case R.id.buttonAdd:
			intent.putExtra("memo", memo);
			startActivity(intent);
			break;
		case R.id.buttonView:
			if(c.moveToPosition(index-1))
				memo = db.getMemoInformation(c.getString(c.getColumnIndex(c.getColumnName(1))));
			intent.putExtra("memo", memo);
			startActivity(intent);
			break;	
		}
	}

	public void home(View v) {

		// if(v.getId() == R.id.buttonSave1){

		// }else if(v.getId() == R.id.buttonOption){
		// code pour lancer les options.
		// 1. create intent.
		Intent intent = new Intent(ListMemo.this, MainActivity.class);

		// 2. create memo object.
		Memo memo = new Memo();
		// editTextMemo = (EditText) findViewById(R.id.editTextMem);
		// memo.setMemo(editTextMemo.getText().toString());

		// 3. put object in the intent.
		intent.putExtra("memo", memo);

		// 4. Start the next activity.
		startActivity(intent);

	}
}
