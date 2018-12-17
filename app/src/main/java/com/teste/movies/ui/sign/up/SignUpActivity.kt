package com.teste.movies.ui.sign.up

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.teste.movies.BR
import com.teste.movies.R
import com.teste.movies.databinding.ActivitySignUpBinding
import com.teste.movies.ui.base.BaseActivity
import com.teste.movies.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>(), SignUpNavigator {

    private var viewModel : SignUpViewModel? = null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_sign_up
    }

    override fun getViewModel(): SignUpViewModel {
        viewModel = SignUpViewModel()
        return viewModel!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_up)

        viewModel!!.setNavigator(this)
        viewModel!!.init()

        supportActionBar!!.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(this, R.color.colorGray800)
            )
        )
        supportActionBar!!.elevation = 0f
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        title = ""
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
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


            passwordEditText.text.length < 6 -> {
                passwordEditText.error = "A senha deve conter seis ou mais digitos."
                passwordEditText.requestFocus()
                false
            }

            passwordRetypeEditText.text.isNullOrEmpty() -> {
                passwordRetypeEditText.error = getString(R.string.mandatory_field)
                passwordRetypeEditText.requestFocus()
                false
            }

            passwordEditText.text.toString() != passwordRetypeEditText.text.toString() -> {
                passwordRetypeEditText.error = getString(R.string.wrong_password)
                passwordRetypeEditText.requestFocus()
                false
            }
            else -> true
        }
    }

    override fun success(message: String) {
        progressBarContainer.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun handlerError(message: String) {
        progressBarContainer.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun signUp() {
        if(isValidFields()){
            progressBarContainer.visibility = View.VISIBLE
            viewModel!!.sendToRegister(this, emailEditText.text.toString(), passwordEditText.text.toString())
        }
    }

}
