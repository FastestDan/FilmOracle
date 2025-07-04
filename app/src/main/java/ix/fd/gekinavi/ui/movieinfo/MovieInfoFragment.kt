package ix.fd.gekinavi.ui.movieinfo

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ix.fd.gekinavi.R

class MovieInfoFragment : Fragment() {

    companion object {
        fun newInstance() = MovieInfoFragment()
    }

    private val viewModel: MovieInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }
}