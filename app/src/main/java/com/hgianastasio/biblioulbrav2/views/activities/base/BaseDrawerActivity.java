package com.hgianastasio.biblioulbrav2.views.activities.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.widget.TextView;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.models.user.UserModel;
import com.hgianastasio.biblioulbrav2.views.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heitor_12 on 09/05/17.
 */

public abstract class BaseDrawerActivity extends BaseFragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.nav_view)
    protected NavigationView navigationDrawer;

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawer;


    @Override
    protected void preBind() {
        super.preBind();
        setContentView(R.layout.main_drawer_layout);
    }

    @Override
    protected void postBind() {
        super.postBind();
        navigationDrawer.setNavigationItemSelectedListener(this);
        createDrawerToggle();
    }

    protected void createDrawerToggle(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void configureDrawer(UserModel model){
        ((TextView)navigationDrawer.getHeaderView(0).findViewById(R.id.tvName)).setText(model.getName());
        ((TextView)navigationDrawer.getHeaderView(0).findViewById(R.id.tvCGU)).setText(model.getCgu());
    }

    @Override
    protected void callFragment(BaseFragment fragment) {
        super.callFragment(fragment);
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.mainContainer;
    }
}
