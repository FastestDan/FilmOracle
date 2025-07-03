package ix.fd.gekinavi.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi{
    @Headers("X-API-KEY: DYFDAKB-MSZMZ3G-MK3VFRK-57R5S3R")
    @GET("movie")
    suspend fun getPageOfMovies(@Query("page") page: Int): Movies

    @Headers("X-API-KEY: DYFDAKB-MSZMZ3G-MK3VFRK-57R5S3R")
    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: Int): Movie

    @Headers("X-API-KEY: DYFDAKB-MSZMZ3G-MK3VFRK-57R5S3R")
    @GET("movie")
    suspend fun getMoviesByName(@Query("name") name: String): Movies
}