package com.alberto.cakelistapp.data.remote.api

import com.alberto.cakelistapp.data.common.CAKE_LIST_API_CLIENT
import com.alberto.cakelistapp.data.remote.model.Cake
import retrofit2.http.GET

interface CakeListApi {
    @GET(CAKE_LIST_API_CLIENT)
    suspend fun getCakeList(): List<Cake>

}