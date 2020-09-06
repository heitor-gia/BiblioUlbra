package com.hgianastasio.biblioulbrav2.views.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import com.google.android.material.textfield.TextInputLayout
import com.hgianastasio.biblioulbrav2.R
import com.hgianastasio.biblioulbrav2.models.user.UserModel
import com.hgianastasio.biblioulbrav2.navigation.Navigator
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter
import com.hgianastasio.biblioulbrav2.views.activities.base.BaseActivity
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import javax.inject.Inject

class LoginActivity : BaseActivity(), OnProgressListener {
    @kotlin.jvm.JvmField
    @BindView(R.id.btnLogin)
    var btnLogin: Button? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.etCgu)
    var etCgu: EditText? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.tiCgu)
    var tiCGU: TextInputLayout? = null

    @kotlin.jvm.JvmField
    @BindView(R.id.progress)
    var progress: View? = null

    @kotlin.jvm.JvmField
    @Inject
    var presenter: UserModelPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        presenter!!.getUser({ model: UserModel? -> processUserModelResult(model) }, {  showLoginForm() })
        btnLogin!!.setOnClickListener { v: View? ->
            if (etCgu!!.text.toString().isEmpty()) {
                Toast.makeText(this, "Preencha o campo com seu CGU", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            presenter!!.login(etCgu!!.text.toString(),
                    {
                        Navigator.toMainActivity(this)
                        finish()
                    },
                    { e: Exception -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show() }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        presenter?.progressListener = this
    }

    override fun preBind() {
        super.preBind()
        setContentView(R.layout.activity_login)
    }

    private fun processUserModelResult(model: UserModel?) {
        if (model != null) {
            Navigator.toMainActivity(this)
            finish()
        } else {
            showLoginForm()
        }
    }

    private fun showLoginForm() {
        btnLogin!!.visibility = View.VISIBLE
        tiCGU!!.visibility = View.VISIBLE
    }

    override fun onPause() {
        presenter!!.unsetProgressListener()
        super.onPause()
    }

    override fun showProgress() {
        progress!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress!!.visibility = View.INVISIBLE
    }

    override fun showRetry() {}
    override fun hideRetry() {}
}