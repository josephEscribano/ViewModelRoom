package com.example.viewmodelroomjoseph.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.sax.Element
import androidx.activity.viewModels
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.HeroRoomDatabase
import com.example.viewmodelroomjoseph.databinding.ActivityMainBinding
import com.example.viewmodelroomjoseph.databinding.RecyclerActivityBinding
import com.example.viewmodelroomjoseph.domain.Comic
import com.example.viewmodelroomjoseph.domain.Elements
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.domain.Serie
import com.example.viewmodelroomjoseph.usecases.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var formatter: DateTimeFormatter
    private lateinit var heroAdapter: HeroAdapter
    private lateinit var name:String
    private lateinit var description: String
    private lateinit var date: LocalDate
    private lateinit var hero :Hero
    private lateinit var element : List<Elements>

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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dateButton.setOnClickListener {
            showDatePickerDialog()
        }
        binding.addButton.setOnClickListener {
            with(binding){
                description = etDescription.text.toString()
                name = etName.text.toString()

            }
            element = listOf(Elements(0,Comic("comic1",0), Serie("serie1",0)))
            hero = Hero(0,description, date,name,element)
            viewModel.insertHeroWithElements(hero)
            viewModel.getHeroes()
            val intent = Intent(this,RecyclerActivity::class.java)
            startActivity(intent)

        }



    }

    fun showDatePickerDialog() {
        val newFragment =
            DatePickerFragment.newInstance { _, year, month, day ->
                if (day > 1 && day < 10 ){
                    formatter = DateTimeFormatter.ofPattern("d/MM/yyyy")

                }else {
                    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                }
                val selectDate =  day.toString() + resources.getString(R.string.barra)+ (month + 1) + resources.getString(R.string.barra) + year
                binding.tvDate.text = selectDate

                date = LocalDate.parse(binding.tvDate.text.toString(),formatter)

            }

        newFragment.show(supportFragmentManager, resources.getString(R.string.datepicker))


    }
}

