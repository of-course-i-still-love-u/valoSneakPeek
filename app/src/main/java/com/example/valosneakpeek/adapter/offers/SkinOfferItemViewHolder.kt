package com.example.valosneakpeek.adapter.offers

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginRight
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valosneakpeek.databinding.ItemFeaturedCollectionBinding
import com.example.valosneakpeek.databinding.ItemOffersBinding
import com.example.valosneakpeek.model.weapons.Skin
import kotlin.random.Random

class SkinOfferItemViewHolder(private val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val itemBinding get() = ItemOffersBinding.bind(itemView)
    fun initView(skin: Skin, position: Int) {
        val splitName = Regex(" ").split(skin.displayName)
        itemBinding.itemOfferTextTitle.text = splitName[1]
        itemBinding.itemOfferTextSubTitle.text = splitName[0]
        itemBinding.itemOfferTextVpCoin.text = getRandomPrice(Random)
        Glide.with(context).load(skin.displayIcon).into(itemBinding.itemOfferImageGun);

            (itemBinding.itemOfferCardView.layoutParams as ViewGroup.MarginLayoutParams).apply {
              if (position % 2 == 0) {
                  rightMargin = 50

                }
            }


    }

    private fun getRandomPrice(random: Random): String {
        return random.nextInt(1200, 3000).toString()
            .reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()

    }


}