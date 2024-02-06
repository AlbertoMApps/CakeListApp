package com.alberto.cakelistapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.cakelistapp.domain.CakeListRepositoryService
import com.alberto.cakelistapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CakeListViewModel @Inject constructor(
    private val cakeListRepositoryService: CakeListRepositoryService
) : ViewModel() {

    private var cakeListJob: Job? = null
    private val _cakeListState = MutableStateFlow(CakeListState())
    val cakeListState = _cakeListState.asStateFlow()

    init {
        getCakeList()
    }

    fun getCakeList() {
        cakeListJob?.cancel()//This is to cancel any asynchronous calls that come back in the future.
        cakeListJob = viewModelScope.launch {
            cakeListRepositoryService.getCakeList().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _cakeListState.value = CakeListState(
                            isLoading = true
                        )
                    }

                    is Resource.Success -> {
                        _cakeListState.value = CakeListState(
                            data = result.data ?: emptyList()
                        )
                    }

                    else -> {
                        _cakeListState.value = CakeListState(
                            errorMessage = result.message
                                ?: "Unexpected error loading cakes"
                        )
                    }
                }
            }
        }
    }

}