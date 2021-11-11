package com.example.viewmodelroomjoseph.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.databinding.ViewComicsSeriesBinding
import com.example.viewmodelroomjoseph.domain.Comic


data class ComicAdapter(private val list: List<String>) :
    RecyclerView.Adapter<HeroesDatosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesDatosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroesDatosViewHolder(
            layoutInflater.inflate(
                R.layout.view_comics_series,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroesDatosViewHolder, position: Int) {
        holder.render(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class HeroesDatosViewHolder( view: View) : RecyclerView.ViewHolder(view) {
    val binding = ViewComicsSeriesBinding.bind(view)
    fun render(s: String) {
        binding.tvNameComic.text = s
    }

}
