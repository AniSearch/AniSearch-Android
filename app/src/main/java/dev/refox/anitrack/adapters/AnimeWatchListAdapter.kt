package dev.refox.anitrack.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import dev.refox.anitrack.R
import dev.refox.anitrack.database.Animes
import dev.refox.anitrack.database.AnimesDBViewModel
import dev.refox.anitrack.databinding.WatchListItemBinding
import dev.refox.anitrack.utils.Snacker

class AnimeWatchListAdapter(
    val animesDBViewModel: AnimesDBViewModel,
    val animesWatchList: MutableList<Animes>,
    val context: Context
) : RecyclerView.Adapter<AnimeWatchListAdapter.AnimeDBViewHolder>() {

    class AnimeDBViewHolder(val binding: WatchListItemBinding, val context: Context): RecyclerView.ViewHolder(binding.root) {
        val animeDBPic: ImageView = itemView.findViewById(R.id.ivAnimeWatchPic)
        val animeDBName: TextView = itemView.findViewById(R.id.tvWatchAnimeName)
        val animeDBEpisodes: TextView = itemView.findViewById(R.id.tvWatchEpisodes)
        val animeDBStatus: TextView = itemView.findViewById(R.id.tvWatchStatus)
        val animeDBNoOfEpisodes: TextView = itemView.findViewById(R.id.tvNoOfEpidoes)
        val btnSubEpisodes: MaterialCardView = itemView.findViewById(R.id.btnSubEpisodes)
        val btnAddEpisodes: MaterialCardView = itemView.findViewById(R.id.btnAddEpisodes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeDBViewHolder {
     val layoutInflater = LayoutInflater.from(parent.context)
        val binding = WatchListItemBinding.inflate(layoutInflater, parent, false)
        return AnimeDBViewHolder(binding, parent.context)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AnimeDBViewHolder, position: Int) {

        val animeItem = animesWatchList[position]

        holder.animeDBName.text = animeItem.name
        holder.animeDBEpisodes.text = "Episodes: " + animeItem.episodes.toString()
        holder.animeDBStatus.text = "Status: " + animeItem.status
        holder.animeDBNoOfEpisodes.text = animeItem.noOfEpisodes.toString()
        holder.binding.tvWatchSeason.text = "Season: " + animeItem.season
        Picasso.get().load(animeItem.url).into(holder.animeDBPic)

        holder.btnAddEpisodes.setOnClickListener {
            if(animeItem.noOfEpisodes >= animeItem.episodes){
                Snacker(it,"Maximum Episodes reached").error()
            } else {
                animeItem.noOfEpisodes += 1
                animesDBViewModel.updateAnimes(animeItem)
                holder.animeDBNoOfEpisodes.text = animeItem.noOfEpisodes.toString()
            }

        }

        holder.btnSubEpisodes.setOnClickListener {
            if(holder.animeDBNoOfEpisodes.text.toString() == "0"){
                Snacker(it,"Episodes cannot be in negative").error()
            } else {
                animeItem.noOfEpisodes -= 1
                animesDBViewModel.updateAnimes(animeItem)
                holder.animeDBNoOfEpisodes.text = animeItem.noOfEpisodes.toString()
            }

        }

        holder.binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete?")
                .setPositiveButton("Yes") { dialog, _ ->
                    animesDBViewModel.deleteAnimes(animeItem)
                    Snacker(it, "Anime Deleted").success()
                }
                .setNegativeButton("No") { dialog, _ ->
                    Snacker(it, "Anime Not Deleted").warning()
                    animesDBViewModel.updateAnimes(animeItem)
                }
                .setCancelable(false)
                .show()


        }

    }


    fun getAnimeList() = animesWatchList

    override fun getItemCount(): Int {
        return animesWatchList.size
    }
}

