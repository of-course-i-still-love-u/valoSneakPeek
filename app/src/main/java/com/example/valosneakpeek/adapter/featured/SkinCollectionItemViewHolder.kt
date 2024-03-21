package com.example.valosneakpeek.adapter.featured

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.valosneakpeek.databinding.ItemFeaturedCollectionBinding
import com.example.valosneakpeek.model.weapons.Skin
import kotlin.random.Random

class SkinCollectionItemViewHolder(private val context: Context, itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val itemBinding get() = ItemFeaturedCollectionBinding.bind(itemView)
    fun initView(skin: Skin, position: Int) {
        val splitName = Regex(" ").split(skin.displayName)
        itemBinding.itemFeaturedTextTitle .text = splitName[1]
        itemBinding.itemFeaturedTextSubTitle.text = splitName[0]
        itemBinding.itemFeaturedTextVpCoin.text = getRandomPrice(Random)
        Glide.with(context).load(skin.displayIcon).into(itemBinding.itemFeaturedImageGun);

    }

    private fun getRandomPrice(random: Random): String {
        return random.nextInt(1200, 3000).toString()
            .reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()

    }


}