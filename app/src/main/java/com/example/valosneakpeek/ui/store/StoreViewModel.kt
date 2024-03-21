package com.example.valosneakpeek.ui.store

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.valosneakpeek.impl.WeaponsRepositoryImpl
import com.example.valosneakpeek.model.ResultWrapper
import com.example.valosneakpeek.model.weapons.Skin
import com.example.valosneakpeek.model.weapons.Weapons
import com.example.valosneakpeek.networking.WormHole
import com.example.valosneakpeek.networking.service.weapons.WeaponsApiService
import com.example.valosneakpeek.preferences.PreferenceManager
import com.example.valosneakpeek.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class StoreViewModel : BaseViewModel<StoreNavigator>(), CoroutineScope {
    private val job: Job by lazy { SupervisorJob() }
    private val navigator: StoreNavigator? by lazy { getNavigator()?.get() }
    private val ELDERFLAME_THEMEUUID = "6245cbbc-4aa3-b339-0d2e-abb333f6c8c5"
    private val preferenceManager: PreferenceManager = PreferenceManager()
    override fun tag(): String = StoreViewModel::class.java.simpleName
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job


    private var weaponsRepositoryImpl: WeaponsRepositoryImpl

    private var weaponsMutableLiveData = MutableLiveData<MutableList<Weapons>>()
    private var collectionMutableLiveData = MutableLiveData<MutableList<Skin>>()
    private var offersMutableLiveData = MutableLiveData<MutableList<Skin>>()
    val weaponsLiveData: LiveData<MutableList<Weapons>> = weaponsMutableLiveData
    val collectionLiveData: LiveData<MutableList<Skin>> = collectionMutableLiveData
    val offersLiveData: LiveData<MutableList<Skin>> = offersMutableLiveData


    init {
        val weaponsApiService = WormHole.createWormHole(WeaponsApiService::class.java)
        weaponsRepositoryImpl = WeaponsRepositoryImpl(weaponsApiService)
    }

    fun getWeapons() {

        launch {
            navigator?.showLoading()
            when (val result = weaponsRepositoryImpl.getWeaponsList()) {
                is ResultWrapper.Success -> {
                    navigator?.hideLoading()
                    val data = result.value
                    val weaponsList = data.data
                    weaponsList?.let { weaponsDataList ->
                        weaponsMutableLiveData.postValue(weaponsDataList)
                        setUpData(weaponsDataList)
                        navigator?.getWeaponsListSuccess()

                    } ?: run {
                        Timber.d("data -> null")
                        navigator?.getWeaponsListFailed(Exception("DATA NULL"))

                    }


                }

                is ResultWrapper.GenericError -> {

                    navigator?.hideLoading()
                    val exception = result.exception ?: Exception("ERRR")
                    navigator?.getWeaponsListFailed(exception)

                }

                is ResultWrapper.NetworkError -> {

                    navigator?.hideLoading()
                    val exception = result.exception ?: Exception("ERRR")
                    navigator?.getWeaponsListFailed(exception)

                }

            }
        }
    }

    private fun setUpData(data: MutableList<Weapons>) {
        val finalResult: MutableList<Skin> = ArrayList()
        val offersSkinResult : MutableList<Skin> = ArrayList()

        for (weapon in data) {
            val result = weapon.skins.filter { elderFlame ->
                elderFlame.themeUuid == ELDERFLAME_THEMEUUID
            }
            result.forEach { skin ->
                finalResult.add(skin)
            }

             weapon.skins.forEach{
                skin -> offersSkinResult.add(skin)
            }
        }





        offersMutableLiveData.postValue(offersSkinResult)
//        Timber.d("data -> ${finalResult.size}")
        collectionMutableLiveData.postValue(finalResult)

    }

}