package com.example.valosneakpeek.adapter.featured

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.valosneakpeek.model.weapons.Skin
import android.view.LayoutInflater
import com.example.valosneakpeek.R

class SkinCollectionItemAdapter(private val context: Context) :
    ListAdapter<Skin, SkinCollectionItemViewHolder>(SkinCollectionDiffUtil()) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from((context))
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinCollectionItemViewHolder {
        val view = layoutInflater.inflate(R.layout.item_featured_collection, parent, false)
        return SkinCollectionItemViewHolder(context, view)
    }

    override fun onBindViewHolder(holder: SkinCollectionItemViewHolder, position: Int) {
        val skin = getItem(position)
        holder.initView(skin,position)
    }
}