package com.acs.funbook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.acs.funbook.R;
import com.acs.funbook.adapters.MainCategoriesListAdapter;
import com.acs.funbook.config.Constants;

public class MainCategoriesActivity extends BaseActivity {
	// http://www.smsbuzz.in/
	private ListView livMainCategories;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_categories);
		findViews();
		setViews();
		setListeners();
	}

	private void findViews() {
		// TODO Auto-generated method stub
		livMainCategories = (ListView) findViewById(R.id.livMainCategories);
	}

	private void setViews() {
		// TODO Auto-generated method stub
		livMainCategories.setAdapter(new MainCategoriesListAdapter(getApplicationContext()));
	}

	private void setListeners() {
		// TODO Auto-generated method stub
		livMainCategories.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String mainCategory = (String) livMainCategories.getAdapter().getItem(position);
				Intent intent = new Intent(MainCategoriesActivity.this,SubCategoriesActivity.class);
				intent.putExtra(Constants.MAIN_CATEGORY_NAME, mainCategory);
				startActivity(intent);
			}
		});
	}
}
