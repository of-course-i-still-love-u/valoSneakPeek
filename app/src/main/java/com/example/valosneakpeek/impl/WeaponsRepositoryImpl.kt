package com.example.valosneakpeek.impl

import androidx.lifecycle.MutableLiveData
import com.example.valosneakpeek.model.BaseApiResponse
import com.example.valosneakpeek.model.ResultWrapper
import com.example.valosneakpeek.model.callApiPortal
import com.example.valosneakpeek.model.weapons.Weapons
import com.example.valosneakpeek.networking.service.weapons.WeaponsApiService
import com.example.valosneakpeek.repo.WeaponsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WeaponsRepositoryImpl(
    private val weaponsApiService: WeaponsApiService,
    private val coroutineContext: CoroutineDispatcher = Dispatchers.IO
) : WeaponsRepository {
    override suspend fun getWeaponsList(): ResultWrapper<BaseApiResponse<MutableList<Weapons>>> =

        callApiPortal(coroutineContext) {
            weaponsApiService.getWeaponsList()
        }


}