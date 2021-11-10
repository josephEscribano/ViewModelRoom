package com.example.viewmodelroomjoseph.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.data.Converters
import com.example.viewmodelroomjoseph.databinding.ViewDataBinding
import com.example.viewmodelroomjoseph.domain.Hero

class DataScreen: AppCompatActivity() {
    private lateinit var binding: ViewDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val hero: Hero = intent.getSerializableExtra(resources.getString(R.string.heroe)) as Hero
        with(binding){
            etName.setText(hero.name)
            etDescription.setText(hero.description)
            tvDate.text = hero.date.toString()

        }
    }
}