package com.example.valosneakpeek.adapter.featured

import androidx.recyclerview.widget.DiffUtil
import com.example.valosneakpeek.model.weapons.Skin

class SkinCollectionDiffUtil:DiffUtil.ItemCallback<Skin> (){
    override fun areItemsTheSame(oldItem: Skin, newItem: Skin): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Skin, newItem: Skin): Boolean = oldItem.uuid == newItem.uuid

}