package com.ir.smkcoding.CONNECTION


import com.ir.smkcoding.model.ResponseMovieModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("movie/now_playing")
    fun getlistMovieNowPlaying(
        @Query("api_key")
        apiKey: String
    ): Observable<ResponseMovieModel>
}