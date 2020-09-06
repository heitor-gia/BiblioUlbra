package com.hgianastasio.biblioulbrav2.data.disk.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.hgianastasio.biblioulbrav2.data.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/26/17.
 */
class DBHelper @Inject constructor(private val mContext: Context) : SQLiteOpenHelper(mContext, DB_NAME, null, DB_VERSION) {
    private fun fromRawResource(resourceId: Int): String {
        val inputStream = mContext.resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var result = ""
        try {
            do {
                result += reader.readLine()
            } while (reader.ready())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(fromRawResource(R.raw.sql_createtable_historybooks))
        db.execSQL(fromRawResource(R.raw.sql_createtable_loanbooks))
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    companion object {
        private const val DB_NAME = "BiblioULBRADB"
        private const val DB_VERSION = 1
    }

}