package dev.refox.anitrack.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.card.MaterialCardView
import dev.refox.anitrack.R
import dev.refox.anitrack.adapters.AnimeTopSearchAdapter
import dev.refox.anitrack.database.*
import dev.refox.anitrack.databinding.FragmentSearchAnimeBinding
import dev.refox.anitrack.models.topAnimeModel.Data
import dev.refox.anitrack.networking.Repository
import dev.refox.anitrack.utils.Snacker
import dev.refox.anitrack.viewmodels.AnimeViewModel
import dev.refox.anitrack.viewmodels.AnimeViewModelFactory

private lateinit var animeViewModel: AnimeViewModel
private lateinit var animesDBViewModel: AnimesDBViewModel
private lateinit var animeAdapter: AnimeTopSearchAdapter

@Suppress("DEPRECATION")
class SearchAnimeFragment : Fragment() {
    private var _binding: FragmentSearchAnimeBinding? = null
    private val binding
        get() = _binding!!


    private val repository: Repository by lazy {
        Repository()
    }

    private val database by lazy {
        AnimesRoomDatabase.getAnimesDatabase(requireContext())
    }

    private val animesRepository: AnimesRepository by lazy {
        AnimesRepository(database.animesDao())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchAnimeBinding.inflate(inflater, container, false)

        animeViewModel =
            ViewModelProvider(this, AnimeViewModelFactory(repository))[AnimeViewModel::class.java]

        animesDBViewModel = ViewModelProvider(
            this,
            AnimesDBViewModelFactory(animesRepository)
        )[AnimesDBViewModel::class.java]

        animeViewModel.getTopAnime()

        var loadProgress = binding.shimmerEffect



        animeViewModel.topResponse.observe(viewLifecycleOwner, Observer {
            val data: MutableList<Data> = it.body()!!.data as MutableList<Data>

            loadProgress.visibility = View.GONE

            animeAdapter = AnimeTopSearchAdapter(data)
            animeAdapter.notifyDataSetChanged()
            binding.searchRecyclerView.setHasFixedSize(true)
            binding.searchRecyclerView.adapter = animeAdapter
            binding.searchRecyclerView.layoutManager = GridLayoutManager(context, 2)
            animeAdapter.notifyDataSetChanged()

            animeAdapter.onItemClick = {
                val dialog = AnimeDetailsBottomSheet(it)
                dialog.setCancelable(true)
                dialog.show(parentFragmentManager, "AnimeBottomSheetDialog")
            }

            animeAdapter.onItemLongClick = {

                val animeData = Animes(
                    name = it.title,
                    episodes = it.episodes,
                    status = it.status,
                    season = it.season,
                    url = it.images.jpg.imageUrl,
                    noOfEpisodes = 0
                )
                animeData.id = System.currentTimeMillis()

                val longPressDialogBinding =
                    layoutInflater.inflate(R.layout.add_to_lib_dialog, null)
                val longPressDialog = Dialog(requireContext())

                longPressDialog.setContentView(longPressDialogBinding)
                longPressDialog.setCancelable(true)
                longPressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                longPressDialog.show()

                val btnAdd = longPressDialogBinding.findViewById<MaterialCardView>(R.id.btnAdd)
                btnAdd.setOnClickListener {
                    animesDBViewModel.insertAnimes(animeData)
                    Snacker(it, "Anime added to your WatchList").success()
                    longPressDialog.dismiss()
                }
            }

        })

        binding.btnSearch.setOnClickListener {

            loadProgress.visibility = View.VISIBLE

            var queryString: String = binding.searchEditText.text.toString()

            if (queryString.isNotEmpty()) {
                animeViewModel.getAnimeSearch(queryString)
            }

            animeViewModel.searchResponse.observe(viewLifecycleOwner, Observer {
                val sData: MutableList<Data> = it.body()!!.data as MutableList<Data>

                loadProgress.visibility = View.GONE

                animeAdapter = AnimeTopSearchAdapter(sData)

                var adapter = animeAdapter

                adapter.notifyDataSetChanged()
                binding.searchRecyclerView.setHasFixedSize(true)
                binding.searchRecyclerView.adapter = adapter
                binding.searchRecyclerView.layoutManager = GridLayoutManager(context, 2)
                adapter.notifyDataSetChanged()

                animeAdapter.onItemClick = {
                    val dialog = AnimeDetailsBottomSheet(it)
                    dialog.setCancelable(true)
                    dialog.show(parentFragmentManager, "AnimeBottomSheetDialog")
                }

                animeAdapter.onItemLongClick = {

                    val animeData = Animes(
                        name = it.title,
                        episodes = it.episodes,
                        status = it.status,
                        season = it.season,
                        url = it.images.jpg.imageUrl,
                        noOfEpisodes = 0
                    )
                    animeData.id = System.currentTimeMillis()

                    val longPressDialogBinding =
                        layoutInflater.inflate(R.layout.add_to_lib_dialog, null)
                    val longPressDialog = Dialog(requireContext())

                    longPressDialog.setContentView(longPressDialogBinding)
                    longPressDialog.setCancelable(true)
                    longPressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    longPressDialog.show()

                    val btnAdd = longPressDialogBinding.findViewById<MaterialCardView>(R.id.btnAdd)
                    btnAdd.setOnClickListener {
                        animesDBViewModel.insertAnimes(animeData)
                        Toast.makeText(context, "Added Anime to your library", Toast.LENGTH_SHORT)
                            .show()
                        longPressDialog.dismiss()
                    }
                }

            })

        }
        return binding.root
    }


}