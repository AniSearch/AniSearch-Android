package dev.refox.anitrack.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.card.MaterialCardView
import dev.refox.anitrack.R
import dev.refox.anitrack.R.id.btnAddToLib
import dev.refox.anitrack.adapters.AnimeTopSearchAdapter
import dev.refox.anitrack.databinding.FragmentSearchAnimeBinding
import dev.refox.anitrack.models.topAnimeModel.Data
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

            animeAdapter = AnimeTopSearchAdapter(data)
            animeAdapter.notifyDataSetChanged()
            binding.searchRecyclerView.setHasFixedSize(true)
            binding.searchRecyclerView.adapter = animeAdapter
            binding.searchRecyclerView.layoutManager = GridLayoutManager(context, 2)
            animeAdapter.notifyDataSetChanged()

            animeAdapter.onItemClick = {
                val dialog = AnimeDetailsBottomSheet(it)

                val bottomSheetView = inflater.inflate(R.layout.anime_bottom_sheet, null)
                val btnAdd = bottomSheetView.findViewById<MaterialCardView>(btnAddToLib)

                btnAdd.setOnClickListener {
                    Toast.makeText(requireContext(), "Adding Anime to Library", Toast.LENGTH_SHORT).show()
                }

                dialog.setCancelable(true)
                dialog.show(parentFragmentManager,"AnimeBottomSheetDialog")
            }

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

                var adapter = animeAdapter

                adapter.notifyDataSetChanged()
                binding.searchRecyclerView.setHasFixedSize(true)
                binding.searchRecyclerView.adapter = adapter
                binding.searchRecyclerView.layoutManager = GridLayoutManager(context, 2)
                adapter.notifyDataSetChanged()

                animeAdapter.onItemClick = {
                    val dialog = AnimeDetailsBottomSheet(it)

                    val bottomSheetView = inflater.inflate(R.layout.anime_bottom_sheet, null)
                    val btnAdd = bottomSheetView.findViewById<MaterialCardView>(btnAddToLib)

                    btnAdd.setOnClickListener {
                        Toast.makeText(requireContext(), "Adding Anime to Library", Toast.LENGTH_SHORT).show()
                    }

                    dialog.setCancelable(true)
                    dialog.show(parentFragmentManager,"AnimeBottomSheetDialog")
                }

            })

        }
        return binding.root
    }


}