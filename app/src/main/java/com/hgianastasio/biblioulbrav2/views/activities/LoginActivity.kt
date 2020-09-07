package com.hgianastasio.biblioulbrav2.views.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hgianastasio.biblioulbrav2.databinding.ActivityLoginBinding
import com.hgianastasio.biblioulbrav2.models.user.UserModel
import com.hgianastasio.biblioulbrav2.navigation.Navigator
import com.hgianastasio.biblioulbrav2.presenters.UserModelPresenter
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener
import com.hgianastasio.biblioulbrav2.views.viewBinding
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity(), OnProgressListener {
    val binding by viewBinding(ActivityLoginBinding::inflate)
    val presenter: UserModelPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter!!.getUser({ model: UserModel? -> processUserModelResult(model) }, {  showLoginForm() })
        binding.btnLogin.setOnClickListener { v: View? ->
            if (binding.etCgu.text.toString().isEmpty()) {
                Toast.makeText(this, "Preencha o campo com seu CGU", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            presenter!!.login(binding.etCgu.text.toString(),
                    {
                        Navigator.toHomeActivity(this)
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


    private fun processUserModelResult(model: UserModel?) {
        if (model != null) {
            Navigator.toHomeActivity(this)
            finish()
        } else {
            showLoginForm()
        }
    }

    private fun showLoginForm() = binding.run {
        btnLogin.visibility = View.VISIBLE
        tiCgu.visibility = View.VISIBLE
    }

    override fun onPause() {
        presenter!!.unsetProgressListener()
        super.onPause()
    }

    override fun showProgress() {
        binding.progress.root.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.root.visibility = View.INVISIBLE
    }

    override fun showRetry() {}
    override fun hideRetry() {}
}