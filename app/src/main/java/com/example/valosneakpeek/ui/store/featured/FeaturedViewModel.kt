package com.example.valosneakpeek.ui.store.featured

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.valosneakpeek.model.timer.Timer
import com.example.valosneakpeek.model.weapons.Weapons
import com.example.valosneakpeek.preferences.PreferenceManager
import com.example.valosneakpeek.ui.base.BaseViewModel
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class FeaturedViewModel : BaseViewModel<FeaturedNavigator>() {
    private val preferenceManager: PreferenceManager = PreferenceManager()
    private var countDownMutableLiveData = MutableLiveData<Timer>()
    val countDownLiveData: LiveData<Timer> = countDownMutableLiveData


    init {
        val getCount = preferenceManager.getCountDownBanner()
        if (getCount > 0L) setCountDownTimer(getCount) else setCountDownTimer(432000000)

    }


    fun setCountDownTimer(milliSec: Long) {
        //432000000 millisSec = 5 day

        object : CountDownTimer(milliSec, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                val millisSecDuration: Duration = millisUntilFinished.milliseconds
                val durationString =
                    millisSecDuration.toComponents { days, hours, minutes, seconds, _ ->
                        "%02d:%02d:%02d:%02d".format(days, hours, minutes, seconds)
                    }
                countDownMutableLiveData.postValue(Timer(millisUntilFinished, durationString))
//                Timber.d("--> ${durationString}")
            }

            override fun onFinish() {
                countDownMutableLiveData.postValue(Timer(0L, "00:00:00:00"))


            }

        }.start()

    }


    override fun tag(): String = FeaturedViewModel::class.java.simpleName

}

