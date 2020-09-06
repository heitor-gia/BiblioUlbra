package com.hgianastasio.biblioulbrav2.views.activities.base

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import butterknife.BindView
import com.google.android.material.navigation.NavigationView
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.models.user.UserModel
import com.hgianastasio.biblioulbrav2.views.fragments.BaseFragment

/**
 * Created by heitor_12 on 09/05/17.
 */
abstract class BaseDrawerActivity : BaseFragmentActivity(), NavigationView.OnNavigationItemSelectedListener {
    @kotlin.jvm.JvmField
    @BindView(R.id.nav_view)
    var navigationDrawer: NavigationView? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.drawer_layout)
    var drawer: DrawerLayout? = null
    override fun preBind() {
        super.preBind()
        setContentView(R.layout.main_drawer_layout)
    }

    override fun postBind() {
        super.postBind()
        navigationDrawer!!.setNavigationItemSelectedListener(this)
        createDrawerToggle()
    }

    protected fun createDrawerToggle() {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)
        drawer!!.addDrawerListener(toggle)
        toggle.syncState()
    }

    fun configureDrawer(model: UserModel) {
        (navigationDrawer!!.getHeaderView(0).findViewById<View>(R.id.tvName) as TextView).text = model.name
        (navigationDrawer!!.getHeaderView(0).findViewById<View>(R.id.tvCGU) as TextView).text = model.cgu
    }

    override fun callFragment(fragment: BaseFragment) {
        super.callFragment(fragment)
    }

    protected override val fragmentContainerId: Int
        protected get() = R.id.mainContainer
}