package com.hgianastasio.biblioulbrav2.navigation

import android.content.Context
import android.content.Intent
import com.hgianastasio.biblioulbrav2.views.activities.HomeActivity
import com.hgianastasio.biblioulbrav2.views.activities.LoginActivity

/**
 * Created by heitor_12 on 09/05/17.
 */
object Navigator {
    fun toHomeActivity(context: Context) {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }

    fun toLoginActivity(context: Context) {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }
}