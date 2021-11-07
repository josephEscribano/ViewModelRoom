package com.example.viewmodelroomjoseph.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.databinding.ViewHeroBinding
import com.example.viewmodelroomjoseph.domain.Hero

class HeroAdapter: ListAdapter<Hero,HeroAdapter.ItemViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_hero,parent,false))
    }

    override fun onBindViewHolder(holder:ItemViewHolder, position: Int) = with(holder){
        val item  = getItem(position)
        bind(item)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ViewHeroBinding.bind(itemView)

        fun bind(hero:Hero) = with(binding){
            tvName.text = hero.name
            tvid.text = hero.id.toString()
        }


    }

}

class DiffCallback: DiffUtil.ItemCallback<Hero>() {
    override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
        return oldItem == newItem
    }

}

