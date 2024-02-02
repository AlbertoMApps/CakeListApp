package com.alberto.cakelistapp.domain

import com.alberto.cakelistapp.data.remote.model.Cake
import com.alberto.cakelistapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CakeListRepositoryService {
    fun getCakeList(): Flow<Resource<List<Cake>>>
}