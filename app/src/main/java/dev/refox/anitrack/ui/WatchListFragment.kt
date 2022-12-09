package dev.refox.anitrack.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.refox.anitrack.adapters.AnimeWatchListAdapter
import dev.refox.anitrack.database.*
import dev.refox.anitrack.databinding.FragmentWatchListBinding


class WatchListFragment : Fragment() {

    private lateinit var animesDBViewModel: AnimesDBViewModel
    lateinit var animeWatchListAdapter: AnimeWatchListAdapter
    private var _binding: FragmentWatchListBinding? = null
    private val binding
        get() = _binding!!


    private val database by lazy {
        AnimesRoomDatabase.getAnimesDatabase(requireContext())
    }

    private val animesRepository: AnimesRepository by lazy {
        AnimesRepository(database.animesDao())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWatchListBinding.inflate(inflater, container, false)

        animesDBViewModel = ViewModelProvider(this, AnimesDBViewModelFactory(animesRepository))[AnimesDBViewModel::class.java]


        animesDBViewModel.allAnimesLists.observe(viewLifecycleOwner, Observer{

            val watchData: MutableList<Animes> = it

            animeWatchListAdapter = AnimeWatchListAdapter(animesDBViewModel, watchData)
            binding.rvWatchList.adapter = animeWatchListAdapter
            binding.rvWatchList.setHasFixedSize(true)
            binding.rvWatchList.layoutManager = LinearLayoutManager(context)

            animeWatchListAdapter.notifyDataSetChanged()
        })



        return binding.root
    }

}