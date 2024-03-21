package com.example.valosneakpeek.model.weapons

data class WeaponStats(
    val adsStats: AdsStats,
    val airBurstStats: Any,
    val altFireType: String,
    val altShotgunStats: Any,
    val damageRanges: List<DamageRange>,
    val equipTimeSeconds: Double,
    val feature: String,
    val fireMode: Any,
    val fireRate: Float,
    val firstBulletAccuracy: Double,
    val magazineSize: Int,
    val reloadTimeSeconds: Float,
    val runSpeedMultiplier: Double,
    val shotgunPelletCount: Int,
    val wallPenetration: String
)