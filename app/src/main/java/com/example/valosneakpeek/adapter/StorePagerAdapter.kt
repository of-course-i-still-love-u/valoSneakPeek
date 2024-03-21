package com.example.valosneakpeek.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.valosneakpeek.ui.store.featured.FeaturedFragment
import com.example.valosneakpeek.ui.store.offers.OffersFragment

class StorePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val fragmentClasses: Array<Fragment> = arrayOf(
        FeaturedFragment(),
        OffersFragment(),
        )

    override fun getItemCount(): Int = fragmentClasses.size

    override fun createFragment(position: Int): Fragment = fragmentClasses[position]
}