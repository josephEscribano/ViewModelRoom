package com.example.viewmodelroomjoseph.ui.main


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.HeroRoomDatabase
import com.example.viewmodelroomjoseph.databinding.RecyclerActivityBinding
import com.example.viewmodelroomjoseph.databinding.ViewHeroBinding
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.usecases.*

class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: RecyclerActivityBinding
    private lateinit var heroAdapter: HeroAdapter
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            GetHeroes(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            GetHeroById(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            InsertHeroWithSeriesAndComics(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            UpdateHero(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            DeleteHero(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        heroAdapter = HeroAdapter(viewModel,::showData)
        binding.rvHeroes.adapter = heroAdapter

        viewModel.heroes.observe(this,{heroes ->
            heroAdapter.submitList(heroes)

        })
        viewModel.getHeroes()

    }

    fun showData(hero: Hero) {
        val intent = Intent(this, DataScreen::class.java)
        val bundle = Bundle()
        bundle.putSerializable(resources.getString(R.string.heroe), hero)
        intent.putExtras(bundle)
        startActivity(intent)
    }


}