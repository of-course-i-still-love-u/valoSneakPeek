package com.example.valosneakpeek.adapter.offers

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.valosneakpeek.model.weapons.Skin
import android.view.LayoutInflater
import com.example.valosneakpeek.R

class SkinOfferItemAdapter(private val context: Context) :
    ListAdapter<Skin, SkinOfferItemViewHolder>(SkinOfferDiffUtil()) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from((context))
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinOfferItemViewHolder {
        val view = layoutInflater.inflate(R.layout.item_offers, parent, false)
        return SkinOfferItemViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: SkinOfferItemViewHolder, position: Int) {
        val skin = getItem(position)
        holder.initView(skin,position)
    }
}