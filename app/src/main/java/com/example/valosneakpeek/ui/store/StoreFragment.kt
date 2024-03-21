package com.example.valosneakpeek.ui.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.valosneakpeek.R
import com.example.valosneakpeek.adapter.StorePagerAdapter
import com.example.valosneakpeek.databinding.FragmentStoreBinding
import com.example.valosneakpeek.ui.base.BaseFragment
import com.example.valosneakpeek.ui.store.featured.FeaturedFragment
import com.example.valosneakpeek.ui.store.offers.OffersFragment
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class StoreFragment : BaseFragment<StoreViewModel>(), StoreNavigator {

    private lateinit var fragmentStoreBinding: FragmentStoreBinding
    private var viewModel: StoreViewModel? = null
    private var pagerAdapter: StorePagerAdapter? = null
    override fun tag(): String = StoreFragment::class.java.simpleName


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.fragmentStoreBinding = FragmentStoreBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return this.fragmentStoreBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider.NewInstanceFactory().create(StoreViewModel::class.java)


        if (savedInstanceState == null) {
            setUpView()
            initial()
        }


    }

    override fun setUpView() {
//        replaceFragment(FeaturedFragment())

        pagerAdapter = StorePagerAdapter(childFragmentManager, lifecycle)
        fragmentStoreBinding.storeViewPager.adapter = pagerAdapter

        TabLayoutMediator(
            fragmentStoreBinding.storeTabsLayout,
            fragmentStoreBinding.storeViewPager
        ) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.store_featured)
                1 -> tab.text = getString(R.string.store_offers)
            }

        }.attach()

    }


//    private fun replaceFragment(fragmentName: Fragment): Int {
//        childFragmentManager.beginTransaction()
//            .replace(R.id.store_frame_container, fragmentName)
//            .commit()
//        return childFragmentManager.backStackEntryCount
//
//    }


    override fun initial() {
        getWeaponsList()
    }


    override fun showLoading() {

    }

    override fun hideLoading() {
    }


    override fun getWeaponsList() {
        showLoading()
//        this.viewModel?.getWeapons()
        getWeaponsListSuccess()

    }

    override fun getWeaponsListSuccess() {
        hideLoading()
    }

    override fun getWeaponsListFailed(exception: Exception) {
        Timber.d("this Ex -> $exception")
        hideLoading()
    }

}