package com.hgianastasio.biblioulbrav2.views.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoadCache;
import com.hgianastasio.biblioulbrav2.navigation.Navigator;
import com.hgianastasio.biblioulbrav2.presenters.LoadCachePresenter;
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter;
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity;
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseDrawerActivity;
import com.hgianastasio.biblioulbrav2.views.fragments.HistoryBooksListFragment;
import com.hgianastasio.biblioulbrav2.views.fragments.HomeFragment;
import com.hgianastasio.biblioulbrav2.views.fragments.LoanBooksListFragment;
import com.hgianastasio.biblioulbrav2.views.fragments.SearchFragment;
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by heitor_12 on 09/05/17.
 */

public class MainActivity extends BaseDrawerActivity {
    @Inject
    protected UserModelPresenter userModelPresenter;
    @Inject
    protected LoadCachePresenter loadCachePresenter;

    @BindView(R.id.mainProgress)
    ProgressBar mainProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        loadCachePresenter.setProgressListener(
                new OnProgressListener() {
                    @Override
                    public void showProgress() {mainProgress.setVisibility(View.VISIBLE);}

                    @Override
                    public void hideProgress() {mainProgress.setVisibility(View.GONE);}

                    @Override
                    public void showRetry() {}

                    @Override
                    public void hideRetry() {}
                }
        );
        loadCachePresenter.loadCache();
        callFragment(new HomeFragment());
    }

    @Override
    protected void onStart() {
        super.onStart();
        userModelPresenter.getUser(
                this::configureDrawer,
                e->{
                    Toast.makeText(this, "Sessão inválida", Toast.LENGTH_SHORT).show();
                    userModelPresenter.logout(
                            success-> {
                                Navigator.toLoginActivity(this);
                                finish();
                            },
                            error -> Toast.makeText(this,error.getMessage(),Toast.LENGTH_SHORT).show()
                    );
                }
        );
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navHistory:
                callFragment(new HistoryBooksListFragment());
                break;
            case R.id.navLoans:
                callFragment(new LoanBooksListFragment());
                break;
            case R.id.navHome:
                callFragment(new HomeFragment());
                break;
            case R.id.navAdvancedSearch:
                callFragment(new SearchFragment());
                break;
            case R.id.navLogout:
                logout();
                break;
        }
        drawer.closeDrawers();
        return false;
    }

    private void logout(){
        userModelPresenter.logout(
                result -> {
                    Navigator.toLoginActivity(this);
                    finish();
                },
                e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public void onBackPressed() {
        if(!(currentFragment instanceof HomeFragment))
            callFragment(new HomeFragment());
        else
            super.onBackPressed();
    }
}
