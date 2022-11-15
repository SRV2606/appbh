package com.example.appbharat

import android.content.Context
import com.bumptech.glide.Glide
import com.example.appbharat.databinding.ItemRecyclerCardBinding
import com.example.practice.base.BaseViewHolder

class MainViewHolder(
    private val binding: ItemRecyclerCardBinding,
    private val context: Context,

    ) : BaseViewHolder<Meme>(binding) {

    override fun setItem(data: Meme?, itemClickListener: (Meme) -> Unit) {
        data?.let {
            Glide.with(context).load(it.url).into(binding.memeImageIV)
            binding.memeTextTV.text = it.name
        }
    }

}