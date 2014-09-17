package com.acs.funbook.activities;

import android.content.Intent;
import android.os.Bundle;

import com.acs.funbook.R;

public class SplashActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		startActivity(new Intent(this, MainCategoriesActivity.class));
	}
}
