package be.ac.ucl.lfsab1509.memogeo;
 
import DataBase.DatabaseHandler;
import android.R.color;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
 
public class CustomCursorAdapter extends CursorAdapter {
 

	private static final Context ListMemo = null;
	DatabaseHandler db;
	

	@SuppressWarnings("deprecation")
	public CustomCursorAdapter(Context context, Cursor c) {
     
		super (context, c);
		db = new DatabaseHandler(context);
		
    }
 
   
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // when the view will be created for first time,
        // we need to tell the adapters, how each item will look
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.single_row_item, parent, false);
        MemoHolder holder = new MemoHolder(row,cursor);
        row.setTag(holder);
        return (row);
    }
 
   
    public void bindView(View view, Context context, Cursor cursor) {
    	
    	  MemoHolder holder =  (MemoHolder)view.getTag();
    holder.populateFrom(cursor, db);
    }
       
    
}


class MemoHolder{
	
    // here we are setting our data
    // that means, take the data from the cursor and put it in views
	private TextView numero;
	private TextView textViewPersonName;
	
	
	MemoHolder(View row, Cursor c){
		numero = (TextView) row.findViewById(R.id.title_container1);
	   
	    numero.setBackgroundColor(Color.argb(255, 0, 0, 0));
	    
	    textViewPersonName = (TextView) row.findViewById(R.id.title_container);
	  
	    numero.setBackgroundColor(color.background_light);
		
		
	}
	
void populateFrom(Cursor c, DatabaseHandler db){
	
	 numero.setText(c.getString(c.getColumnIndex(c.getColumnName(0))));
	  textViewPersonName.setText(c.getString(c.getColumnIndex(c.getColumnName(1))));
}
	
}