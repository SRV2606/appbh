package com.example.appbharat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.appbharat.databinding.ItemRecyclerCardBinding
import com.example.practice.base.BaseViewHolder

class MainAdapter(
    private val itemClickListener: (ServerMemes.Data.Meme) -> Unit,
    private val context: Context
) : androidx.recyclerview.widget.ListAdapter<ServerMemes.Data.Meme, BaseViewHolder<*>>(DIFF_CALLBACK) {


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ServerMemes.Data.Meme>() {
            override fun areItemsTheSame(
                oldItem: ServerMemes.Data.Meme,
                newItem: ServerMemes.Data.Meme
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ServerMemes.Data.Meme,
                newItem: ServerMemes.Data.Meme
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        return MainViewHolder(
            ItemRecyclerCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), context
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as MainViewHolder).setItem(
            currentList[position],
            itemClickListener
        )
    }

    override fun getItemViewType(position: Int): Int {

        return super.getItemViewType(position)
    }
}