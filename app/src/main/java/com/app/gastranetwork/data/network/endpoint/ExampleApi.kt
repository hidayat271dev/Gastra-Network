package com.app.gastranetwork.data.network.endpoint

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface ExampleApi {

    @GET("/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02")
    fun getExample(): Observable<Response<String>>

}
