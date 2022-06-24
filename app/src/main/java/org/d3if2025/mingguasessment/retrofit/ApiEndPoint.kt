package org.d3if2025.mingguasessment.retrofit

import org.d3if2025.mingguasessment.MainModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("data.php")
    fun getData() : Call<MainModel>
}