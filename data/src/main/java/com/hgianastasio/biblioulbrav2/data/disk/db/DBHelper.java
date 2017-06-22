package com.hgianastasio.biblioulbrav2.data.disk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgianastasio.biblioulbrav2.data.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/26/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String DB_NAME = "BiblioULBRADB";
    private static final int DB_VERSION = 1;

    @Inject
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    private String fromRawResource(int resourceId){
        InputStream inputStream = mContext.getResources().openRawResource(resourceId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String result = "";
        try {
            do{
                result = result.concat(reader.readLine());
            }while (reader.ready());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(fromRawResource(R.raw.sql_createtable_historybooks));
        db.execSQL(fromRawResource(R.raw.sql_createtable_loanbooks));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
