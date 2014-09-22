package com.acs.funbook.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.acs.funbook.database.MySQLiteOpenHelper;

public class MyApplication extends Application {

	public static MySQLiteOpenHelper mySQLiteOpenHelper;
	
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
	}
}
