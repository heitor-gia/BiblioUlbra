package com.hgianastasio.biblioulbrav2.views.activities.base

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.hgianastasio.biblioulbrav2.R

/**
 * Created by heitor_12 on 09/05/17.
 */
abstract class BaseDrawerActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    abstract val navigationDrawer: NavigationView?
    abstract val drawer: DrawerLayout?
    override fun preBind() {
        super.preBind()
        setContentView(R.layout.main_drawer_layout)
    }


}