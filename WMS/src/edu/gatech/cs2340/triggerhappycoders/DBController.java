package edu.gatech.cs2340.triggerhappycoders;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;

public class DBController {
	
	private static final  String DATABASE_NAME = "mydatabase.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_NAME = "newtable";
	public static final String TABLE_USER = "userTable";
	
	private static Context context;
	public static SQLiteDatabase db;

	private SQLiteStatement insertStmt;
	private SQLiteStatement insertUser;
	
	//create item table
    private static final String INSERT = "insert into "
		+ TABLE_NAME + " (name, description, reward, type, date, category, owner) " +
				"values (?,?,?,?,?,?,?)";
    
    //creates user table
    private static final String USER = "insert into " + TABLE_USER + " (name," +
    		"password, email, isLocked, isAdmin) values (?,?,?,?,?)";

    //database
	public DBController(Context context) {
		DBController.context = context;
		OpenHelper openHelper = new OpenHelper(DBController.context);
		DBController.db = openHelper.getWritableDatabase();
		this.insertStmt = DBController.db.compileStatement(INSERT);
		this.insertUser = DBController.db.compileStatement(USER);
	}
	
	//adds the item info
	public long insert(String name, String description, String reward, 
			String type, String date, String category, String owner) {
		this.insertStmt.bindString(1, name);
		this.insertStmt.bindString(2, description);
		this.insertStmt.bindString(3, reward);
		this.insertStmt.bindString(4, type);
		this.insertStmt.bindString(5, date);
		this.insertStmt.bindString(6, category);
		this.insertStmt.bindString(7, owner);
		return this.insertStmt.executeInsert();
	}
	
	//adds the user info
	public long addUser(String name, String password, String email, String
			isLocked, String isAdmin){
		this.insertUser.bindString(1, name);
		this.insertUser.bindString(2, password);
		this.insertUser.bindString(3, email);
		this.insertUser.bindString(4, isLocked);
		this.insertUser.bindString(5, isAdmin);
		return this.insertUser.executeInsert();
	}

	//delete everything
	public void deleteAll() {
		db.delete(TABLE_NAME, null, null);
	}

	//returns a list of the items in the table
	public List<String[]> selectAll() {

		List<String[]> list = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME, new String[] { "id", "name", 
				"description", "reward", "type", "date", "category", "owner" },
				null, null, null, null, "name asc"); 

		int x=0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1=new String[]{cursor.getString(0), cursor.getString(1),
						cursor.getString(2), cursor.getString(3), cursor.getString(4),
						cursor.getString(5), cursor.getString(6), cursor.getString(7)};

				list.add(b1);

				x=x+1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		} 
		cursor.close();

		return list;
	}
	
	public List<String[]> allUsers(){
		List<String[]> userList = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_USER, new String[] { "id", "name",
				"password", "email", "isLocked", "isAdmin" }, null, null, null,
				null, "name asc");
		int x=0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1=new String[]{cursor.getString(0), cursor.getString(1),
						cursor.getString(2), cursor.getString(3), cursor.getString(4),
						cursor.getString(5)};
				userList.add(b1);
				x=x+1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		} 
		cursor.close();
		return userList;
	}

	//removes a specified row in the table
	public void delete(int rowId) {
		db.delete(TABLE_NAME, null, null); 
	}

	//updates the database
	private static class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY, " 
					+ "name TEXT, description TEXT, reward TEXT, type TEXT, " +
					"date TEXT, category TEXT, owner TEXT)");
			db.execSQL("CREATE TABLE " + TABLE_USER + " (id INTEGER PRIMARY KEY, " 
					+ "name TEXT, password TEXT, email TEXT, isLocked TEXT," +
					"isAdmin TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
			onCreate(db);
		}
	}
}