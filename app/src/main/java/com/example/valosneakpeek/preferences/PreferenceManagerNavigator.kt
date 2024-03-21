package com.example.valosneakpeek.preferences

interface PreferenceManagerNavigator {
        fun setCountDownBanner(key:Long)
        fun getCountDownBanner():Long?

        fun clearPrefData()
}