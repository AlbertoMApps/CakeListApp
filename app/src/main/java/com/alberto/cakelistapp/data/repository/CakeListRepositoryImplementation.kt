package com.alberto.cakelistapp.data.repository

import com.alberto.cakelistapp.data.remote.api.CakeListApi
import com.alberto.cakelistapp.data.remote.model.Cake
import com.alberto.cakelistapp.domain.CakeListRepositoryService
import com.alberto.cakelistapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CakeListRepositoryImplementation @Inject constructor(
    private val cakeListApi: CakeListApi
) : CakeListRepositoryService {

    override fun getCakeList(): Flow<Resource<List<Cake>>> = flow {
        emit(Resource.Loading())
        val cakeList = cakeListApi.getCakeList()
        emit(Resource.Success(data = cakeList))
    }.catch {
        emit(
            Resource.Error(
                message = it.localizedMessage ?: "Unexpected error loading random data of cakes"
            )
        )
    }

}