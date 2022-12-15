package com.example.albertsons.initialismform.usecase

import com.example.albertsons.initialismform.di.IOCoroutineDispatcher
import com.example.albertsons.initialismform.repository.InitialismRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetInitialismUseCase @Inject constructor(
    private val initialismRepository: InitialismRepository,
    @IOCoroutineDispatcher private val CoroutineDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(sf: String) = withContext(CoroutineDispatcher) {
            initialismRepository.fetchAcromine(sf)
    }
}