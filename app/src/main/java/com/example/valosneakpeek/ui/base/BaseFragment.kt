package com.example.valosneakpeek.ui.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment<V:BaseViewModel<*>>:Fragment() {

    abstract fun tag() : String
    abstract fun setUpView()
    abstract fun initial()

    fun justShowToast(context : Context, message : String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}