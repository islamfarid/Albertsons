package com.example.albertsons.initialismform.datasource

import com.example.albertsons.initialismform.models.api.InitialismResponseModelItem
import retrofit2.Response
import retrofit2.http.Query

interface InitialismDataSource {
    suspend fun fetchAcromine(sf: String): List<InitialismResponseModelItem>

}