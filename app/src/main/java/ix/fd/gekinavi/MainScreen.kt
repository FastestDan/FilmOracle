package ix.fd.gekinavi

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ix.fd.gekinavi.api.MovieAdapter
import ix.fd.gekinavi.api.MovieApi
import ix.fd.gekinavi.databinding.ActivityMainScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainScreen : AppCompatActivity() {
    private lateinit var adapter: MovieAdapter
    private lateinit var binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

//        adapter = MovieAdapter()
//        var movieCards = findViewById<RecyclerView>(R.id.movie_cards)
//        movieCards.layoutManager = GridLayoutManager(this, 2)
//        movieCards.adapter = adapter

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main_screen)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_selection, R.id.navigation_favourites
            )
        )
        val retrofit = Retrofit.Builder().baseUrl("https://api.kinopoisk.dev/v1.4/").client(client).addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(MovieApi::class.java)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        CoroutineScope(Dispatchers.IO).launch {
//            val movielist = api.getPageOfMovies(1)
//            runOnUiThread {
//                binding.apply {
//                    adapter.submitList(movielist.docs)
//                }
//            }
//        }

    }
}