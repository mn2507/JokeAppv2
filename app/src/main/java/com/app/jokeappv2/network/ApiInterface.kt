package com.app.jokeappv2.network

import com.app.jokeappv2.data.response.JokeResponseBody
import com.app.jokeappv2.data.response.JokesResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Muthu Narayanan on 19/11/2021.
 */
interface ApiInterface {

    @GET("joke/Any")
    fun singleAnyJoke(): Observable<JokeResponseBody?>?

    @GET("joke/{category}")
    fun multiAnyJoke(
        @Path("category") category: String?,
        @Query("amount") jokeAmount: Int?,
        @Query("type") jokeType: String?
    ): Observable<JokesResponseBody?>?
}

