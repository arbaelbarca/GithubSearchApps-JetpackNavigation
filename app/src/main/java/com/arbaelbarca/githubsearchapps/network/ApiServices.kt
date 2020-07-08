package com.arbaelbarca.githubsearchapps.network

import com.arbaelbarca.githubsearchapps.model.modeluserlist.ModelListUser

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiServices {
    @GET("search/users")
    fun getListUser(
            @Query("q") textSearch: String,
            @Query("sort") sort: String

    ): Observable<Response<ModelListUser>>
}
