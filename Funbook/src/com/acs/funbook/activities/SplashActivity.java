package com.acs.funbook.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.acs.funbook.R;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Intent intent = new Intent(this, MainCategoriesActivity.class);
		startActivity(intent);
	}
}
