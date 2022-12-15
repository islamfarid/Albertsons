package com.example.albertsons.initialismform.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.albertsons.MainCoroutineRule
import com.example.albertsons.initialismform.models.ui.InitialismUiState
import com.example.albertsons.initialismform.usecase.GetInitialismUseCase
import com.example.albertsons.initialismform.usecase.PrepareInitialismResultForUiUseCase
import com.example.albertsons.testObserver
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

//    @ExperimentalCoroutinesApi
//    @Test
//    fun `GIVEN setup needed  WHEN Login Activity created THEN  notify Ui with SetUp action`() =
//        mainCoroutineRule.runBlockingTest {
//            val uiStateObserver = viewModel.loginUiState.testObserver()
//            coEvery { loginStatusUseCase.execute(any()) } returns false
//            coEvery { countriesUseCase.execute(Unit) } returns emptyList()
//
//            viewModel.onViewCreated()
//
//            coVerify { loginStatusUseCase.execute(Unit) }
//            TestCase.assertTrue(uiStateObserver.observedValues.contains(LoginUIState.SetUp))
//            TestCase.assertFalse(uiStateObserver.observedValues.contains(LoginUIState.Login))
//        }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `GIVEN viewModel creation WHEN Login Activity created THEN  notify Ui with both SetUp and Countries`() =
//        mainCoroutineRule.runBlockingTest {
//            val uiStateObserver = viewModel.loginUiState.testObserver()
//            coEvery { loginStatusUseCase.execute(any()) } returns false
//            coEvery { countriesUseCase.execute(Unit) } returns emptyList()
//
//            viewModel.onViewCreated()
//
//            coVerify { loginStatusUseCase.execute(Unit) }
//            TestCase.assertTrue(uiStateObserver.observedValues.contains(LoginUIState.SetUp))
//            verifyUiStatesContainsCountries(uiStateObserver)
//        }
//
//    private fun verifyUiStatesContainsCountries(uiStateObserver: TestObserver<LoginUIState>) {
//        var containsCountriesEvent = false
//        uiStateObserver.observedValues.forEach {
//            if (it is LoginUIState.CountriesLoaded) {
//                containsCountriesEvent = true
//            }
//        }
//        TestCase.assertTrue(containsCountriesEvent)
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `GIVEN setup is not needed  WHEN Login Activity created THEN  notify Ui with Login action`() =
//        mainCoroutineRule.runBlockingTest {
//            val uiStateObserver = viewModel.loginUiState.testObserver()
//            coEvery { loginStatusUseCase.execute(any()) } returns true
//            coEvery { countriesUseCase.execute(Unit) } returns emptyList()
//
//            viewModel.onViewCreated()
//
//            coVerify { loginStatusUseCase.execute(Unit) }
//            TestCase.assertFalse(uiStateObserver.observedValues.contains(LoginUIState.SetUp))
//            TestCase.assertTrue(uiStateObserver.observedValues.contains(LoginUIState.Login))
//        }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `GIVEN userName and password ready  WHEN login mode, and user clicks login THEN call login usecase`() =
//        mainCoroutineRule.runBlockingTest {
//            val userName = "userName"
//            val password = "password"
//            coEvery { loginStatusUseCase.execute(any()) } returns true
//            coEvery { countriesUseCase.execute(Unit) } returns emptyList()
//            coEvery { loginUseCase.execute(CurrentUser(userName, password)) } returns true
//            viewModel.onViewCreated()
//
//            viewModel.onLogin(userName, password)
//
//            coVerify { loginUseCase.execute(CurrentUser(userName, password)) }
//        }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `GIVEN userName and password ready  WHEN setup mode, and user clicks setup THEN call setup usecase`() =
//        mainCoroutineRule.runBlockingTest {
//            val userName = "userName"
//            val password = "password"
//            coEvery { loginStatusUseCase.execute(any()) } returns false
//            coEvery { countriesUseCase.execute(Unit) } returns emptyList()
//            coEvery { loginSetUpUseCase.execute(CurrentUser(userName, password)) } returns true
//            viewModel.onViewCreated()
//
//            viewModel.onLogin(userName, password)
//
//            coVerify { loginSetUpUseCase.execute(CurrentUser(userName, password)) }
//        }
}