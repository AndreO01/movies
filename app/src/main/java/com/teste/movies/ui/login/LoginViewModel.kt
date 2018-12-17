package com.teste.movies.ui.login

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.teste.movies.ui.base.BaseViewModel
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.teste.movies.R.string.email
import com.teste.movies.R.string.email

class LoginViewModel : BaseViewModel(){

    private lateinit var navigator: LoginNavigator

    private val mAuth = FirebaseAuth.getInstance()

    fun init(){
        navigator = getNavigator() as LoginNavigator
    }

    fun loginClick(){
        navigator.login()
    }

    fun signUpClick(){
        navigator.intentToSignUpActivity()
    }

    fun authenticate(context: Context, email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    navigator.success()
                } else {
                    navigator.handlerError("Authentication failed.")
                }
            }
    }
}