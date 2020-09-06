package com.hgianastasio.biblioulbrav2.data.disk.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by heitorgianastasio on 4/28/17.
 */
abstract class BasePreferences(protected val context: Context) {
    protected val preferences: SharedPreferences

    companion object {
        const val BIBLIOULBRA_PREFERENCE_KEY = "biblioulbra.preferences"
    }

    init {
        preferences = context.getSharedPreferences(BIBLIOULBRA_PREFERENCE_KEY, Context.MODE_PRIVATE)
    }
}