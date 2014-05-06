
package be.ac.ucl.lfsab1509.memogeo;

import java.util.ArrayList;
import java.util.List;

import DataBase.DatabaseHandler;
import DataBase.MemoInformation;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import java.util.Arrays;



//public class ListMemo extends ListActivity  implements View.OnClickListener
public class ListMemo extends Activity implements View.OnClickListener
{    
	
	private CustomCursorAdapter customAdapter;
	DatabaseHandler db;
	private ListView listdata;
	//private Button addListMemo;
	private Button deleteListMemo;
	private Button chooseDate;
	private MemoInformation memo;
	
	//private ListView lv;
	//private ArrayAdapter<String> adapter;
	//private List<String> arr;
	
   // String[] memo = { "acheter un bic", "voir oncle Tibo", "acheter un plumier", "Aller à la commune", ".Net", "SQL" };
	   
		 public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.list_memo);
		    db = new DatabaseHandler(this);
		  
		    listdata = (ListView)findViewById(R.id.list_data);
		    final Cursor c = db.getAllTable1();
		    customAdapter = new CustomCursorAdapter(this.getApplicationContext(),c);
		    listdata.setAdapter(customAdapter);
		    
		    /**lv.setOnItemLongClickListener(new OnItemLongClickListener() {
	            // setting onItemLongClickListener and passing the position to the function
	               
		    	 // setting onItemLongClickListener and passing the position to the function
                @Override
              public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
              int position, long arg3) {
              removeItemFromList(position);   
          
             return true;
      }**/
                
		    listdata.setOnItemClickListener(new OnItemClickListener() 
                {
                   	@Override
                   	    public void onItemClick(AdapterView<?> adapter, View view, final int position, long arg)   {
                        		
                   		Object listItem =  listdata.getItemAtPosition(position);
                   		Toast.makeText(getApplicationContext(), "You selected item "+ position + ": " + listItem, Toast.LENGTH_SHORT).show();
                        		
                   	    Builder alertDialogBuilder = new AlertDialog.Builder(ListMemo.this);
                   	 	alertDialogBuilder.setTitle("Delete item");
                   	 	alertDialogBuilder.setMessage("Are you sure?");
                   	 	alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                   			public void onClick(DialogInterface dialog,int id) {
                				//listdata.removeViewAt(position);
                   			    memo = new MemoInformation();
                   				db.deleteMemoInformation(memo);
                   				customAdapter.notifyDataSetChanged();   
                  				}
                  		    });
                       		alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                       			public void onClick(DialogInterface dialog,int id) {
                       				dialog.cancel();
                       			}
                       		});
                       	 
                       		AlertDialog alertDialog = alertDialogBuilder.create();
                       		alertDialog.show();
                        		
                     	}
                });         


		    	  
		    		// button  list add
		    		final Button addListMemo = (Button) findViewById(R.id.buttonAdd);
		    		addListMemo.setOnClickListener(this);
		    		// button list delete
		    		final Button deleteListMemo = (Button) findViewById(R.id.delete_list_memo);
		    		this.deleteListMemo = (Button) findViewById(R.id.delete_list_memo);//Bouton et champ de la date
		    		this.deleteListMemo.setOnClickListener(this);
		    	 	
		     	 }
	 
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	 
	 //@Override
		/*public void onClick(View v) {
			// 1. create intent.
						Intent intent = new Intent(ListMemo.this, WriteNewMemo.class);

						// 2. create memo object.
						MemoInformation memo = new MemoInformation();
						

						// 3. put object in the intent.
						//intent.putExtra("memo", memo);

						// 4. Start the next activity.
						startActivity(intent);
			
		}*/

	 @Override
		public void onClick(View v) {
			switch (v.getId()) {

			case R.id.buttonAdd:
				Intent listaddNewMemo = new Intent(ListMemo.this, WriteNewMemo.class);
				startActivity(listaddNewMemo);
				break;
			case R.id.delete_list_memo:
				Intent listdeleteMemo = new Intent(ListMemo.this, ListMemo.class);
				startActivity(listdeleteMemo);
				break;
				

			}

		}

	/*	public void home(View v) {
			
			//if(v.getId() == R.id.buttonSave1){
				
			//}else if(v.getId() == R.id.buttonOption){
				// code pour lancer les options.
				// 1. create intent.
				Intent intent = new Intent(ListMemo.this, MainActivity.class);

				// 2. create memo object.
				Memo memo = new Memo();
				//editTextMemo = (EditText) findViewById(R.id.editTextMem);
				//memo.setMemo(editTextMemo.getText().toString());

				// 3. put object in the intent.
				intent.putExtra("memo", memo);

				// 4. Start the next activity.
				startActivity(intent);
				
			
			
			
		}*/

		
}
