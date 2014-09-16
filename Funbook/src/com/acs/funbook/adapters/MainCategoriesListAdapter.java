package com.acs.funbook.adapters;

import com.acs.funbook.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainCategoriesListAdapter extends BaseAdapter {

	private String[] mMainCategoriesList;
	private LayoutInflater mLayoutInflater;

	public MainCategoriesListAdapter(Context pContext) {
		// TODO Auto-generated constructor stub
		this.mMainCategoriesList = pContext.getResources().getStringArray(
				R.array.main_categories);
		this.mLayoutInflater = (LayoutInflater) pContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mMainCategoriesList.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mMainCategoriesList[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null || convertView.getTag() == null) {
			convertView = this.mLayoutInflater.inflate(
					R.layout.main_categories_item, null);
			viewHolder = new ViewHolder();
			viewHolder.txtName = (TextView)convertView.findViewById(R.id.txtName);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.txtName.setText(mMainCategoriesList[position]);
		return convertView;
	}

	static class ViewHolder {
		TextView txtName;
	}
}
