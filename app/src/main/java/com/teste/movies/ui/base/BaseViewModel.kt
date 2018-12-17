package com.teste.movies.ui.base

import android.arch.lifecycle.ViewModel
import android.content.Context

abstract class BaseViewModel : ViewModel() {

    private var mNavigator: BaseNavigator? = null

    private var context: Context? = null

    fun getNavigator(): BaseNavigator? {
        return mNavigator
    }

    fun setNavigator(navigator: BaseNavigator) {
        this.mNavigator = navigator
    }

    fun setContext(context: Context) {
        this.context = context
    }

    fun getContext() : Context {
        return context!!
    }
}