package com.hgianastasio.biblioulbrav2.data.disk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.bluelinelabs.logansquare.LoganSquare;
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCache;
import com.hgianastasio.biblioulbrav2.data.disk.db.DBHelper;
import com.hgianastasio.biblioulbrav2.data.disk.preferences.BasePreferences;
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntity;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/29/17.
 */

public class LoanBooksOnCacheImpl implements LoanBooksOnCache {
    private static final String HISTORY_CACHE_TABLENAME = "loanbooks";
    private static final String HISTORY_CACHE_IDFIELD = "_id";
    private static final String HISTORY_CACHE_JSONFIELD = "json";


    private DBHelper dbHelper;
    private LoanBookPreferences preferences;

    @Inject
    public LoanBooksOnCacheImpl(DBHelper dbHelper, LoanBookPreferences preferences) {
        this.dbHelper = dbHelper;
        this.preferences = preferences;
    }

    @Override
    public List<LoanBookEntity> get() throws IOException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {HISTORY_CACHE_IDFIELD, HISTORY_CACHE_JSONFIELD};
        Cursor cursor = db.query(HISTORY_CACHE_TABLENAME, columns, null, null, null, null, null);
        List<LoanBookEntity> result = new LinkedList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(fromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return result;
    }

    @Override
    public boolean save(List<LoanBookEntity> historyBookEntities) throws IOException {
        clearCache();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {

            for (LoanBookEntity entity : historyBookEntities)
                db.insertOrThrow(HISTORY_CACHE_TABLENAME, null, toValues(entity));
            preferences.setUpdateTime(System.currentTimeMillis());
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
        db.endTransaction();
        return true;
    }

    @Override
    public boolean clearCache() {
        try {
            dbHelper
                    .getWritableDatabase()
                    .execSQL(String.format("DELETE FROM %s", HISTORY_CACHE_TABLENAME));
            return preferences.clear();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean isUpdated() {
            long updateTime = preferences.getUpdateTime();
            long difference = updateTime*1000 - System.currentTimeMillis() ;
            return  difference > - 3600000;

    }

    private LoanBookEntity fromCursor(Cursor cursor) throws IOException {
        int idFieldIndex = cursor.getColumnIndex(HISTORY_CACHE_IDFIELD);
        int jsonFieldIndex = cursor.getColumnIndex(HISTORY_CACHE_JSONFIELD);
        LoanBookEntity entity = LoganSquare.parse(cursor.getString(jsonFieldIndex), LoanBookEntity.class);
        entity.setId(cursor.getLong(idFieldIndex));
        return entity;
    }

    private ContentValues toValues(LoanBookEntity entity) throws IOException {
        ContentValues result = new ContentValues();
        result.put(HISTORY_CACHE_JSONFIELD, LoganSquare.serialize(entity));
        return result;
    }


    static class LoanBookPreferences extends BasePreferences {
        private static final String LOAN_BOOKS_UPDATE_TIME_KEY = "biblioulbra.pref.loan_time";

        @Inject
        public LoanBookPreferences(Context mContext) {
            super(mContext);
        }

        boolean setUpdateTime(long time){
            return getPreferences()
                    .edit()
                    .putLong(LOAN_BOOKS_UPDATE_TIME_KEY,time)
                    .commit();
        }

        long getUpdateTime(){
            return getPreferences()
                    .getLong(LOAN_BOOKS_UPDATE_TIME_KEY,-1);
        }

        boolean clear(){
            return getPreferences().edit().remove(LOAN_BOOKS_UPDATE_TIME_KEY).commit();
        }
    }
}
