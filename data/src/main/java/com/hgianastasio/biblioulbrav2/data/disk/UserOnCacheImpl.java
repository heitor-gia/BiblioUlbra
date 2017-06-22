package com.hgianastasio.biblioulbrav2.data.disk;

import android.content.Context;
import android.content.SharedPreferences;

import com.bluelinelabs.logansquare.LoganSquare;
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCache;
import com.hgianastasio.biblioulbrav2.data.disk.preferences.BasePreferences;
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/28/17.
 */
public class UserOnCacheImpl extends BasePreferences implements UserOnCache{
    private static String USER_DATA_PREFERENCE_KEY = "biblioulbra.pref.user_data";
    private static String USER_TIME_PREFERENCE_KEY = "biblioulbra.pref.user_time";

    @Inject
    public UserOnCacheImpl(Context mContext) {
        super(mContext);
    }

    public UserEntity get() throws IOException {
        String json = getPreferences().getString(USER_DATA_PREFERENCE_KEY,"");
        return LoganSquare.parse(json,UserEntity.class);
    }

    public boolean save(UserEntity user){
        SharedPreferences.Editor editor = getPreferences().edit();
        try {
            String userJson = LoganSquare.serialize(user);
            editor
                .putString(USER_DATA_PREFERENCE_KEY,userJson)
                .putLong(USER_TIME_PREFERENCE_KEY,System.currentTimeMillis())
                .apply();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean clearCache(){
        return getPreferences()
                .edit()
                .clear()
                .commit();

    }

    @Override
    public boolean isUpdated() {
        try {
            Date date = new Date(getPreferences().getLong(USER_TIME_PREFERENCE_KEY,0));
            return date.compareTo(new Date(System.currentTimeMillis())) > -3600000;
        }catch (Exception e){
            return false;
        }
    }
}
