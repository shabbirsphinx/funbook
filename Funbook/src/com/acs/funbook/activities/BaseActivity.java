package com.acs.funbook.activities;

import android.app.Activity;
import android.os.Bundle;

import com.acs.funbook.database.MySQLiteOpenHelper;

public abstract class BaseActivity extends Activity {

	protected MySQLiteOpenHelper mySQLiteOpenHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mySQLiteOpenHelper = new MySQLiteOpenHelper(getApplicationContext());
	}
}
