package com.teste.movies.ui.splash

import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.teste.movies.ui.base.BaseViewModel

class SplashViewModel : BaseViewModel() {

    private lateinit var navigator: SplashNavigator

    private val mAuth = FirebaseAuth.getInstance()

    fun init() {
        navigator = getNavigator() as SplashNavigator

        val handler = Handler()

        handler.postDelayed({


            if (mAuth.currentUser != null) {
                navigator.intentToMainActivity()
            } else
                navigator.intentToLoginActivity()

        }, 2000)
    }
}