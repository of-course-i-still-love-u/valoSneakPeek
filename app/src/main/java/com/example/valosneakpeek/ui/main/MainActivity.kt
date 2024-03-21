package com.example.valosneakpeek.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.valosneakpeek.R
import com.example.valosneakpeek.databinding.ActivityMainBinding
import com.example.valosneakpeek.ui.store.StoreFragment
import com.example.valosneakpeek.ui.store.StoreViewModel
import com.example.valosneakpeek.ui.store.featured.FeaturedViewModel
import com.example.valosneakpeek.ui.store.offers.OffersFragment
import com.google.android.material.navigation.NavigationBarView
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var bottomNavigationBarView: NavigationBarView
    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        bottomNavigationBarView = mainBinding.mainBottomNavigation
        setContentView(mainBinding.root)
        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        val sharedViewModel = ViewModelProvider(this).get(StoreViewModel::class.java)
        bottomNavigationBarView.setOnItemSelectedListener { select ->
            when (select.itemId) {
                R.id.menu_item_home -> {
                    true
                }

                R.id.menu_item_store -> {
                    sharedViewModel.getWeapons()
                    replaceFragment(StoreFragment(), select.itemId)
                    true
                }

                R.id.menu_item_trophy -> {
                    true
                }

                R.id.menu_item_user -> {
                    true
                }


                else -> false
            }

        }


    }

    private fun replaceFragment(fragmentName: Fragment, navMenuId: Int) {
        val currentFragment = supportFragmentManager.findFragmentByTag(navMenuId.toString())

        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame_container, fragmentName, navMenuId.toString())
                .addToBackStack(navMenuId.toString())
                .commit()
        } else {
            supportFragmentManager.popBackStack(
                navMenuId.toString(),
                0
            )

        }

    }

}