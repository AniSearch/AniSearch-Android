package dev.refox.anitrack.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import dev.refox.anitrack.databinding.AnimeBottomSheetBinding
import dev.refox.anitrack.models.topAnimeModel.Data

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
            tvRating.text = anime.score.toString()
            tvStatus.text = anime.status
            tvEpisodes.text = anime.episodes.toString()
            tvSynopsis.text = anime.synopsis
            tvSeason.text = anime.season

            tvKnowMore.setOnClickListener {
                openCustomTab(activity, Uri.parse(anime.url))
            }

            tvWatchTrailer.setOnClickListener{
                openCustomTab(activity, Uri.parse(anime.trailer.url))
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