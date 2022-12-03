package dev.refox.anitrack.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.refox.anitrack.R
import dev.refox.anitrack.models.topAnimeModel.TopAnime

class AnimeTopSearchAdapter(
    val parentActivity: FragmentActivity?,
    val animeList: ArrayList<TopAnime>
) : RecyclerView.Adapter<AnimeTopSearchAdapter.AnimeViewHolder>() {

    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animePic: ImageView = itemView.findViewById(R.id.animePic)
        val animeName: TextView = itemView.findViewById(R.id.animeName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.anime_item_layout, parent, false)
        return AnimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]

        holder.animeName.text = anime.data[position].title
        Picasso.get().load(anime.data[position].images.jpg.imageUrl).into(holder.animePic)
    }

    override fun getItemCount(): Int {
        return animeList.size
    }
}