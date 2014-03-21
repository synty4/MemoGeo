package be.ac.ucl.lfsab1509.memogeo;

import java.util.List;

import DataBase.DatabaseHandler;
import DataBase.MemoInformation;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
	private Button newMemo;
	private Button map;
	private Button memoList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.newMemo = (Button) findViewById(R.id.new_memo);
		this.newMemo.setOnClickListener(this);

		this.map = (Button) findViewById(R.id.open_map);
		this.map.setOnClickListener(this);

		this.memoList = (Button) findViewById(R.id.list_memo);
		this.memoList.setOnClickListener(this);

		/* test affichage dans l'onglet log */
		/*
		 * DatabaseHandler db;
		 * 
		 * db = new DatabaseHandler(this);
		 * 
		 * Log.d("Insert: ", "Inserting .."); MemoInformation memoInfoUn = new
		 * MemoInformation
		 * ("Title: acheteer medicament","description: pour mal de tete "
		 * ,"Adresse: place ...."); db.addMemo(memoInfoUn);
		 * Log.d("ONE ************************** ", "**********************");
		 * 
		 * MemoInformation memoInfoDeux = new
		 * MemoInformation("livre calculabiliterrrr"
		 * ,"biblio ingifffff ","placeffff ste barbe 2");
		 * db.addMemo(memoInfoDeux); Log.d("TWO ************************** ",
		 * "**********************");
		 * 
		 * /*test suppression MemoInformation dans l'onglet log: suppression et
		 * affichage apr�s suppression
		 */
		/*
		 * Log.d("************ DELETE ************** ",
		 * "**********************");
		 * Log.d("idd",String.valueOf(memoInfoUn.getId()));
		 * Log.d("titles",memoInfoUn.getTitle());
		 */

		/*
		 * Log.d("Reading: ", "Reading all memoInfo.."); List<MemoInformation>
		 * memoInfo = db.getAllMemoInfo(); for (MemoInformation i : memoInfo) {
		 * String log = i.toString();
		 * 
		 * // Writing MemoInformation to log Log.d("Title: ", log); }
		 */
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_memo:
			Intent WriteNewMemo = new Intent(MainActivity.this,WriteNewMemo.class);
			startActivity(WriteNewMemo);
			break;
		case R.id.open_map:
			Intent Map = new Intent(MainActivity.this, Map.class);
			startActivity(Map);
			break;
		case R.id.list_memo:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}