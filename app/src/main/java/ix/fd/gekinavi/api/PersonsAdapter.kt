package ix.fd.gekinavi.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ix.fd.gekinavi.databinding.ActorStickerBinding
import ix.fd.gekinavi.R

class PersonsAdapter: ListAdapter<PersonsForMovie, PersonsAdapter.Holder>(Comparator()) {
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ActorStickerBinding.bind(view)

        fun bind(persons: PersonsForMovie) =with(binding){
            stickerLabel.text = persons.name
//            Glide.with().load(movie.poster.url).into(stickerImage)
            Picasso.get().load(persons.photo).into(stickerImage)
        }

    }

    class Comparator: DiffUtil.ItemCallback<PersonsForMovie>() {
        override fun areContentsTheSame(oldItem: PersonsForMovie,newItem: PersonsForMovie): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: PersonsForMovie,newItem: PersonsForMovie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.actor_sticker, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder,position: Int) {
        holder.bind(getItem(position))
    }

}