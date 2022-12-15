package com.example.albertsons.initialismform.datasource

import com.example.albertsons.initialismform.api.InitialismApi
import com.example.albertsons.initialismform.models.api.InitialismResponseModelItem
import javax.inject.Inject

class InitialismDataSourceImp @Inject constructor(private val initialismApi: InitialismApi) :
    InitialismDataSource {
    override suspend fun fetchAcromine(sf: String): List<InitialismResponseModelItem> {
        return initialismApi.fetchAcromine(sf).body() ?: error(SOMETHING_WENT_WRONG)
    }

    companion object {
        const val SOMETHING_WENT_WRONG =
            "SOMETHING WENT WRONG" // Just as a simple as no error handler component
    }
}