package com.example.valosneakpeek.ui.store.featured

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.valosneakpeek.adapter.featured.SkinCollectionItemAdapter
import com.example.valosneakpeek.databinding.FragmentFeaturedBinding
import com.example.valosneakpeek.preferences.PreferenceManager
import com.example.valosneakpeek.ui.base.BaseFragment
import com.example.valosneakpeek.ui.store.StoreViewModel
import timber.log.Timber


class FeaturedFragment : BaseFragment<FeaturedViewModel>(), FeaturedNavigator {
    private lateinit var fragmentFeaturedBinding: FragmentFeaturedBinding
    private var viewModel: FeaturedViewModel? = null
    private val BUNDLE_BANNER_URL =
        "https://media.valorant-api.com/bundles/1ba50cf0-46dd-848f-13a9-dc92fb0a3e3b/displayicon.png"
    private var skinItemAdapter: SkinCollectionItemAdapter? = null
    private val preferenceManager: PreferenceManager = PreferenceManager()

    override fun tag(): String = FeaturedFragment::class.java.simpleName


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.fragmentFeaturedBinding = FragmentFeaturedBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return this.fragmentFeaturedBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider.NewInstanceFactory().create(FeaturedViewModel::class.java)

        if (savedInstanceState == null) {
            setUpView()
        }


    }

    override fun onStop() {
        super.onStop()
        this.viewModel?.countDownLiveData?.observe(requireActivity()) { timer ->
            preferenceManager.setCountDownBanner(timer.milliSec)
        }
    }


    override fun setUpView() {
        showLoading()
        val sharedViewModel = ViewModelProvider(requireActivity()).get(StoreViewModel::class.java)

        skinItemAdapter = SkinCollectionItemAdapter(requireActivity())
        this.fragmentFeaturedBinding.featuredRecyclerView.apply {
            adapter = skinItemAdapter
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }

        Glide.with(requireActivity())
            .load(BUNDLE_BANNER_URL)
            .into(this.fragmentFeaturedBinding.featuredImageFeatured)


        sharedViewModel.weaponsLiveData.observe(viewLifecycleOwner, Observer { weaponsList ->
            Timber.d("weaponsList ->x $weaponsList")
            this.fragmentFeaturedBinding.featuredTextCollectionName.text = "ELDERFLAME COLLECTION"
        })
//

        sharedViewModel.collectionLiveData.observe(viewLifecycleOwner, Observer { collection ->
            skinItemAdapter?.submitList(collection).let {
                hideLoading()
            }

        })

        this.viewModel?.countDownLiveData?.observe(requireActivity()) { timer ->
//            Timber.d("--> ${timer.milliSec}")
            this.fragmentFeaturedBinding.featuredTextTimeCount.text = timer.countDownFormat

        }


    }

    override fun initial() {
        TODO("Not yet implemented")
    }


    override fun showLoading() {
        this.fragmentFeaturedBinding.featuredLottieAnimationView.visibility = View.VISIBLE

    }

    override fun hideLoading() {
        this.fragmentFeaturedBinding.featuredLottieAnimationView.visibility = View.GONE
    }


}