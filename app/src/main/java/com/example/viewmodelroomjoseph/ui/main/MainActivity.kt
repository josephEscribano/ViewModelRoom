package com.example.viewmodelroomjoseph.ui.main

import android. content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.HeroRoomDatabase
import com.example.viewmodelroomjoseph.databinding.ActivityMainBinding
import com.example.viewmodelroomjoseph.domain.Comic
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.domain.Serie
import com.example.viewmodelroomjoseph.usecases.*
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var formatter: DateTimeFormatter
    private lateinit var name:String
    private lateinit var description: String
    private lateinit var date: LocalDate
    private lateinit var hero :Hero
    private lateinit var comics : MutableList<Comic>
    private lateinit var series : MutableList<Serie>

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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        comics = arrayListOf()
        series = arrayListOf()
        with(binding){
            dateButton.setOnClickListener {
                showDatePickerDialog()
            }

            addComicsButton.setOnClickListener {

                comics.add(Comic(binding.etComics.text.toString(),0))
                Snackbar.make(binding.root,resources.getString(R.string.confirmarComic),Snackbar.LENGTH_SHORT).show()
                binding.etComics.setText("")
            }

            addSeriesButton.setOnClickListener {

                series.add(Serie(binding.etSeries.text.toString(),0))
                Snackbar.make(binding.root,resources.getString(R.string.confirmarSerie),Snackbar.LENGTH_SHORT).show()
                binding.etSeries.setText("")
            }

            addButton.setOnClickListener {
                if (etName.text?.isNotEmpty() != false && etDescription.text?.isNotEmpty( ) != false && !tvDate.text.equals(resources.getString(R.string.dateField))){
                    with(binding){
                        description = etDescription.text.toString()
                        name = etName.text.toString()
                    }

                    hero = Hero(0,description, date,name,series,comics)
                    viewModel.insertHeroWithSeruesAndComics(hero)
                    Snackbar.make(binding.root,resources.getString(R.string.confirmarHeroe),Snackbar.LENGTH_SHORT).show()
                    comics.clear()
                    series.clear()
                    viewModel.getHeroes()
                }else{
                    Snackbar.make(binding.root,resources.getString(R.string.aviso),Snackbar.LENGTH_SHORT).show()
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
                formatter = DateTimeFormatter.ofPattern(resources.getString(R.string.formato))
                date = LocalDate.parse(binding.tvDate.text.toString(),formatter)

            }

        newFragment.show(supportFragmentManager, resources.getString(R.string.datepicker))


    }

    fun Int.twoDigits() = if (this <= 9) "0$this" else this.toString()
}

