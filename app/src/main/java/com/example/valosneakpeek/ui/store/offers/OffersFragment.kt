package com.example.valosneakpeek.ui.store.offers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valosneakpeek.R
import com.example.valosneakpeek.adapter.offers.SkinOfferItemAdapter
import com.example.valosneakpeek.databinding.ActivityMainBinding
import com.example.valosneakpeek.databinding.FragmentOffersBinding
import com.example.valosneakpeek.model.weapons.Skin
import com.example.valosneakpeek.ui.base.BaseFragment
import com.example.valosneakpeek.ui.store.StoreViewModel
import com.example.valosneakpeek.ui.store.featured.FeaturedViewModel
import timber.log.Timber
import androidx.lifecycle.Observer


class OffersFragment : BaseFragment<OffersViewModel>(), OffersNavigator {
    private lateinit var offersFragmentBinding: FragmentOffersBinding
    override fun tag(): String = OffersFragment::class.java.simpleName
    private var skinOfferItemAdapter: SkinOfferItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.offersFragmentBinding = FragmentOffersBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return this.offersFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            setUpView()
        }


    }

    override fun setUpView() {
        showLoading()

        val sharedViewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

        skinOfferItemAdapter = SkinOfferItemAdapter(requireActivity())
        this.offersFragmentBinding.offerRecyclerView.apply {
            adapter = skinOfferItemAdapter
            layoutManager = GridLayoutManager(requireActivity(), 2)
        }

        sharedViewModel.offersLiveData.observe(viewLifecycleOwner, Observer { collection ->
            Timber.d("--->${collection}")
            skinOfferItemAdapter?.submitList(collection).let {
                hideLoading()
            }
        })
    }

    override fun initial() {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        this.offersFragmentBinding.offerLottieAnimationView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        this.offersFragmentBinding.offerLottieAnimationView.visibility = View.GONE
    }


}