package com.example.findmovie.network

object ApiUtils {
    val instance by lazy { RetrofitClient.getInstance().create(WebApiService::class.java)}
}