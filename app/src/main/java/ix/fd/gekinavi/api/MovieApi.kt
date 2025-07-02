package ix.fd.gekinavi.api

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi{
    @GET("movie?page={page}&limit=15")
    suspend fun getPageOfMovies(@Path("page") page: Int): List<Movie>
}