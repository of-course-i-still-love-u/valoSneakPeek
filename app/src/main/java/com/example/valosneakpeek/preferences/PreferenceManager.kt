package com.example.valosneakpeek.preferences

import com.example.valosneakpeek.ValoSneakPeekApp
import com.example.valosneakpeek.utils.AppConstants

class PreferenceManager : PreferenceManagerNavigator {
    private val preference = ValoSneakPeekApp.instanceAppPreference

    override fun setCountDownBanner(key: Long) {
        preference.edit().putLong(AppConstants.Store.KEY_PREF_STORE_COUNT_DOWN, key).apply()
    }

    override fun getCountDownBanner(): Long =
        preference.getLong(AppConstants.Store.KEY_PREF_STORE_COUNT_DOWN, 0L)

    override fun clearPrefData() {
        preference.edit().clear().apply()
    }


}