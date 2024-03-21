package com.example.valosneakpeek.ui.base

import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N> : ViewModel() {


    abstract fun tag(): String
    private var navigator: WeakReference<N>? = null



    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator(): WeakReference<N>? = navigator

}