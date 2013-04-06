package edu.gatech.cs2340.triggerhappycoders;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Uses SQLite to implement a database that contains a table of items and a
 * table of users
 * 
 * @author Trigger Happy Coders (23)
 * 
 */
public class DBController {

	private static final String DATABASE_NAME = "mydatabase.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_NAME = "newtable";
	public static final String TABLE_USER = "userTable";

	private static Context context;
	public static SQLiteDatabase db;

	private SQLiteStatement insertStmt;
	private SQLiteStatement insertUser;

	// create item table
	private static final String INSERT = "insert into " + TABLE_NAME
			+ " (name, description, location, category, reward, type, "
			+ "date, owner) values (?,?,?,?,?,?,?,?)";

	// creates user table
	private static final String USER = "insert into " + TABLE_USER + " (name,"
			+ "password, email, isLocked, isAdmin) values (?,?,?,?,?)";

	/**
	 * This method is the database
	 * 
	 * @param context
	 *            - Context the whereabouts of the controller
	 */
	public DBController(Context context) {
		DBController.context = context;
		OpenHelper openHelper = new OpenHelper(DBController.context);
		DBController.db = openHelper.getWritableDatabase();
		this.insertStmt = DBController.db.compileStatement(INSERT);
		this.insertUser = DBController.db.compileStatement(USER);
	}

	/**
	 * This method adds the item info
	 * 
	 * @param name
	 *            - String item's name
	 * @param description
	 *            - String item's description
	 * @param location
	 *            - String item's location
	 * @param category
	 *            - String item's category
	 * @param reward
	 *            - String item's reward
	 * @param type
	 *            - String item's type
	 * @param date
	 *            - String item's date
	 * @param owner
	 *            - String item's owner
	 */
	public long insert(String name, String description, String location,
			String category, String reward, String type, String date,
			String owner) {
		this.insertStmt.bindString(1, name);
		this.insertStmt.bindString(2, description);
		this.insertStmt.bindString(3, location);
		this.insertStmt.bindString(4, category);
		this.insertStmt.bindString(5, reward);
		this.insertStmt.bindString(6, type);
		this.insertStmt.bindString(7, date);
		this.insertStmt.bindString(8, owner);
		return this.insertStmt.executeInsert();
	}

	/**
	 * This method adds the user info
	 * 
	 * @param name
	 *            - String user's name
	 * @param email
	 *            - String user's email
	 * @param isLocked
	 *            - String flag
	 * @param isAdmin
	 *            - String special user
	 * @return insertUser
	 */
	public long addUser(String name, String password, String email,
			String isLocked, String isAdmin) {
		this.insertUser.bindString(1, name);
		this.insertUser.bindString(2, password);
		this.insertUser.bindString(3, email);
		this.insertUser.bindString(4, isLocked);
		this.insertUser.bindString(5, isAdmin);
		return this.insertUser.executeInsert();
	}

	/**
	 * This method deletes everything
	 */
	public void deleteAll() {
		db.delete(TABLE_NAME, null, null);
	}

	// returns a list of the items in the table

	/**
	 * This method deletes everything
	 * 
	 * @return list
	 */
	public List<String[]> selectAll() {

		List<String[]> list = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME, new String[] { "id", "name",
				"description", "location", "category", "reward", "type",
				"date", "owner" }, null, null, null, null, "name asc");

		int x = 0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1 = new String[] { cursor.getString(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4),
						cursor.getString(5), cursor.getString(6),
						cursor.getString(7), cursor.getString(8) };

				list.add(b1);

				x = x + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();

		return list;
	}

	/**
	 * This method returns a list of users from the table
	 * 
	 * @return userList
	 */
	public List<String[]> allUsers() {
		List<String[]> userList = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_USER, new String[] { "id", "name",
				"password", "email", "isLocked", "isAdmin" }, null, null, null,
				null, "name asc");
		int x = 0;
		if (cursor.moveToFirst()) {
			do {
				String[] b1 = new String[] { cursor.getString(0),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4),
						cursor.getString(5) };
				userList.add(b1);
				x = x + 1;
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		cursor.close();
		return userList;
	}

	/**
	 * This method removes a specified row in the table
	 */
	public void delete(int rowId) {
		db.delete(TABLE_NAME, null, null);
	}

	private static class OpenHelper extends SQLiteOpenHelper {

		/**
		 * This method updates the database
		 */
		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		/**
		 * This method creates the database table
		 * 
		 * @param db - SQLiteDatabase database
		 */
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "
					+ TABLE_NAME
					+ " (id INTEGER PRIMARY KEY, "
					+ "name TEXT, description TEXT, location TEXT, category TEXT, "
					+ "reward TEXT, type TEXT, date TEXT, owner TEXT)");
			db.execSQL("CREATE TABLE " + TABLE_USER
					+ " (id INTEGER PRIMARY KEY, "
					+ "name TEXT, password TEXT, email TEXT, isLocked TEXT,"
					+ "isAdmin TEXT)");
		}

		@Override
		/**
		 * This method upgrades the database table
		 * 
		 * @param db - SQLiteDatabase database
		 * @param oldVersion - int old version of the database
		 * @param newVersion - int new version of the database
		 */
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
			onCreate(db);
		}
	}
}