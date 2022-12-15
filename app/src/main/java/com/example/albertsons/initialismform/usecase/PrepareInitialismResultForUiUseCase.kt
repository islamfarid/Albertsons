package com.example.albertsons.initialismform.usecase

import com.example.albertsons.initialismform.di.DefaultCoroutineDispatcher
import com.example.albertsons.initialismform.models.domain.InitialismItem
import com.example.albertsons.initialismform.models.ui.InitialismUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PrepareInitialismResultForUiUseCase @Inject constructor(
    @DefaultCoroutineDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(usersList: List<InitialismItem>): List<InitialismUiModel> {
        return withContext(coroutineDispatcher) {
            val result = mutableListOf<InitialismUiModel>()
            usersList.forEach {
                result.add(InitialismUiModel.InitialismItem(it.sf))
                it.lfs.forEach { lf ->
                    result.add(InitialismUiModel.InitialismSubItem(lf.lf, lf.since, lf.vars))
                }
            }
            result
        }
    }
}