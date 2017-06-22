package com.hgianastasio.biblioulbrav2.views.activities.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.views.fragments.BaseFragment;

/**
 * Created by heitor_12 on 09/05/17.
 */

public abstract class BaseFragmentActivity extends BaseActivity {
    protected Fragment currentFragment;


    @IdRes
    protected abstract int getFragmentContainerId();


    protected void callFragment(BaseFragment fragment){
        if(currentFragment!=null)
            if (currentFragment.getClass().getCanonicalName().equals(fragment.getClass().getCanonicalName()))
                return;

        currentFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getFragmentContainerId(), fragment, fragment.getFragTag())
                .commit();

        toolbar.setSubtitle(fragment.getTitle());
    }
}
