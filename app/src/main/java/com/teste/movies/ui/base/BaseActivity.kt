package com.teste.movies.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<out T : ViewDataBinding, out V : BaseViewModel> : AppCompatActivity() {

    private var mViewDataBinding: T? = null
    private var mViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    fun getViewDataBinding(): T? {
        return mViewDataBinding
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = getViewModel()
        this.mViewModel!!.setContext(this)
        mViewDataBinding.run {
            this!!.setVariable(getBindingVariable(), mViewModel)
            executePendingBindings()
        }
    }
}