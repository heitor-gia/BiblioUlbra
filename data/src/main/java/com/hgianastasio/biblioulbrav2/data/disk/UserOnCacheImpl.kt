package com.hgianastasio.biblioulbrav2.data.disk

import android.content.Context
import com.google.gson.Gson
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCache
import com.hgianastasio.biblioulbrav2.data.disk.preferences.BasePreferences
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity
import java.io.IOException
import java.util.*
import javax.inject.Inject

/**
 * Created by heitorgianastasio on 4/28/17.
 */
class UserOnCacheImpl @Inject constructor(mContext: Context) : BasePreferences(mContext), UserOnCache {
    @Throws(IOException::class)
    override fun get(): UserEntity {
        val json = preferences.getString(USER_DATA_PREFERENCE_KEY, "")
        return Gson().fromJson(json, UserEntity::class.java)
    }

    override fun save(user: UserEntity): Boolean {
        val editor = preferences.edit()
        try {
            val userJson = Gson().toJson(user)
            editor
                    .putString(USER_DATA_PREFERENCE_KEY, userJson)
                    .putLong(USER_TIME_PREFERENCE_KEY, System.currentTimeMillis())
                    .apply()
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun clearCache(): Boolean {
        return preferences
                .edit()
                .clear()
                .commit()
    }

    override val isUpdated: Boolean
        get() = try {
            val date = Date(preferences.getLong(USER_TIME_PREFERENCE_KEY, 0))
            date.compareTo(Date(System.currentTimeMillis())) > -3600000
        } catch (e: Exception) {
            false
        }

    companion object {
        private const val USER_DATA_PREFERENCE_KEY = "biblioulbra.pref.user_data"
        private const val USER_TIME_PREFERENCE_KEY = "biblioulbra.pref.user_time"
    }
}