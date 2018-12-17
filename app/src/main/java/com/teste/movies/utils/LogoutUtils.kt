package com.teste.movies.utils

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.teste.movies.ui.login.LoginActivity

class LogoutUtils(private var context: Context) {

    private val mAuth = FirebaseAuth.getInstance()

    fun logout() {

        mAuth.signOut()

        val prefs = AppPreferences.getInstance(context)
        prefs.favorites = ""

        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}