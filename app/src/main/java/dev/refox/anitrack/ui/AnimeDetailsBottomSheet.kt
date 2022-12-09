package dev.refox.anitrack.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import dev.refox.anitrack.database.AnimesDBViewModel
import dev.refox.anitrack.databinding.AnimeBottomSheetBinding
import dev.refox.anitrack.models.topAnimeModel.Data
import dev.refox.anitrack.networking.Repository
import dev.refox.anitrack.viewmodels.AnimeViewModel
import dev.refox.anitrack.viewmodels.AnimeViewModelFactory


class AnimeDetailsBottomSheet(val anime: Data): BottomSheetDialogFragment(){

    companion object {
        const val TAG ="AnimeBottomSheetDialogFragment"
    }

    lateinit var binding: AnimeBottomSheetBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val animeInflater = LayoutInflater.from(requireContext())
        binding = AnimeBottomSheetBinding.inflate(animeInflater)

        binding.apply{
            Picasso.get().load(anime.images.jpg.imageUrl).into(ivAnimePic)
            tvAnimeName.text = anime.title
            tvRating.text = "Rating: " + anime.score.toString()
            tvStatus.text = "Status: " + anime.status
            tvEpisodes.text = "Episodes: " + anime.episodes.toString()
            tvSynopsis.text = anime.synopsis
            tvSeason.text = "Season: " + anime.season

            tvKnowMore.setOnClickListener {
                openCustomTab(activity, Uri.parse(anime.url))
            }

            tvWatchTrailer.setOnClickListener{

                var url = anime.trailer.url

                if(url.isNullOrEmpty()){
                    Toast.makeText(context, "Trailer not provided by MyAnimeList", Toast.LENGTH_SHORT).show()

                } else {
                    openCustomTab(activity, Uri.parse(anime.trailer.url))
                }


            }
        }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun openCustomTab(activity: FragmentActivity?, url: Uri){
        val builder = CustomTabsIntent.Builder()
        builder.setShowTitle(true)
        builder.build().launchUrl(requireActivity(),url)
    }

}

