package com.alberto.cakelistapp.presentation

import com.alberto.cakelistapp.data.remote.model.Cake

data class CakeListState(
    val isLoading: Boolean = false,
    val data: List<Cake> = emptyList(),
    val errorMessage: String = ""
)