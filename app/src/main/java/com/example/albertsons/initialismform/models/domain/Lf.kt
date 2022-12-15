package com.example.albertsons.initialismform.models.domain



data class Lf(
    val lf: String,
    val since: Long,
    val vars: List<Var>
)