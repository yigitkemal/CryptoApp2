package com.example.cryptoapp2.service

import com.example.cryptoapp2.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET


interface CryptoAPI {

    @GET("prices?key=fc673eb872161cda9a1e7639b8b31f43fa24f441")
    open fun getData(): Call<List<CryptoModel>>

}