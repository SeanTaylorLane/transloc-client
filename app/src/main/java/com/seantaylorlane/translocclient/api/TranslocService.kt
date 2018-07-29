package com.seantaylorlane.translocclient.api

import retrofit2.Call
import retrofit2.http.GET

interface TranslocService {
    @GET("agencies")
    fun getAgencies(): Call<TranslocModels.AgenciesResponse>
}