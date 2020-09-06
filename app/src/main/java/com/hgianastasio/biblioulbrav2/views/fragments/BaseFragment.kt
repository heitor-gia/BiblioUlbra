package com.hgianastasio.biblioulbrav2.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import com.hgianastasio.biblioulbrav2.di.components.ActivityComponent
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity

/**
 * Created by heitor_12 on 09/05/17.
 */
abstract class BaseFragment : Fragment() {
    protected var unbinder: Unbinder? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutID, container, false)
        preBind()
        unbinder = ButterKnife.bind(this, view)
        postBind()
        return view
    }

    protected fun preBind() {}
    protected open fun postBind() {}

    @get:LayoutRes
    protected abstract val layoutID: Int
    abstract val fragTag: String
    abstract val title: String
    override fun onDestroyView() {
        super.onDestroyView()
        unbinder!!.unbind()
    }

    val component: ActivityComponent?
        get() = (activity as? BaseActivity)?.activityComponent
}