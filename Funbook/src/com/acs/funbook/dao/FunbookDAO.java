package com.acs.funbook.dao;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.acs.funbook.model.FunbookItem;

public class FunbookDAO {
	private static final String TAG = FunbookDAO.class.getSimpleName();
	private static final String TABLE_NAME = "funbook";
	public static final String COLUMN_CATEGORY = "col_1";
	public static final String COLUMN_DATA = "col_2";

	public static ArrayList<FunbookItem> getFunbookListForCategory(final SQLiteDatabase db, final String category) {
		ArrayList<FunbookItem> funbookList = new ArrayList<FunbookItem>();
		String sql = "select * from " + TABLE_NAME + " where " + COLUMN_CATEGORY + "=?";
		Cursor cursor = db.rawQuery(sql, new String[] { category });
		if (cursor != null) {
			while (cursor.moveToNext()) {
				funbookList.add(setFunbookItem(cursor));
			}
		}
		return funbookList;
	}

	private static FunbookItem setFunbookItem(Cursor cursor) {
		String category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
		String data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA));
		FunbookItem badge = new FunbookItem();
		badge.setCategory(category);
		badge.setData(data);
		return badge;
	}
	
	public static ArrayList<String> getAllCategories(final SQLiteDatabase db) {
		ArrayList<String> allCategories = new ArrayList<String>();
		String sql = "select col_1 from " + TABLE_NAME;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				allCategories.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA)));
			}
		}
		return allCategories;
	}
}
