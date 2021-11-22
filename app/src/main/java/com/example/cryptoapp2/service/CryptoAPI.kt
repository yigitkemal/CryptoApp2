package com.example.cryptoapp2.service

import com.example.cryptoapp2.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET


interface CryptoAPI {

    @GET("prices?key=fc673eb872161cda9a1e7639b8b31f43fa24f441")
    fun getData(): Observable<List<CryptoModel>>

    //fun getData(): Call<List<CryptoModel>>

}