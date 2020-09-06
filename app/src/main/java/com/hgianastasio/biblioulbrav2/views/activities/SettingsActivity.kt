package com.hgianastasio.biblioulbrav2.views.activities

import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity

/**
 * Created by heitor_12 on 06/06/17.
 */
class SettingsActivity : BaseActivity() {

    override fun postBind() {
        super.postBind()
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
}