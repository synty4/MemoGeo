package DataBase;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler.Value;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "memoDatabase";
    
 // Memo table name
    private static final String TABLE_MEMO = "memo";
 
    // MemoDatabase Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_ADDRESS = "address";
 
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	
// creating table 	
	@Override
	public void onCreate(SQLiteDatabase db) {

		 String CREATE_MEMO_TABLE = "CREATE TABLE " + TABLE_MEMO + "("
	                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
	                + KEY_DESCRIPTION+ " TEXT,"+ KEY_ADDRESS+ " TEXT" + ")";
	        db.execSQL(CREATE_MEMO_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
		// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMO);
 
        // Create tables again
        onCreate(db);
		
	}


    // Adding new memo
public void addMemoInformation(MemoInformation MemoInformation) {
    SQLiteDatabase db = this.getWritableDatabase();
 
    ContentValues values = new ContentValues();
    values.put(KEY_TITLE, MemoInformation.getTitle()); // Contact Name
    values.put(KEY_DESCRIPTION, MemoInformation.getDescription()); // Contact Phone Number
    values.put(KEY_ADDRESS, MemoInformation.getAddress());
    // Inserting Row
    
    db.insert(TABLE_MEMO, null, values);
    db.close(); // Closing database connection
}

// Getting single contact
public MemoInformation getMemoInformation(int id) {
 SQLiteDatabase db = this.getReadableDatabase();
/*
	null,//group by  null,//having  null,//order by  null//limit
 * */
 Cursor cursor = db.query(TABLE_MEMO, new String[] { KEY_ID,
         KEY_TITLE, KEY_DESCRIPTION, KEY_ADDRESS }, KEY_ID + "=?",
         new String[] { String.valueOf(id) }, null, null, null, null);
 if (cursor != null)
     cursor.moveToFirst();
MemoInformation memoT;
memoT = new MemoInformation(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3));
 // return memo
Log.d("hello","hello");
Log.d("Hello",memoT.toString());
 return memoT;
}

// Getting All Memo

public List<MemoInformation> getAllMemoInformation() {
	
   List<MemoInformation> memoInformationList = new ArrayList<MemoInformation>();
   // Select All Query
   String selectQuery = "SELECT  * FROM " + TABLE_MEMO;

   SQLiteDatabase db = this.getWritableDatabase();
   Cursor cursor = db.rawQuery(selectQuery, null);

   // looping through all rows and adding to list
   MemoInformation memoInfo;
   if (cursor.moveToFirst()) {
	   memoInfo = new MemoInformation();
       memoInfo.setId(Integer.parseInt(cursor.getString(0)));
       memoInfo.setTitle(cursor.getString(1));
       memoInfo.setDescription(cursor.getString(2));
       memoInfo.setAddress(cursor.getString(3));
       // Adding MemoInfo to list
       memoInformationList.add(memoInfo);
       while (cursor.moveToNext()){
           memoInfo = new MemoInformation();
           memoInfo.setId(Integer.parseInt(cursor.getString(0)));
           memoInfo.setTitle(cursor.getString(1));
           memoInfo.setDescription(cursor.getString(2));
           memoInfo.setAddress(cursor.getString(3));
           // Adding MemoInfo to list
           memoInformationList.add(memoInfo);
       } 
   }
   return memoInformationList;
}


//Deleting single MemoInformation 
public void deleteMemoInformation(int delmemo) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_MEMO, KEY_ID + " = ?", new String[] {Integer.toString(delmemo)});
    db.close();
}
	

}
