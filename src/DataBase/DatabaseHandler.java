package DataBase;

import java.util.ArrayList;
import java.util.List;

import be.ac.ucl.lfsab1509.memogeo.Memo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler.Value;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "memoDatabase";

	// Memo table name
	private static final String TABLE_MEMO = "memo";

	// MemoDatabase Table Columns names
	private static final String KEY_ID = "_id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_ADDRESS = "address";
	private static final String KEY_LATITUDE = "latitude";
	private static final String KEY_LONGITUDE = "longitude";
	private static final String KEY_TIME = "time";
	private static final String KEY_DATE = "date";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	// creating a table
	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_MEMO_TABLE = "CREATE TABLE " + TABLE_MEMO + "( " + KEY_ID
				+ " INTEGER PRIMARY KEY, " + KEY_TITLE + " TEXT UNIQUE, "
				+ KEY_DESCRIPTION + " TEXT, " + KEY_ADDRESS + " TEXT, "
				+ KEY_LATITUDE + " FLOAT, " + KEY_LONGITUDE + " FLOAT, "
				+ KEY_TIME + " TEXT, " + KEY_DATE + " TEXT " + ");";

		db.execSQL(CREATE_MEMO_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMO);

		// Create tables again
		onCreate(db);

	}

	// Opening a database

	// Closing a database

	// Adding new memo
	public void addMemoInformation(Memo memo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, memo.getTitle()); // Memo title
		values.put(KEY_DESCRIPTION, memo.getMemo()); // description of the memo
		values.put(KEY_ADDRESS, memo.getAddress());
		values.put(KEY_LATITUDE, memo.getLatitude());
		values.put(KEY_LONGITUDE, memo.getLongitude());
		values.put(KEY_TIME, memo.getTime());
		values.put(KEY_DATE, memo.getDate());

		// Inserting Row

		db.insert(TABLE_MEMO, null, values);
		db.close(); // Closing database connection
	}
	
	

	public Cursor getAllData() {

		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor;

		String selectQuery = "SELECT * FROM " + TABLE_MEMO;

		cursor = db.rawQuery(selectQuery, null);

		return cursor;

	}

	public Cursor getAllTable() {

		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cur = db.rawQuery("SELECT " + KEY_ID + " , " + KEY_TITLE+ " , " + KEY_DESCRIPTION
				+ " FROM " + TABLE_MEMO, null);

		return cur;

	}

	/**
	 * The following method will read single row.
	 * 
	 * @param id
	 * @return the matched row from the database
	 */
	// I changed the parameter to be a string....

	public Memo getMemoInformation(String title) {

		SQLiteDatabase db = this.getReadableDatabase();

		String[] param = new String[1];
		param[0] = title;

		Cursor cursor = db.rawQuery("SELECT " + KEY_ID + " , "+ KEY_TITLE + " , "
				+ KEY_DESCRIPTION + " , " + KEY_ADDRESS + " , " + KEY_LATITUDE
				+ " , " + KEY_LONGITUDE + " , " + KEY_TIME + " , " + KEY_DATE
				+ " FROM " + TABLE_MEMO + " WHERE " + KEY_TITLE + " = ? ",
				param);
		
		Memo memo = new Memo();

		if (cursor.moveToFirst()) {
			memo = new Memo(
					cursor.getString(cursor.getColumnIndex(KEY_TITLE)),
					cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)),
					cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)),
					cursor.getDouble(cursor.getColumnIndex(KEY_LATITUDE)),
					cursor.getDouble(cursor.getColumnIndex(KEY_LONGITUDE)),
					cursor.getString(cursor.getColumnIndex(KEY_TIME)),
					cursor.getString(cursor.getColumnIndex(KEY_DATE))
					);
		}

		return memo;
	}

	/*
	 * getAllMemoInfo Uses a for loop to go through each memo
	 * 
	 * @return all m�mos from the database in an arrayList format
	 */

	public ArrayList<Memo> getAllMemoInformation() {

		SQLiteDatabase db = this.getReadableDatabase();
		// Select All Query
		//String selectQuery = "SELECT " + KEY_TITLE + " , "
			//	+ KEY_DESCRIPTION + " , " + KEY_ADDRESS + " , " + KEY_LATITUDE
				//+ " , " + KEY_LONGITUDE + " , " + KEY_TIME + " , " + KEY_DATE
				//+ " FROM " + TABLE_MEMO;

		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MEMO, null);
		
		ArrayList<Memo> memoList = new ArrayList<Memo>();
		
		
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				memoList.add(new Memo(
						cursor.getString(cursor.getColumnIndex(KEY_TITLE)),
						cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)),
						cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)),
						cursor.getDouble(cursor.getColumnIndex(KEY_LATITUDE)),
						cursor.getDouble(cursor.getColumnIndex(KEY_LONGITUDE)),
						cursor.getString(cursor.getColumnIndex(KEY_TIME)),
						cursor.getString(cursor.getColumnIndex(KEY_DATE))
						));
				// Adding MemoInfoLooped to list
			} while (cursor.moveToNext());
		}
		db.close();
		return memoList;
	}

	/***
	 * updateContact() will update a single memo in the database
	 * 
	 * @param memo
	 *            is a MemoInformation class object
	 * @return
	 */

	public int updateMemoInformation(Memo memo, String title)
			throws NullPointerException {
		if (title == null) {
			throw new NullPointerException();
		} else {
			SQLiteDatabase db = this.getWritableDatabase();

			// updating single memo
			ContentValues values = new ContentValues();
			values.put(KEY_TITLE, memo.getTitle());
			values.put(KEY_DESCRIPTION, memo.getMemo());
			values.put(KEY_ADDRESS, memo.getAddress());
			values.put(KEY_LATITUDE, memo.getLatitude());
			values.put(KEY_LONGITUDE, memo.getLongitude());
			values.put(KEY_TIME, memo.getTime());
			values.put(KEY_DATE, memo.getDate());

			// updating row
			return db.update(TABLE_MEMO, values, KEY_TITLE + " = ? ",
					new String[] { title });
		}
	}
	
	public String getTitle(Cursor c) {
		return (c.getString(1));
	}
	public String getDescription(Cursor c) {
		return (c.getString(2));
	}
	/***
	 * deleteMemo() will delete a single memo from database
	 * 
	 * @param memo
	 *            = MemoInformation class object
	 */
	// Deleting single memo
	public void deleteMemoInformation(String title) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MEMO, KEY_TITLE + " = ? ", new String[] { title });
		db.close();
	}

	/***
	 * //Deleting single MemoInformation public void deleteMemo(int delmemo) {
	 * SQLiteDatabase db = this.getWritableDatabase(); db.delete(TABLE_MEMO,
	 * KEY_ID + " = ?", new String[] {Integer.toString(delmemo)}); db.close(); }
	 **/

	// Getting The number of m�mo in the db
	public int getMemoInformationCount() {
		String countQuery = "SELECT  * FROM " + TABLE_MEMO;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

	public Cursor getById(String id) {
		String[] args = { id };
		// Add a comment to this line
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cur = db.rawQuery("select " + KEY_ID + " _id from " + TABLE_MEMO
				+ "WHERE_id=?", args);

		return cur;

	}

	

	public void deleteMemo(String id) {

		// boolean result = false;

		String query = "Select * FROM " + TABLE_MEMO + " WHERE " + KEY_ID
				+ " =  \"" + id + "\"";

		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(query, null);
		Memo memo = new Memo();

		if (cursor.moveToFirst()) {
			memo.setId(Integer.parseInt(cursor.getString(0)));
			db.delete(TABLE_MEMO, KEY_ID + " = ?",
					new String[] { String.valueOf(memo.getId()) });
			cursor.close();
			// result = true;
		}
		db.close();
		// return result;
	}

	public void deleteAll() {

		String query = "Select * FROM " + TABLE_MEMO;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		if (cursor != null) {
			db.delete(TABLE_MEMO, null, new String[] {});
			cursor.close();
		} else {
			return;
		}
		db.close();
	}

}
