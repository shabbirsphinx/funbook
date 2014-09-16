package com.acs.funbook.activities;

import com.acs.funbook.R;
import com.acs.funbook.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		startActivity(new Intent(this, MainCategoriesActivity.class));
	}
}
