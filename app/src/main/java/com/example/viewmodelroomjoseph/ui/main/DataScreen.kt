package com.example.viewmodelroomjoseph.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.HeroRoomDatabase
import com.example.viewmodelroomjoseph.databinding.ViewDataBinding
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.usecases.*
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DataScreen: AppCompatActivity() {
    private lateinit var binding: ViewDataBinding
    private lateinit var comicAdapter: ComicAdapter
    private lateinit var formatter: DateTimeFormatter

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            GetHeroes(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            InsertHeroWithSeriesAndComics(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            UpdateHero(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
            DeleteHero(HeroRepository(HeroRoomDatabase.getDatabase(this).heroDao())),
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        formatter = DateTimeFormatter.ofPattern(resources.getString(R.string.formato))
        val hero: Hero = intent.getSerializableExtra(resources.getString(R.string.heroe)) as Hero
        with(binding){
            etName.setText(hero.name)
            etDescription.setText(hero.description)
            tvDate.text = formatter.format(hero.date).toString()

            hero.comics?.let {
                rvComics.adapter = ComicAdapter(it.map { it.name })
            }

            hero.series?.let {
                rvSeries.adapter = ComicAdapter(it.map { it.name })
            }

            dateButton.setOnClickListener {
                showDatePickerDialog()
            }

            updateButton.setOnClickListener {
                if (etName.text?.isNotEmpty() != false && etDescription.text?.isNotEmpty( ) != false
                    && !tvDate.text.equals(resources.getString(R.string.dateField))){
                    with(binding){
                        hero.name = etName.text.toString()
                        hero.description = etDescription.text.toString()
                        hero.date = LocalDate.parse(tvDate.text,formatter)
                    }
                    viewModel.updateHero(hero)
                    viewModel.getHeroes()
                    Snackbar.make(binding.root,resources.getString(R.string.actualizado), Snackbar.LENGTH_SHORT).show()
                }else{
                    Snackbar.make(binding.root,resources.getString(R.string.aviso), Snackbar.LENGTH_SHORT).show()
                }

            }

            listButton.setOnClickListener {
                val intent = Intent(binding.root.context,RecyclerActivity::class.java)
                startActivity(intent)
            }

        }




    }


    fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance { _, year, month, day ->
            val dayFormat = day.twoDigits()
            val monthFormat = (month + 1).twoDigits()

            val selectDate =  dayFormat + resources.getString(R.string.barra)+ monthFormat + resources.getString(R.string.barra) + year
            binding.tvDate.text = selectDate

        }

        newFragment.show(supportFragmentManager, resources.getString(R.string.datepicker))


    }

    fun Int.twoDigits() = if (this <= 9) "0$this" else this.toString()
}
