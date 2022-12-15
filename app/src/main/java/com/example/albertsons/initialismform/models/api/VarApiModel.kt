package com.example.albertsons.initialismform.models.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VarApiModel(
    @SerialName("freq")
    val freq: Int,
    @SerialName("lf")
    val lf: String,
    @SerialName("since")
    val since: Long
)