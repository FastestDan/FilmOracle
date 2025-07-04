package ix.fd.gekinavi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ix.fd.gekinavi.R
import ix.fd.gekinavi.api.MovieAdapter
import ix.fd.gekinavi.api.MovieApi
import ix.fd.gekinavi.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val pagenum = 5

    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var adapter: MovieAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        _binding?.movieCards?.layoutManager = GridLayoutManager(this.context, 2)
        adapter = MovieAdapter()
        _binding?.movieCards?.adapter = adapter
//
        val retrofit = Retrofit.Builder().baseUrl("https://api.kinopoisk.dev/v1.4/")
            .client(client).addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(MovieApi::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            val movielist = api.getPageOfMovies(pagenum)
            withContext(Dispatchers.Main) { adapter.submitList(movielist.docs) }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}