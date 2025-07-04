package ix.fd.gekinavi.api

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import ix.fd.gekinavi.databinding.MovieStickerBinding
import ix.fd.gekinavi.R
import ix.fd.gekinavi.ui.movieinfo.MovieInfo

class MovieAdapter(): ListAdapter<Movie, MovieAdapter.Holder>(Comparator()) {
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = MovieStickerBinding.bind(view)
        var id: Int = -5

        fun bind(movie: Movie) =with(binding){
            stickerLabel.text = movie.name
            id = movie.id
            stickerYear.text = movie.year.toString()
//            Glide.with().load(movie.poster.url).into(stickerImage)
            Picasso.get().load(movie.poster.previewUrl).into(stickerImage)
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
        holder.itemView.setOnClickListener {
            Intent(holder.itemView.context, MovieInfo::class.java)
                .putExtra("id", holder.id.toString())
        }
    }

}