package com.hgianastasio.biblioulbrav2.views.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hgianastasio.biblioulbrav2.di.components.ActivityComponent;
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by heitor_12 on 09/05/17.
 */

public abstract class BaseFragment extends Fragment {
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(),container,false);
        preBind();
        unbinder = ButterKnife.bind(this,view);
        postBind();
        return view;
    }

    protected void preBind(){}

    protected void postBind(){}

    @LayoutRes
    protected abstract int getLayoutID();

    public abstract String getFragTag();

    public abstract String getTitle();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    ActivityComponent getComponent(){
        return ((BaseActivity)getActivity()).getActivityComponent();
    }
}
