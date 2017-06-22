package com.hgianastasio.biblioulbrav2.views.activities;

import android.support.v7.widget.Toolbar;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by heitor_12 on 06/06/17.
 */

public class SettingsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void preBind() {
        super.preBind();
    }

    @Override
    protected void postBind() {
        super.postBind();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
