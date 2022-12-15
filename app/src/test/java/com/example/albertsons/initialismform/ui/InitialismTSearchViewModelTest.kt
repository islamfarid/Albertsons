package com.example.albertsons.initialismform.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.albertsons.MainCoroutineRule
import com.example.albertsons.initialismform.models.ui.InitialismUiState
import com.example.albertsons.initialismform.usecase.GetInitialismUseCase
import com.example.albertsons.initialismform.usecase.PrepareInitialismResultForUiUseCase
import com.example.albertsons.testObserver
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class InitialismTSearchViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()
    private lateinit var viewModel: InitialismTSearchViewModel

    private val getInitialismUseCase: GetInitialismUseCase = mockk()
    private val prepareInitialismResultForUiUseCase: PrepareInitialismResultForUiUseCase = mockk()


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        viewModel =
            InitialismTSearchViewModel(getInitialismUseCase, prepareInitialismResultForUiUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `GIVEN viewModel Called  WHEN Text Changed THEN  loading state called`() =
        mainCoroutineRule.runBlockingTest {
            val uiStateObserver = viewModel.uiState.testObserver()
            viewModel.onSfChanged("test")

            TestCase.assertTrue(uiStateObserver.observedValues.contains(InitialismUiState.Loading))
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `GIVEN viewModel Called  WHEN Text Changed THEN  load the list`()=
        mainCoroutineRule.runBlockingTest {
            val sf = "test"
            coEvery { getInitialismUseCase.invoke(sf) } returns emptyList()
            coEvery { prepareInitialismResultForUiUseCase.invoke(emptyList()) } returns emptyList()
            val uiStateObserver = viewModel.uiState.testObserver()
            viewModel.onSfChanged(sf)
            TestCase.assertTrue(uiStateObserver.observedValues.contains(InitialismUiState.InitialismResultLoaded(
                emptyList()
            )))
        }
}