package com.teste.movies.ui.sign.up

import android.app.Activity
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.teste.movies.ui.base.BaseViewModel
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.teste.movies.R.string.email



class SignUpViewModel : BaseViewModel() {

    private lateinit var navigator: SignUpNavigator

    private val mAuth = FirebaseAuth.getInstance()

    fun init() {
        navigator = getNavigator() as SignUpNavigator
    }

    fun signUpClick() {
        navigator.signUp()
    }

    fun sendToRegister(context: Context, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(context as Activity) { task ->
                if (task.isSuccessful) {
                    navigator.success("Sign in success")
                } else {
                   navigator.handlerError("Authentication failed.")
                }
            }
    }
}