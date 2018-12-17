package com.teste.movies.ui.sign.up

import com.teste.movies.ui.base.BaseNavigator

interface SignUpNavigator : BaseNavigator{

    fun success(message: String)
    fun handlerError(message: String)
    fun signUp()
}