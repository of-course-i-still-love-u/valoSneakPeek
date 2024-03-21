package com.example.valosneakpeek.repo

import androidx.lifecycle.MutableLiveData
import com.example.valosneakpeek.model.BaseApiResponse
import com.example.valosneakpeek.model.ResultWrapper
import com.example.valosneakpeek.model.weapons.Weapons

interface WeaponsRepository {

    suspend fun getWeaponsList(): ResultWrapper<BaseApiResponse<MutableList<Weapons>>>
}