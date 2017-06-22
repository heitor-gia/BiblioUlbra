package com.hgianastasio.biblioulbrav2.views.activities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.TextView;
import android.widget.Toast;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.models.user.UserModel;
import com.hgianastasio.biblioulbrav2.navigation.Navigator;
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter;
import com.hgianastasio.biblioulbrav2.views.fragments.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heitor_12 on 09/05/17.
 */

public abstract class BaseDrawerActivity extends BaseFragmentActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected NavigationView navigationDrawer;

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawer;



    @Override
    protected void preBind() {
        super.preBind();
        setContentView(R.layout.main_drawer_layout);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationDrawer = ButterKnife.findById(drawer,R.id.nav_view);
        navigationDrawer.setNavigationItemSelectedListener(this);
        createDrawerToggle();
    }



    protected void createDrawerToggle(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    protected void configureDrawer(UserModel model){
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
