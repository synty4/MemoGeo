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

    // creating a table 	
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

    //Opening a database
	
	//Closing a database
	
    // Adding new memo
public void addMemoInformation(MemoInformation Memo) {
    SQLiteDatabase db = this.getWritableDatabase();
 
    ContentValues values = new ContentValues();
    values.put(KEY_TITLE, Memo.getTitle()); // Memo title 
    values.put(KEY_DESCRIPTION, Memo.getDescription()); // description of the memo
    values.put(KEY_ADDRESS, Memo.getAddress());
    // Inserting Row
    
    db.insert(TABLE_MEMO, null, values);
    db.close(); // Closing database connection
}


public Cursor getAllData () {

	SQLiteDatabase db = this.getWritableDatabase();

	Cursor cursor;

    String selectQuery = "SELECT * FROM " + TABLE_MEMO;

    cursor = db.rawQuery(selectQuery, null);

    return  cursor;

}



public Cursor getAllTable1() {

	SQLiteDatabase db = this.getWritableDatabase();

	Cursor cur =  db.rawQuery( "select "+KEY_ID+" _id , "+KEY_TITLE+" from "+TABLE_MEMO, null);

	return cur;

}

public Cursor getById(String id){
	String[] args={id};
	SQLiteDatabase db = this.getWritableDatabase();

	Cursor cur =  db.rawQuery( "select "+KEY_ID+" _id from "+TABLE_MEMO+"WHERE_id=?", args);

	return cur;
	
}
	
	public String getTitle(Cursor c){
		return (c.getString(1));
	}



/**The following method will read single row.  
 * 
 * @param id
 * @return the matched row from the database
 */
//I changed the parameter to be a string....

public MemoInformation getMemoInformation(String title) {
	 SQLiteDatabase db = this.getReadableDatabase();

		//null,//group by  null,//having  null,//order by  null//limit
	 
	 Cursor cursor = db.query(TABLE_MEMO, new String[] { KEY_ID,
	         KEY_TITLE, KEY_DESCRIPTION, KEY_ADDRESS }, KEY_TITLE + "=?",
	         new String[] { String.valueOf(title) }, null, null, null, null);
	 if (cursor != null)
	     cursor.moveToFirst();
	MemoInformation memoT;
	memoT = new MemoInformation(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2), cursor.getString(3));
	 // return memo
	Log.d("hello","hello");
	Log.d("Hello",memoT.toString());
	 return memoT;
	}

/**public MemoInformation getMemoInfo(int id) {
 SQLiteDatabase db = this.getReadableDatabase();

	//null,//group by  null,//having  null,//order by  null//limit
 
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
**/


/***
 * getAllMemoInfo 
 * Uses a for loop to go through each memo
 * @return all mémos from the database in an arrayList format
 */

public List<MemoInformation> getAllMemoInformation() {
	
   List<MemoInformation> memoList = new ArrayList<MemoInformation>();
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
       memoList.add(memoInfo);
       while (cursor.moveToNext()){
           memoInfo = new MemoInformation();
           memoInfo.setId(Integer.parseInt(cursor.getString(0)));
           memoInfo.setTitle(cursor.getString(1));
           memoInfo.setDescription(cursor.getString(2));
           memoInfo.setAddress(cursor.getString(3));
           // Adding MemoInfo to list
           memoList.add(memoInfo);
       } 
   }
   return memoList;
}

/***
 * updateContact() will update a single memo in the database
 * @param memo is a MemoInformation class object
 * @return
 */

public int updateMemoInformation(MemoInformation memo) {
 SQLiteDatabase db = this.getWritableDatabase();

 //updating single memo
 ContentValues values = new ContentValues();
 values.put(KEY_TITLE, memo.getTitle());
 values.put(KEY_DESCRIPTION, memo.getDescription());
 values.put(KEY_ADDRESS, memo.getAddress());

 // updating row
 return db.update(TABLE_MEMO, values, KEY_ID + " = ?",
         new String[] { String.valueOf(memo.getId()) });
}



/***
 * deleteMemo() will delete a single memo from database
 * @param memo = MemoInformation class object 
 */

/**

// Deleting single memo
public void deleteMemoInformation(MemoInformation memo) {
   SQLiteDatabase db = this.getWritableDatabase();
   db.delete(TABLE_MEMO, KEY_ID + " = ?",
           new String[] { String.valueOf(memo.getId()) });
   db.close();
}


//Deleting single MemoInformation 
public void deleteMemo(String id) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_MEMO, KEY_ID + " = ?", new String[] {id});
    db.close();
}**/

public void deleteMemo(String id) {
	
	//boolean result = false;
	
	String query = "Select * FROM " + TABLE_MEMO + " WHERE " + KEY_ID + " =  \"" + id + "\"";

	SQLiteDatabase db = this.getWritableDatabase();
	
	Cursor cursor = db.rawQuery(query, null);
	MemoInformation memo = new MemoInformation();
	
	if (cursor.moveToFirst()) {
		memo.setId(Integer.parseInt(cursor.getString(0)));
		db.delete(TABLE_MEMO, KEY_ID + " = ?",
	    new String[] { String.valueOf(memo.getId()) });
		cursor.close();
		//result = true;
	}
        db.close();
	//return result;
}



//Getting The number of mémo in the db
public int getMemoInformationCount() {
   String countQuery = "SELECT  * FROM " + TABLE_MEMO;
   SQLiteDatabase db = this.getReadableDatabase();
   Cursor cursor = db.rawQuery(countQuery, null);
   cursor.close();

   // return count
   return cursor.getCount();
}
	

}
