package com.hgianastasio.biblioulbrav2.views.activities;

import android.os.Bundle;
import com.google.android.material.textfield.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hgianastasio.biblioulbrav2.R;
import com.hgianastasio.biblioulbrav2.models.user.UserModel;
import com.hgianastasio.biblioulbrav2.navigation.Navigator;
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter;
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity;
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener;

import javax.inject.Inject;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements OnProgressListener {

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.etCgu)
    EditText etCgu;
    @BindView(R.id.tiCgu)
    TextInputLayout tiCGU;
    @BindView(R.id.progress)
    View progress;

    @Inject
    UserModelPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        presenter.getUser(this::processUserModelResult,e->showLoginForm());
        btnLogin.setOnClickListener((v)->{
            if (etCgu.getText().toString().isEmpty()){
                Toast.makeText(this,"Preencha o campo com seu CGU", Toast.LENGTH_SHORT).show();
                return;
            }
            presenter.login(etCgu.getText().toString(),
                    result -> {
                        Navigator.toMainActivity(this);
                        finish();
                    },
                    e -> {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setProgressListener(this);
    }

    @Override
    protected void preBind() {
        super.preBind();
        setContentView(R.layout.activity_login);
    }


    private void processUserModelResult(UserModel model){
        if(model!=null) {
            Navigator.toMainActivity(this);
            finish();
        }else {
            showLoginForm();
        }
    }

    private void showLoginForm(){
        btnLogin.setVisibility(View.VISIBLE);
        tiCGU.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        presenter.unsetProgressListener();
        super.onPause();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showRetry() {}

    @Override
    public void hideRetry() {}
}
