package com.example.viewmodelroomjoseph.ui.main


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.HeroRoomDatabase
import com.example.viewmodelroomjoseph.databinding.RecyclerActivityBinding
import com.example.viewmodelroomjoseph.usecases.*

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: RecyclerActivityBinding
    private lateinit var heroAdapter: HeroAdapter
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            GetHeroes(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            InsertHero(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            InsertHeroWithElements(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            UpdateHero(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            DeleteHero(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        heroAdapter = HeroAdapter()
        binding.rvHeroes.adapter = heroAdapter

        viewModel.heroes.observe(this,{heroes ->
            heroAdapter.submitList(heroes)

        })


        viewModel.getHeroes()

    }
}