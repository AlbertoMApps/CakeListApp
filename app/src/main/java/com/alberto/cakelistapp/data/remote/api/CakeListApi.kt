package com.alberto.cakelistapp.data.remote.api

import com.alberto.cakelistapp.data.remote.model.Cake
import retrofit2.http.GET

interface CakeListApi {
    @GET()
    suspend fun getCakeList(): List<Cake>

}