package com.example.valosneakpeek.networking.service.weapons

import com.example.valosneakpeek.model.BaseApiResponse
import com.example.valosneakpeek.model.weapons.Weapons
import retrofit2.http.GET

interface WeaponsApiService {
    @GET("weapons")
    suspend fun getWeaponsList():BaseApiResponse<MutableList<Weapons>>
}