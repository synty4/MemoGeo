package be.ac.ucl.lfsab1509.memogeo;
 
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
 

	@SuppressWarnings("deprecation")
	public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }
 
   
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // when the view will be created for first time,
        // we need to tell the adapters, how each item will look
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.single_row_item, parent, false);
 
        return retView;
    }
 
   
    public void bindView(View view, Context context, Cursor cursor) {
        // here we are setting our data
        // that means, take the data from the cursor and put it in views
    	
    	TextView numero = (TextView) view.findViewById(R.id.title_container1);
        numero.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0))));
        numero.setBackgroundColor(Color.argb(255, 0, 0, 0));
         
        TextView textViewPersonName = (TextView) view.findViewById(R.id.title_container);
        textViewPersonName.setText(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
        numero.setBackgroundColor(color.background_light);
    }
}