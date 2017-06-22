package com.hgianastasio.biblioulbrav2.data.disk.preferences;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by heitorgianastasio on 4/28/17.
 */

public abstract class BasePreferences {
    private Context mContext;
    private SharedPreferences preferences;
    public static final String BIBLIOULBRA_PREFERENCE_KEY = "biblioulbra.preferences";

    public BasePreferences(Context mContext) {
        this.mContext = mContext;
        preferences = mContext.getSharedPreferences(BIBLIOULBRA_PREFERENCE_KEY,Context.MODE_PRIVATE);
    }

    protected Context getContext() {
        return mContext;
    }

    protected SharedPreferences getPreferences() {
        return preferences;
    }
}
