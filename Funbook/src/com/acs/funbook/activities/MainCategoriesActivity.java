package com.acs.funbook.activities;

import com.acs.funbook.R;
import com.acs.funbook.adapters.MainCategoriesListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainCategoriesActivity extends Activity {
	// http://www.smsbuzz.in/
	private ListView livMainCategories;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_categories);
		findViews();
		setViews();
	}

	private void findViews() {
		// TODO Auto-generated method stub
		livMainCategories = (ListView) findViewById(R.id.livMainCategories);
	}
	
	private void setViews() {
		// TODO Auto-generated method stub
		livMainCategories.setAdapter(new MainCategoriesListAdapter(getApplicationContext()));
	}
}
