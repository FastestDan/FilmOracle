package ix.fd.gekinavi.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi{
    @Headers("X-API-KEY: DYFDAKB-MSZMZ3G-MK3VFRK-57R5S3R")
    @GET("movie")
//  page=1&limit=10&notNullFields=name&notNullFields=description&notNullFields=type&notNullFields=year&notNullFields=genres.name&notNullFields=poster.url&notNullFields=persons.id&notNullFields=persons.name&notNullFields=votes.kp&notNullFields=countries.name
    suspend fun getPageOfMovies(@Query("page") page: Int,
                                @Query("notNullFields") notNullFields: List<String> =
                                    listOf<String>("name", "poster.url", "year", "description")
    ): Movies

    @Headers("X-API-KEY: DYFDAKB-MSZMZ3G-MK3VFRK-57R5S3R")
    @GET("movie/{id}")
    suspend fun getMovieById(@Path("id") id: Int?): Movie

    @Headers("X-API-KEY: DYFDAKB-MSZMZ3G-MK3VFRK-57R5S3R")
    @GET("movie")
    suspend fun getMoviesByName(@Query("name") name: String): Movies
}