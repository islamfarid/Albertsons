package com.example.albertsons.initialismform.models.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InitialismResponseModelItem(
    @SerialName("lfs")
    val lfApiModels: List<LfApiModel>,
    @SerialName("sf")
    val sf: String
)