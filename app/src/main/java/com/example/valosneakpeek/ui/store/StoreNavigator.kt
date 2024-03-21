package com.example.valosneakpeek.ui.store

interface StoreNavigator {
    fun showLoading()
    fun hideLoading()

    fun getWeaponsList()
    fun getWeaponsListSuccess()
    fun getWeaponsListFailed(exception : Exception)
}