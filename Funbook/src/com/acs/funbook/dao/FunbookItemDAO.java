package com.acs.funbook.dao;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.acs.funbook.model.FunbookItem;

public class FunbookItemDAO {
	private static final String TAG = FunbookItemDAO.class.getSimpleName();
	private static final String TABLE_NAME = "funbook";
	public static final String COLUMN_CATEGORY = "category";
	public static final String COLUMN_DATA = "data";

	public static ArrayList<FunbookItem> getEarnedBadges(final SQLiteDatabase db, final String category) {
		ArrayList<FunbookItem> earnedBadgesList = new ArrayList<FunbookItem>();
		String sql = "select * from " + TABLE_NAME + " where " + COLUMN_CATEGORY + "=?";
		Cursor cursor = db.rawQuery(sql, new String[] { category });
		if (cursor != null) {
			while (cursor.moveToNext()) {
				earnedBadgesList.add(setFunbookItem(cursor));
			}
		}
		return earnedBadgesList;
	}

	private static FunbookItem setFunbookItem(Cursor cursor) {
		String category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
		String data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA));
		FunbookItem badge = new FunbookItem();
		badge.setCategory(category);
		badge.setData(data);
		return badge;
	}
}
