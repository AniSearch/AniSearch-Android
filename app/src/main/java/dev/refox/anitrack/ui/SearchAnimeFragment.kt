package dev.refox.anitrack.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.squareup.picasso.Picasso
import dev.refox.anitrack.R
import dev.refox.anitrack.adapters.AnimeTopSearchAdapter
import dev.refox.anitrack.databinding.FragmentSearchAnimeBinding
import dev.refox.anitrack.models.topAnimeModel.Data
import dev.refox.anitrack.models.topAnimeModel.TopAnime
import dev.refox.anitrack.networking.Repository
import dev.refox.anitrack.viewmodels.AnimeViewModel
import dev.refox.anitrack.viewmodels.AnimeViewModelFactory

private lateinit var animeViewModel: AnimeViewModel
private lateinit var animeAdapter: AnimeTopSearchAdapter

@Suppress("DEPRECATION")
class SearchAnimeFragment : Fragment() {
    private var _binding: FragmentSearchAnimeBinding? = null
    private val binding
        get() = _binding!!


    private val repository: Repository by lazy {
        Repository()
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

        animeViewModel.getTopAnime()




        animeViewModel.topResponse.observe(viewLifecycleOwner, Observer {
            val data: MutableList<Data> = it.body()!!.data as MutableList<Data>
            Log.d("debzdata", it.body()!!.data.toString())

            val animeAdapter: AnimeTopSearchAdapter = AnimeTopSearchAdapter(data)
            animeAdapter.notifyDataSetChanged()
            binding.searchRecyclerView.setHasFixedSize(true)
            binding.searchRecyclerView.adapter = animeAdapter
            binding.searchRecyclerView.layoutManager = GridLayoutManager(context, 2)
            animeAdapter.notifyDataSetChanged()

        })

        binding.btnSearch.setOnClickListener {

            var queryString: String = binding.searchEditText.text.toString()

            if (queryString.isNotEmpty()) {
                animeViewModel.getAnimeSearch(queryString)
            }

            animeViewModel.searchResponse.observe(viewLifecycleOwner, Observer {
                val sData: MutableList<Data> = it.body()!!.data as MutableList<Data>

                Log.d("statusCode", it.code().toString())

                animeAdapter = AnimeTopSearchAdapter(sData)


                animeAdapter.notifyDataSetChanged()
                binding.searchRecyclerView.setHasFixedSize(true)
                binding.searchRecyclerView.adapter = animeAdapter
                binding.searchRecyclerView.layoutManager = GridLayoutManager(context, 2)
                animeAdapter.notifyDataSetChanged()

                animeAdapter.onItemClick = {
                    val dialog = AnimeDetailsBottomSheet(it)

                    val view = layoutInflater.inflate(R.layout.anime_bottom_sheet, null)
                    val btnClose = view.findViewById<Button>(R.id.btnClose)
                    btnClose.setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.setCancelable(true)
                    dialog.show(parentFragmentManager,"AnimeBottomSheetDialog")

                    AnimeDetailsBottomSheet(it).apply {
                        show(requireFragmentManager(), AnimeDetailsBottomSheet.TAG)
                    }
                }
            })

        }



        return binding.root
    }


}