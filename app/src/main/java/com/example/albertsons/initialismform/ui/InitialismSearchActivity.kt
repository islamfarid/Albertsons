package com.example.albertsons.initialismform.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albertsons.databinding.ActivityInitialismFormBinding
import com.example.albertsons.initialismform.extensions.textChanges
import com.example.albertsons.initialismform.models.ui.InitialismUiState
import com.example.albertsons.initialismform.ui.InitialismTSearchViewModel.Companion.DEBOUNCE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class InitialismSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitialismFormBinding
    private val initialismSearchViewModel: InitialismTSearchViewModel by viewModels()
    lateinit var initialismAdapter : InitialismAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialismFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        onTextChange()
        listenToChanges()
    }

    private fun setUpRecyclerView() {
        initialismAdapter =  InitialismAdapter()
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = initialismAdapter
    }

    private fun onTextChange() {
        binding.initialismEditField.textChanges().drop(1).debounce(DEBOUNCE)
            .onEach {
                initialismSearchViewModel.onSfChanged(it.toString())
            }
            .launchIn(lifecycleScope)
    }

    private fun listenToChanges() {
        initialismSearchViewModel.uiState.observe(this) { uiState ->
            when (uiState) {
                is InitialismUiState.InitialismResultLoaded -> {
                    handleUsersLoaded(uiState)
                }
                is InitialismUiState.Loading -> {
                    handleLoading()
                }
                is InitialismUiState.Error -> {
                    applyErrorUi()
                }
            }
        }
    }

    private fun handleLoading() {
        binding.loadingView.isVisible = true
        binding.rv.isVisible = false
        binding.errorView.isVisible = false
        binding.errorText.isVisible = false
    }

    private fun applyErrorUi() {
        binding.errorView.isVisible = true
        binding.errorText.isVisible = true
        binding.loadingView.isVisible = false
        binding.rv.isVisible = false
    }

    private fun handleUsersLoaded(uiState: InitialismUiState.InitialismResultLoaded) {
        binding.errorView.isVisible = false
        binding.errorText.isVisible = false
        binding.loadingView.isVisible = false
        binding.rv.isVisible = true
        initialismAdapter.items = uiState.initialismUiModels
    }
}
