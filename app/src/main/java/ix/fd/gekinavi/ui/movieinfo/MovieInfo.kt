package ix.fd.gekinavi.ui.movieinfo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import ix.fd.gekinavi.R
import ix.fd.gekinavi.api.GenresForMovie
import ix.fd.gekinavi.api.MovieApi
import ix.fd.gekinavi.api.PersonsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

class MovieInfo : AppCompatActivity() {
    private lateinit var adapter: PersonsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)
        var id = intent.getStringExtra("id")?.toInt()

        var actors = findViewById<RecyclerView>(R.id.infoActors)
        adapter = PersonsAdapter()
        actors.adapter = adapter

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().baseUrl("https://api.kinopoisk.dev/v1.4/")
            .client(client).addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(MovieApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val movie = api.getMovieById(id)
            runOnUiThread {
                findViewById<TextView>(R.id.infoName).text = movie.name
                findViewById<TextView>(R.id.infoYear).text = movie.year.toString()
                findViewById<TextView>(R.id.infoRating).text = movie.rating.kp.toString()
                var genre = movie.genres
                var genre_string = ""
                for (i in genre){
                    if (genre_string == ""){
                        genre_string = i.name
                    }
                    else{
                        genre_string = genre_string + ", " + i.name
                    }
                }
                findViewById<TextView>(R.id.infoGenres).text = genre_string
                var country = movie.countries
                var country_string = ""
                for (i in country){
                    if (country_string == ""){
                        country_string = i.name
                    }
                    else{
                        country_string = country_string + ", " + i.name
                    }
                }
                findViewById<TextView>(R.id.infoCountry).text = country_string
                findViewById<TextView>(R.id.infoDesc).text = movie.description
                Picasso.get().load(movie.poster.url).into(findViewById<ImageView>(R.id.infoPoster))
                adapter.submitList(movie.persons)
            }
        }
        findViewById<FloatingActionButton>(R.id.fav_button).setOnClickListener {  }
    }
}