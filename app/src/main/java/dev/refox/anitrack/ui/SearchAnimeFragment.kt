package dev.refox.anitrack.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.refox.anitrack.adapters.AnimeTopSearchAdapter
import dev.refox.anitrack.databinding.FragmentSearchAnimeBinding
import dev.refox.anitrack.models.topAnimeModel.TopAnime
import dev.refox.anitrack.networking.Repository
import dev.refox.anitrack.viewmodels.AnimeViewModel
import dev.refox.anitrack.viewmodels.AnimeViewModelFactory

private lateinit var animeViewModel: AnimeViewModel

class SearchAnimeFragment : Fragment() {
    private var _binding: FragmentSearchAnimeBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var animeList: ArrayList<TopAnime>

    private val repository: Repository by lazy {
        Repository()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchAnimeBinding.inflate(inflater, container,false)

        animeViewModel = ViewModelProvider(this, AnimeViewModelFactory(repository))[AnimeViewModel::class.java]

        animeList = arrayListOf<TopAnime>()

        animeViewModel.getTopAnime()

        binding.searchRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.searchRecyclerView.adapter = AnimeTopSearchAdapter(requireActivity(),animeList)

        animeViewModel.topResponse.observe(viewLifecycleOwner, Observer{
            Log.d("debz", it.body()!!.data.get(1).title)


        })

        binding.btnSearch.setOnClickListener {
//            var query: String = binding.searchEditText.text.toString()
//
//            if(query.isNotEmpty()){
//                animeViewModel.getAnimeSearch(query)
//            }

        }



        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding=FragmentSearchAnimeBinding.bind(view)
//
//        animeViewModel = ViewModelProvider(this, AnimeViewModelFactory(repository))[AnimeViewModel::class.java]
//
//        animeList = arrayListOf<TopAnime>()
//
//        animeViewModel.getTopAnime()
//
//        binding.searchRecyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
//        binding.searchRecyclerView.adapter = AnimeTopSearchAdapter(context,
//            animeList
//        )
//
//        animeViewModel.topResponse.observe(viewLifecycleOwner, Observer{
//            Log.d("debz", it.body()!!.data.get(1).title)
//
//
//        })
//
//        binding.btnSearch.setOnClickListener {
////            var query: String = binding.searchEditText.text.toString()
////
////            if(query.isNotEmpty()){
////                animeViewModel.getAnimeSearch(query)
////            }
//
//            animeViewModel.getTopAnime()
//            binding.searchRecyclerView.adapter = AnimeTopSearchAdapter(activity,animeList)
//        }
//    }

}