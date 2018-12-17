package com.teste.movies.ui.login

import com.teste.movies.ui.base.BaseNavigator

interface LoginNavigator : BaseNavigator {

    fun intentToSignUpActivity()
    fun login()
    fun success()
    fun handlerError(message: String)
}