package com.example.albertsons.initialismform.api

import com.example.albertsons.initialismform.models.api.InitialismResponseModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InitialismApi {
    @GET("/software/acromine/dictionary.py")
    suspend fun fetchAcromine(@Query("sf") sf: String): Response<List<InitialismResponseModelItem>>
}