package com.hgianastasio.biblioulbrav2.data.disk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.bluelinelabs.logansquare.LoganSquare;
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCache;
import com.hgianastasio.biblioulbrav2.data.disk.db.DBHelper;
import com.hgianastasio.biblioulbrav2.data.disk.preferences.BasePreferences;
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntity;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/29/17.
 */

public class HistoryBooksOnCacheImpl implements HistoryBooksOnCache {
    private static final String HISTORY_CACHE_TABLENAME = "historybooks";
    private static final String HISTORY_CACHE_IDFIELD = "_id";
    private static final String HISTORY_CACHE_JSONFIELD = "json";

    private DBHelper dbHelper;
    private HistoryBookPreferences preferences;

    @Inject
    public HistoryBooksOnCacheImpl(DBHelper dbHelper, HistoryBookPreferences preferences) {
        this.dbHelper = dbHelper;
        this.preferences = preferences;
    }



    @Override
    public List<HistoryBookEntity> get() throws IOException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {HISTORY_CACHE_IDFIELD,HISTORY_CACHE_JSONFIELD};
        Cursor cursor = db.query(HISTORY_CACHE_TABLENAME,columns,null,null,null,null,null);
        List<HistoryBookEntity> result = new LinkedList<>();
        if(cursor.moveToFirst()){
            do{
                result.add(fromCursor(cursor));
            }while (cursor.moveToNext());
        }
        return result;
    }

    @Override
    public boolean save(List<HistoryBookEntity> historyBookEntities) {
        clearCache();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            for(HistoryBookEntity entity : historyBookEntities)
                db.insertOrThrow(HISTORY_CACHE_TABLENAME,null,toValues(entity));
            preferences.setUpdateTime(System.currentTimeMillis());
            db.setTransactionSuccessful();
        }catch (IOException e){
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
                .execSQL(String.format("DELETE FROM %s",HISTORY_CACHE_TABLENAME));

            return preferences.clear();
        }catch (SQLException e){
            return false;
        }
    }

    @Override
    public boolean isUpdated() {
            long updateTime = preferences.getUpdateTime();
            long difference = updateTime*1000 - System.currentTimeMillis();
            return  difference > - 3600000;//1 Hour

    }

    private HistoryBookEntity fromCursor(Cursor cursor) throws IOException {
        int idFieldIndex = cursor.getColumnIndex(HISTORY_CACHE_IDFIELD);
        int jsonFieldIndex = cursor.getColumnIndex(HISTORY_CACHE_JSONFIELD);
        HistoryBookEntity entity =  LoganSquare.parse(cursor.getString(jsonFieldIndex),HistoryBookEntity.class);
        entity.setId(cursor.getLong(idFieldIndex));
        return entity;
    }

    private ContentValues toValues(HistoryBookEntity entity) throws IOException {
        ContentValues result = new ContentValues();
        result.put(HISTORY_CACHE_JSONFIELD,LoganSquare.serialize(entity));
        return result;
    }


    static class HistoryBookPreferences extends BasePreferences{
        private static final String HISTORY_BOOKS_UPDATE_TIME_KEY = "biblioulbra.pref.history_time";

        @Inject
        public HistoryBookPreferences(Context mContext) {
            super(mContext);
        }

        boolean setUpdateTime(long time){
            return getPreferences()
                    .edit()
                    .putLong(HISTORY_BOOKS_UPDATE_TIME_KEY,time)
                    .commit();
        }

        long getUpdateTime(){
            return getPreferences()
                    .getLong(HISTORY_BOOKS_UPDATE_TIME_KEY,-1);
        }

        boolean clear(){
            return getPreferences().edit().remove(HISTORY_BOOKS_UPDATE_TIME_KEY).commit();
        }
    }
}
