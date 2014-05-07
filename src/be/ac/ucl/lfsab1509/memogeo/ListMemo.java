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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.app.AlertDialog.Builder;

//public class ListMemo extends ListActivity  implements View.OnClickListener
public class ListMemo extends Activity implements View.OnClickListener {

	private CustomCursorAdapter customAdapter;
	DatabaseHandler db;
	private ListView listData;
	private Button delete;
	private Button deleteAll;
	private Button add;
	private Button view;
	
	private int index;
	private Cursor c=null;
	String memoId;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_memo);
		
		this.delete = (Button) findViewById(R.id.delete_list_memo);
		this.delete.setOnClickListener(this);
		
		this.add = (Button) findViewById(R.id.buttonAdd);
		this.add.setOnClickListener(this);
		
		this.view = (Button) findViewById(R.id.buttonView);
		this.view.setOnClickListener(this);
		
		this.deleteAll = (Button) findViewById(R.id.buttonDeleteAll);
		this.deleteAll.setOnClickListener(this);
		
		db = new DatabaseHandler(this);
		//codes d'Olivier
		listData = (ListView) findViewById(R.id.list_data);
		listData.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listData.getItemsCanFocus();//Pas sûr que cela fonctionne.
		
		
		
		c = db.getAllTable();
		customAdapter = new CustomCursorAdapter(this.getApplicationContext(), c);

		listData.setAdapter(customAdapter);
		/**
		listData.setOnItemClickListener(new OnItemClickListener()
	    {
	      public void onItemClick(AdapterView<?> parent, View view,
	          int position, long id) {

	    	  index = position+1;
	    	  Toast.makeText(getApplicationContext(), "Item Clicked "+ index, Toast.LENGTH_SHORT).show();
	         
	      }
	    });
		**/
		
		 listData.setOnItemClickListener(new OnItemClickListener() 
         { //J'ai ajouté long id commme parametre
            	@Override
            	    public void onItemClick(AdapterView<?> adapter, View view, final int position, long id)   {
                //Get the id of the item in the list clicked on
            		//this will be used to update the changes
            		memoId = String.valueOf(id);
            		index = position+1;
            		
            		Object listItem =  listData.getItemAtPosition(position);
            		//ListViewItem item = items.get(position);
            		Toast.makeText(getApplicationContext(), "You selected item "+ position + ": " + listItem, Toast.LENGTH_SHORT).show();
            	}          		
            	    
         }); 
	} 
		 		
	    	 	
@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	return true;
}


public void delete(View v){
	
	if(c.moveToPosition(index-1))
	{
		db.deleteMemoInformation(c.getString(c.getColumnIndex(c.getColumnName(1))));
		Intent refresh = getIntent();
		finish();
		startActivity(refresh);
	
	}
}

	
	@Override
	public void onClick(final View v) {
		
		Intent intent = new Intent(ListMemo.this, WriteNewMemo.class);
		Memo memo = new Memo();
		
		switch (v.getId()) {

		case R.id.delete_list_memo:
		{
			Builder alertDialogBuilder = new AlertDialog.Builder(ListMemo.this);
    	 	alertDialogBuilder.setTitle("Delete item");
    	 	alertDialogBuilder.setMessage("Are you sure?");
    	 	alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
    			@SuppressWarnings("null")
				public void onClick(DialogInterface dialog,int id) {
 				//listdata.removeViewAt(position);
    				
    				if (memoId==null){
    					return;
    				}
    				else{
                		Toast.makeText(getApplicationContext(), "You selected item "+memoId, Toast.LENGTH_SHORT).show();
    					db.deleteMemo(memoId);
    					memoId=null;
    					delete(v);
    				
    					customAdapter = new CustomCursorAdapter(getApplicationContext(),c);	
						listData.setAdapter(customAdapter);
    					
    				}
    				
    				//c.requery()
    			   // memo = new MemoInformation();
    				//db.deleteMemoInformation(memo);
    				customAdapter.notifyDataSetChanged();  
    				
   				}

   		    });
        		alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,int id) {
        				dialog.cancel();
        			}
        		});
        	 
        		AlertDialog alertDialog = alertDialogBuilder.create();
        		//Show the dialog
        		alertDialog.show();
         		
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
		

		// 3. put object in the intent.
		intent.putExtra("memo", memo);

		// 4. Start the next activity.
		startActivity(intent);

	}
}
