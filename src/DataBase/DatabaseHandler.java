package DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	//All Static variables
	//Database Version regarfer a koi ca sert
	private static final int DATABASE_VERSION = 1;
	//Database Name
	private static final String DATABASE_NAME = "memoDatabase";
	// Memo table name
	private static final String TABLE_MEMO = "memo";
	// Memo Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_ADDRESS = "address";


	public DatabaseHandler(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
	}
	
	public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE_MEMO = "CREATE TABLE "+TABLE_MEMO+" ( "+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
				+KEY_TITLE+" TEXT,"
				+KEY_DESCRIPTION+" TEXT,"
				+KEY_ADDRESS+" TEXT "
				+")";
		db.execSQL(CREATE_TABLE_MEMO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_MEMO);
		onCreate(db);
	}
	
	public void addMemo(MemoInformation memoInformation) {
	    SQLiteDatabase db = this.getWritableDatabase();
	 
	    ContentValues values = new ContentValues();
	    values.put(KEY_TITLE, memoInformation.getTitle()); 
	    values.put(KEY_DESCRIPTION, memoInformation.getDescription()); 
	    values.put(KEY_ADDRESS,memoInformation.getAddress());
	    
	    db.insert(TABLE_MEMO, null, values);
	    db.close(); // Closing database connection
	}
	//  get one memo 
	public MemoInformation getMemoInformation(int id) {
	    SQLiteDatabase db = this.getReadableDatabase();
	 
	    Cursor cursor = db.query(TABLE_MEMO, new String[] { KEY_ID,
	            KEY_TITLE, KEY_DESCRIPTION, KEY_ADDRESS }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    MemoInformation memoInformation  = new MemoInformation(Integer.parseInt(cursor.getString(0)),
	    		cursor.getString(1),cursor.getString(2),cursor.getString(3));
	    // return contact
	    return memoInformation;
	}
	
	
	

}
