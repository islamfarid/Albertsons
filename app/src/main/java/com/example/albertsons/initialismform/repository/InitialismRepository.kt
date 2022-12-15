package com.example.albertsons.initialismform.repository

import com.example.albertsons.initialismform.datasource.InitialismDataSource
import com.example.albertsons.initialismform.models.api.InitialismResponseModelItem
import com.example.albertsons.initialismform.models.domain.InitialismItem
import com.example.albertsons.initialismform.models.domain.Lf
import com.example.albertsons.initialismform.models.domain.Var
import javax.inject.Inject

class InitialismRepository @Inject constructor(private val initialismDataSource: InitialismDataSource) {
    suspend fun fetchAcromine(sf: String): List<InitialismItem> =
        initialismDataSource.fetchAcromine(sf).map { getInitialismItem(it) }

    private fun getInitialismItem(initialismResponseModelItem: InitialismResponseModelItem) =
        InitialismItem(lfs = initialismResponseModelItem.lfApiModels.map { lf ->
            Lf(since = lf.since,
                lf = lf.lf,
                vars = lf.varApiModels.map { Var(lf = it.lf, since = it.since) })
        }, sf = initialismResponseModelItem.sf)
}