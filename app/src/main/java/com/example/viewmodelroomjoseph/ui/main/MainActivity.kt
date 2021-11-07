package com.example.viewmodelroomjoseph.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.HeroRoomDatabase
import com.example.viewmodelroomjoseph.databinding.ActivityMainBinding
import com.example.viewmodelroomjoseph.domain.Comic
import com.example.viewmodelroomjoseph.domain.Elements
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.domain.Serie
import com.example.viewmodelroomjoseph.usecases.DeleteHero
import com.example.viewmodelroomjoseph.usecases.GetHeroes
import com.example.viewmodelroomjoseph.usecases.InsertHero
import com.example.viewmodelroomjoseph.usecases.UpdateHero
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var heroAdapter: HeroAdapter
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            GetHeroes(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            InsertHero(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            UpdateHero(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            DeleteHero(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao()))
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        heroAdapter = HeroAdapter()
        binding.rvHeroes.adapter = heroAdapter

        binding.button.setOnClickListener {
            val element = listOf(Elements(0,Comic("comic1",0), Serie("serie1",0)))
            val String = "heroe"
            var numero = 0
            viewModel.insertHero(Hero(numero++,"Heroe de prueba",LocalDate.now(),"heroe3",element))
            viewModel.getHeroes()
        }

        viewModel.heroes.observe(this,{heroes ->
            heroAdapter.submitList(heroes)

        })

        viewModel.getHeroes()



    }
}

