package com.example.segundoparcialkotlin

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getFruits(@Url url: String): Response<MutableList<Fruit>>

}