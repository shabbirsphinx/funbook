package com.acs.funbook.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.acs.funbook.R;
import com.acs.funbook.application.MyApplication;
import com.acs.funbook.dao.FunbookDAO;
import com.acs.funbook.database.MySQLiteOpenHelper;
import com.acs.funbook.model.FunbookItem;

public class SubCategoriesListAdapter extends BaseAdapter {

	private ArrayList<FunbookItem> mSubCategoriesList;
	private LayoutInflater mLayoutInflater;

	public SubCategoriesListAdapter(final Context pContext, final String pMainCategory) {
		// TODO Auto-generated constructor stub
		this.mSubCategoriesList = FunbookDAO.getFunbookListForCategory(MySQLiteOpenHelper.getDb(), pMainCategory);
		this.mLayoutInflater = (LayoutInflater) pContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mSubCategoriesList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mSubCategoriesList.get(position);
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
					R.layout.sub_categories_item, null);
			viewHolder = new ViewHolder();
			viewHolder.txtName = (TextView)convertView.findViewById(R.id.txtName);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.txtName.setText(mSubCategoriesList.get(position).getData());
		return convertView;
	}

	static class ViewHolder {
		TextView txtName;
	}
}
