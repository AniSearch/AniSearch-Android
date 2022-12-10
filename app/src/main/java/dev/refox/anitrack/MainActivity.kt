package dev.refox.anitrack

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.refox.anitrack.databinding.ActivityMainBinding
import dev.refox.anitrack.ui.AnimeDetailsBottomSheet
import dev.refox.anitrack.ui.SearchAnimeFragment
import dev.refox.anitrack.ui.WatchListFragment
import dev.refox.anitrack.utils.Snacker

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        replaceFragment(SearchAnimeFragment())


        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.search -> replaceFragment(SearchAnimeFragment())
                R.id.favourites -> replaceFragment(WatchListFragment())

                else -> {}

            }
            true
        }

    }



    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}