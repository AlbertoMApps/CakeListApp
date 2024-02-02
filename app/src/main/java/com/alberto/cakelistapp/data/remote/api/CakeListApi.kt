package com.alberto.cakelistapp.data.remote.api

import com.alberto.cakelistapp.data.common.CAKE_LIST_API_BASE_URL
import com.alberto.cakelistapp.data.remote.model.CakeList
import retrofit2.http.GET

interface CakeListApi {
    @GET(CAKE_LIST_API_BASE_URL)
    suspend fun getCakeList(): CakeList

}