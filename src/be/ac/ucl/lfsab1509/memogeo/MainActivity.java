package be.ac.ucl.lfsab1509.memogeo;

import DataBase.DatabaseHandler;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	private Button newMemo;
	private Button map;
	private Button memoList;
	private Button timers;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/* test affichage dans l'onglet log */
		DatabaseHandler db;
		db = new DatabaseHandler(this);

		this.newMemo = (Button) findViewById(R.id.new_memo);
		this.newMemo.setOnClickListener(this);

		this.map = (Button) findViewById(R.id.open_map);
		this.map.setOnClickListener(this);

		this.memoList = (Button) findViewById(R.id.list_memo);
		this.memoList.setOnClickListener(this);

		this.timers = (Button) findViewById(R.id.timers);
		this.timers.setOnClickListener(this);

		Memo memo1 = new Memo("Medicament","Mal de tete ", "Traverse d'Esope 5, 1348", 0.0 , 0.0 , "20:42", "20/12/2014");

		db.addMemoInformation(memo1);
		
		Memo memo2 = new Memo("Livre calculabilité", "Biblio INGI ",
				" Place Sainte Barbe 2, 1348", 0.0 , 0.0 ,"12:12", "30/06/2014");
		db.addMemoInformation(memo2);
	}

	@Override
	public void onClick(View v) {
		Memo memo = new Memo();
		switch (v.getId()) {

		case R.id.new_memo:
			Intent writeNewMemo = new Intent(MainActivity.this,
					WriteNewMemo.class);
			memo.setTitle("");
			memo.setMemo("");
			writeNewMemo.putExtra("memo", memo);
			startActivity(writeNewMemo);
			break;
		case R.id.open_map:
			memo.setTitle("");
			memo.setMemo("");
			Intent map = new Intent(MainActivity.this, Map.class);
			map.putExtra("memo", memo);
			startActivity(map);
			break;
		case R.id.list_memo:
			Intent ListMemo = new Intent(MainActivity.this, ListMemo.class);
			startActivity(ListMemo);
			break;
		case R.id.timers:
			Intent Timers = new Intent(MainActivity.this, TimersActivity.class);
			startActivity(Timers);
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}