package ix.fd.gekinavi.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ix.fd.gekinavi.databinding.MovieStickerBinding
import ix.fd.gekinavi.R

class MovieAdapter: ListAdapter<Movie, MovieAdapter.Holder>(Comparator()) {
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = MovieStickerBinding.bind(view)

        fun bind(movie: Movie) =with(binding){
            stickerLabel.text = movie.name
            stickerYear.text = movie.year.toString()
        }

    }

    class Comparator: DiffUtil.ItemCallback<Movie>() {
        override fun areContentsTheSame(oldItem: Movie,newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Movie,newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_sticker, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder,position: Int) {
        holder.bind(getItem(position))
    }

}