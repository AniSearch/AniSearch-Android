package dev.refox.anitrack.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.refox.anitrack.R
import dev.refox.anitrack.models.topAnimeModel.Data


class AnimeTopSearchAdapter(
    val animeList: MutableList<Data>
) : RecyclerView.Adapter<AnimeTopSearchAdapter.AnimeViewHolder>() {

    var onItemClick : ((Data) -> Unit)? = null
    var onItemLongClick : ((Data) -> Unit)? = null

    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animePic: ImageView = itemView.findViewById(R.id.animePic)
        val animeName: TextView = itemView.findViewById(R.id.animeName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.anime_item_layout, parent, false)
        return AnimeViewHolder(itemView)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]

        holder.animeName.text = anime.title
        Picasso.get().load(anime.images.jpg.imageUrl).into(holder.animePic)

        holder.itemView.setOnClickListener(){
          onItemClick?.invoke(anime)
        }

        holder.itemView.setOnLongClickListener {
            onItemLongClick?.invoke(anime)
            true
        }
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}