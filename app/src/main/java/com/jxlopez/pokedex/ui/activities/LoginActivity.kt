package com.jxlopez.pokedex.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jxlopez.pokedex.R
import com.jxlopez.pokedex.data.PreferencesHelper
import com.jxlopez.pokedex.utils.Utils
import com.jxlopez.pokedex.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.StringBuilder

class LoginActivity : BaseActivity() {

    private var loginViewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        validateLogin()
    }

    private fun validateLogin() {
        if(PreferencesHelper.getLogged(this@LoginActivity))
            loadNextActivity()
        else
            initComponents()
    }

    private fun initComponents() {
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel?.logged?.observe(this, Observer {
            if(it) {
                PreferencesHelper.setLogged(this@LoginActivity, true)
                loadNextActivity()
            } else
                Toast.makeText(this@LoginActivity, getString(R.string.error_autenticate), Toast.LENGTH_SHORT).show()
        })

        loginAuthenticateButton.setOnClickListener {
            val error = validateFields()
            if(error.isNotEmpty()) {
                generateMessageDialog(
                    getString(R.string.error),
                    error,
                    getString(R.string.ok),
                    false,
                    null, null
                )
            } else {
                loginViewModel?.loginUser(loginEmailEditText.text.toString(), loginPasswordEditText.text.toString())
            }
        }
    }

    private fun validateFields(): String {
        val error = StringBuilder()
        if(loginEmailEditText.text.trim().isEmpty()) {
            error.append("- ").append(getString(R.string.email_empty)).append("\n")
        } else if (!Utils.isValidEmail(loginEmailEditText.text.toString())) {
            error.append("- ").append(getString(R.string.email_invalid)).append("\n")
        }
        if(loginPasswordEditText.text.trim().isEmpty()) {
            error.append("- ").append(getString(R.string.password_empty)).append("\n")
        }
        return error.toString()
    }

    private fun loadNextActivity() {
        val intent = Intent(this@LoginActivity, ListPokemonActivity::class.java)
        startActivity(intent)
        finish()
    }
}
