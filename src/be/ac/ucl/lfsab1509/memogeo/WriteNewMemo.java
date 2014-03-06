package be.ac.ucl.lfsab1509.memogeo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WriteNewMemo extends Activity implements View.OnClickListener {
	private Button options;
	private EditText editTextMemo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_new_memo);

		this.options = (Button) findViewById(R.id.buttonOption);// Bouton et
																// champ de
																// l'heure
		this.options.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// code pour lancer les options.
		// 1. create intent.
		Intent intent = new Intent(WriteNewMemo.this, OptionsActivity.class);

		// 2. create memo object.
		Memo memo = new Memo();
		editTextMemo = (EditText) findViewById(R.id.editTextMem);
		memo.setMemo(editTextMemo.getText().toString());

		// 3. put object in the intent.
		intent.putExtra("memo", memo);

		// 4. Start the next activity.
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.writenewmemo, menu);

		return true;
	}

}
