package com.acs.funbook.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.acs.funbook.R;
import com.acs.funbook.config.Config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = MySQLiteOpenHelper.class.getSimpleName();
	private static final String DATABASE_NAME = Config.DATABASE_NAME;
	private static String DATABASE_PATH;
	private static final int DATABASE_VERSION = Config.DATABASE_VERSION;
	private final Context context;
	private SQLiteDatabase database = null;

	public MySQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		setDATABASE_PATH("/data/data/" + context.getApplicationContext().getPackageName() + "/databases/");
		final File dbfile = context.getDatabasePath(DATABASE_NAME);
		Log.d(TAG, "Get database dir: " + dbfile.getAbsolutePath());
		boolean databaseExists = false;
		try {
			databaseExists = checkDataBase();
		} catch (Exception e) {
			Log.d(TAG, "Exception: " + e);
		}

		if (databaseExists) {
			Log.d(TAG, "*Local Database Exists*");
		} else {
			Log.d(TAG, "*Setup Local Database*");
			try {
				copyDataBase();
			} catch (IOException e) {
				Log.e(TAG, "Exception: ", e);
				throw new Error("Error copying database");
			} catch (Exception e) {
				Log.e(TAG, "Exception: ", e);
			}
		}
		database = getWritableDatabase();
	}
	
	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {
		final File dbfile = context.getDatabasePath(DATABASE_NAME);
		try {
			SQLiteDatabase.openDatabase(dbfile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY).close();
			return true;
		} catch (SQLiteException e) {
			Log.e(TAG, "SQLiteException: ", e);
		}
		final String MY_PATH = getDATABASE_PATH() + DATABASE_NAME;
		try {
			SQLiteDatabase.openDatabase(MY_PATH, null, SQLiteDatabase.OPEN_READONLY).close();
			return true;
		} catch (SQLiteException e) {
			Log.e(TAG, "SQLiteException: ", e);
		}

		return false;
	}

	/**
	 * Copies your database FROM your local raw-folder to the just created empty
	 * database in the system folder, FROM where it can be accessed and handled.
	 * This is done by transfering bytestream.
	 * */

	private void copyDataBase() throws IOException {
		InputStream input = null;
		FileOutputStream output = null;
		SQLiteDatabase database = null;
		String databasePath = null;
		try {
			try {
				database = context.openOrCreateDatabase(DATABASE_NAME, 0, null);
				databasePath = database.getPath();
			} catch (Exception e) {
				Log.e(TAG, "Exception: ", e);
			}
			if (database != null) {
				database.close();
			}
		} catch (Exception e1) {
			Log.e(TAG, "Exception: ", e1);
		}
		int c;
		byte[] temp;
		try {
			output = new FileOutputStream(databasePath);
			input = context.getResources().openRawResource(R.raw.funbook);
			temp = new byte[1024];
			while ((c = input.read(temp)) != -1) {
				output.write(temp, 0, c);
			}
		} catch (Exception e) {
			Log.e(TAG, "Exception: ", e);
			copyDataBaseOldFlow();
		} finally {
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
				output.flush();
			}
		}
		Log.d(TAG, "Copy database done");
	}

	private void copyDataBaseOldFlow() throws IOException {
		InputStream input = null;
		FileOutputStream output = null;
		SQLiteDatabase database = null;
		try {
			database = context.openOrCreateDatabase(DATABASE_NAME, 0, null);
			if (database != null) {
				database.close();
			}
		} catch (Exception e1) {
			Log.e(TAG, "Exception: ", e1);
		}
		int c;
		byte[] temp;
		try {
			File databaseFile = new File(getDATABASE_PATH(), DATABASE_NAME);
			boolean created1 = databaseFile.mkdirs();
			boolean created2 = databaseFile.createNewFile();
			output = new FileOutputStream(getDATABASE_PATH() + DATABASE_NAME);
			input = context.getResources().openRawResource(R.raw.funbook);
			temp = new byte[1024];
			while ((c = input.read(temp)) != -1) {
				output.write(temp, 0, c);
			}
		} catch (Exception e) {
			Log.e(TAG, "Exception: ", e);
		} finally {
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
				output.flush();
			}
		}
	}

	public static String getDATABASE_PATH() {
		return DATABASE_PATH;
	}

	public static void setDATABASE_PATH(String dATABASE_PATH) {
		DATABASE_PATH = dATABASE_PATH;
	}
	
	@Override
	public synchronized void close() {
		if (database != null)
			database.close();
		super.close();
	}
	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
