package com.hgianastasio.biblioulbrav2.views.activities.base

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.di.DaggerApplicationInjector
import com.hgianastasio.biblioulbrav2.di.components.ActivityComponent
import com.hgianastasio.biblioulbrav2.di.components.DaggerActivityComponent
import com.hgianastasio.biblioulbrav2.di.modules.ActivityModule

/**
 * Created by heitorgianastasio on 11/29/16.
 */
abstract class BaseActivity : AppCompatActivity() {
    @kotlin.jvm.JvmField
    @BindView(R.id.toolbar)
    var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preBind()
        ButterKnife.bind(this)
        postBind()
    }

    @CallSuper
    protected open fun preBind() {
    }

    @CallSuper
    protected open fun postBind() {
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.default_menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miAbout) createAboutDialog().show()
        return super.onOptionsItemSelected(item)
    }

    protected val permissions: Array<String>
        protected get() = arrayOf()

    private fun createAboutDialog(): AlertDialog {
        val message = LayoutInflater.from(this).inflate(R.layout.dialog_about_layout, null) as TextView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) message.text = Html.fromHtml(getString(R.string.about_message, getString(R.string.app_version)), 0) else message.text = Html.fromHtml(getString(R.string.about_message, getString(R.string.app_version)))
        message.movementMethod = LinkMovementMethod.getInstance()
        return AlertDialog.Builder(this)
                .setTitle("Sobre")
                .setView(message)
                .setNeutralButton("OK", null)
                .create()
    }

    val activityComponent: ActivityComponent
        get() = DaggerActivityComponent
                .builder()
                .activityModule(ActivityModule(this))
                .applicationComponent(DaggerApplicationInjector.get())
                .build()
}