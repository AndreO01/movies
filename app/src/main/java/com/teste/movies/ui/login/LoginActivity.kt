package com.teste.movies.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Toast
import com.teste.movies.BR
import com.teste.movies.R
import com.teste.movies.databinding.ActivityLoginBinding
import com.teste.movies.ui.base.BaseActivity
import com.teste.movies.ui.main.MainActivity
import com.teste.movies.ui.sign.up.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    private var viewModel: LoginViewModel? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModel(): LoginViewModel {
        viewModel = LoginViewModel()
        return viewModel!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        viewModel!!.setNavigator(this)
        viewModel!!.init()
    }

    override fun intentToSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    override fun login() {
        if (isValidFields()) {
            progressBarContainer.visibility = View.VISIBLE
            viewModel!!.authenticate(this, emailEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    override fun success() {

        progressBarContainer.visibility = View.GONE

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun handlerError(message: String) {
        progressBarContainer.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun isValidFields(): Boolean {

        return when {

            emailEditText.text.isNullOrEmpty() -> {
                emailEditText.error = getString(R.string.mandatory_field)
                emailEditText.requestFocus()
                false
            }

            passwordEditText.text.isNullOrEmpty() -> {
                passwordEditText.error = getString(R.string.mandatory_field)
                passwordEditText.requestFocus()
                false
            }
            else -> true
        }
    }
}
