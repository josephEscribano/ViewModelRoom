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
import java.time.format.DateTimeFormatter

data class HeroAdapter(private val viewModel: MainViewModel,
                       private val showData: (Hero) -> Unit
                       ): ListAdapter<Hero,HeroAdapter.ItemViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_hero,parent,false))
    }

    override fun onBindViewHolder(holder:ItemViewHolder, position: Int) = with(holder){
        val item  = getItem(position)
        bind(item,viewModel,showData)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ViewHeroBinding.bind(itemView)

        private lateinit var formatter: DateTimeFormatter
        fun bind(hero: Hero, viewModel: MainViewModel, showData: (Hero) -> Unit) = with(binding){
            id.text = hero.id.toString()
            name.text = hero.name
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            date.text = formatter.format(hero.date).toString()
            buttonDelete.setOnClickListener {
                viewModel.deleteHero(hero)
                viewModel.getHeroes()
            }

            buttonView.setOnClickListener {
                val heroDB = viewModel.getHeroById(hero.id)
                showData(heroDB)
            }

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

