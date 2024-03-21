package com.example.valosneakpeek.adapter.offers

import androidx.recyclerview.widget.DiffUtil
import com.example.valosneakpeek.model.weapons.Skin

class SkinOfferDiffUtil:DiffUtil.ItemCallback<Skin> (){
    override fun areItemsTheSame(oldItem: Skin, newItem: Skin): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Skin, newItem: Skin): Boolean = oldItem.uuid == newItem.uuid

}