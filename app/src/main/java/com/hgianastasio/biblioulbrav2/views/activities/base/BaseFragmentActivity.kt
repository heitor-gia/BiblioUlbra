package com.hgianastasio.biblioulbrav2.views.activities.base

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.views.fragments.BaseFragment

/**
 * Created by heitor_12 on 09/05/17.
 */
abstract class BaseFragmentActivity : BaseActivity() {
    protected var currentFragment: Fragment? = null

    @get:IdRes
    protected abstract val fragmentContainerId: Int
    protected open fun callFragment(fragment: BaseFragment) {
        if (currentFragment != null) if (currentFragment!!.javaClass.canonicalName == fragment.javaClass.canonicalName) return
        currentFragment = fragment
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom)
                .replace(fragmentContainerId, fragment, fragment.fragTag)
                .commit()
        toolbar?.subtitle = fragment.title
    }
}