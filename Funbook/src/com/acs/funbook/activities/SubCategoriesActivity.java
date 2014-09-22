package com.acs.funbook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.acs.funbook.R;
import com.acs.funbook.adapters.SubCategoriesListAdapter;
import com.acs.funbook.config.Constants;
import com.acs.funbook.model.FunbookItem;

public class SubCategoriesActivity extends BaseActivity {
	// http://www.smsbuzz.in/
	private ListView livSubCategories;

	private String mMainCategory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_categories);
		init();
		findViews();
		setViews();
		setListeners();
	}


	private void init() {
		// TODO Auto-generated method stub
		mMainCategory = getIntent().getStringExtra(Constants.MAIN_CATEGORY_NAME);
	}

	private void findViews() {
		// TODO Auto-generated method stub
		livSubCategories = (ListView) findViewById(R.id.livSubCategories);
	}
	
	private void setViews() {
		// TODO Auto-generated method stub
		livSubCategories.setAdapter(new SubCategoriesListAdapter(getApplicationContext(), mMainCategory));
	}
	private void setListeners() {
		// TODO Auto-generated method stub
		livSubCategories.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				FunbookItem funbookItem = (FunbookItem) livSubCategories.getAdapter().getItem(position);
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, funbookItem.getData());
				sendIntent.setType("text/plain");
				startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share)));
			}
		});
	}
}
