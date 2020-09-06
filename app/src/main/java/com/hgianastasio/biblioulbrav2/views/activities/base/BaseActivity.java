package com.hgianastasio.biblioulbrav2.views.activities.base;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.di.DaggerApplicationInjector;
import com.hgianastasio.biblioulbrav2.di.components.ActivityComponent;
import com.hgianastasio.biblioulbrav2.di.components.DaggerActivityComponent;
import com.hgianastasio.biblioulbrav2.di.modules.ActivityModule;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by heitorgianastasio on 11/29/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preBind();
        ButterKnife.bind(this);
        postBind();
    }

    @CallSuper
    protected void preBind(){}

    @CallSuper
    protected void postBind(){
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.default_menu_about,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.miAbout)
            createAboutDialog().show();
        return super.onOptionsItemSelected(item);
    }

    protected String[] getPermissions(){
        return new String[]{};
    }

    private AlertDialog createAboutDialog(){
        TextView message = (TextView) LayoutInflater.from(this).inflate(R.layout.dialog_about_layout,null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            message.setText(Html.fromHtml(getString(R.string.about_message,getString(R.string.app_version)),0));
        else
            message.setText(Html.fromHtml(getString(R.string.about_message,getString(R.string.app_version))));
        message.setMovementMethod(LinkMovementMethod.getInstance());
        return new AlertDialog.Builder(this)
                    .setTitle("Sobre")
                    .setView(message)
                    .setNeutralButton("OK",null)
                    .create();
    }

    public final ActivityComponent getActivityComponent(){
        return DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(DaggerApplicationInjector.get())
                .build();
    }
}
