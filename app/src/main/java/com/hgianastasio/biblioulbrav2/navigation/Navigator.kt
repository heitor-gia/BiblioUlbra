package com.hgianastasio.biblioulbrav2.navigation

import android.content.Context
import android.content.Intent
import com.hgianastasio.biblioulbrav2.views.activities.LoginActivity
import com.hgianastasio.biblioulbrav2.views.activities.MainActivity

/**
 * Created by heitor_12 on 09/05/17.
 */
object Navigator {
    fun toMainActivity(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    fun toLoginActivity(context: Context) {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }
}