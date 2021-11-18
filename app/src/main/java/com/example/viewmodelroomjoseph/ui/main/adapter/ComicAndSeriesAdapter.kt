package com.example.viewmodelroomjoseph.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.databinding.ViewComicsSeriesBinding


class ComicAndSeriesAdapter :
    ListAdapter<String, ComicAndSeriesAdapter.ItemViewHolder>(DiffComicAndSeriesCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_comics_series, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = with(holder) {
        val name = getItem(position)
        bind(name)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewComicsSeriesBinding.bind(itemView)

        fun bind(name: String) = with(binding) {
            tvName.text = name

        }


    }

}


class DiffComicAndSeriesCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem.contentEquals(newItem)
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}
