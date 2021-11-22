package com.example.viewmodelroomjoseph.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.databinding.ActivityMainBinding
import com.example.viewmodelroomjoseph.domain.Comic
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.domain.Serie
import com.example.viewmodelroomjoseph.ui.main.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var formatter: DateTimeFormatter
    private lateinit var name: String
    private lateinit var description: String
    private lateinit var date: LocalDate
    private lateinit var hero: Hero
    private lateinit var comics: MutableList<Comic>
    private lateinit var series: MutableList<Serie>

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.btGuardar -> {
                addHero()
                true
            }
            R.id.btHeroes -> {
                val intent = Intent(binding.root.context, RecyclerActivity::class.java)
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        comics = arrayListOf()
        series = arrayListOf()


        with(binding) {
            dateButton.setOnClickListener {
                showDatePickerDialog()
            }

            addComicsButton.setOnClickListener {
                addComics()
            }

            addSeriesButton.setOnClickListener {

                addSeries()
            }

        }


    }


    private fun addHero() {
        with(binding) {
            if (etName.text?.isNotEmpty() != false && etDescription.text?.isNotEmpty() != false && !tvDate.text.equals(
                    resources.getString(R.string.dateField)
                )
            ) {

                description = etDescription.text.toString()
                name = etName.text.toString()

                hero = Hero(0, description, date, name, series, comics)
                viewModel.insertHeroWithSeruesAndComics(hero)
                Snackbar.make(
                    root,
                    resources.getString(R.string.confirmarHeroe),
                    Snackbar.LENGTH_SHORT
                ).show()
                comics.clear()
                series.clear()

            } else {
                Snackbar.make(
                    root,
                    resources.getString(R.string.aviso),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun addSeries() {
        series.add(Serie(binding.etSeries.text.toString(), 0))
        Snackbar.make(
            binding.root,
            resources.getString(R.string.confirmarSerie),
            Snackbar.LENGTH_SHORT
        ).show()
        binding.etSeries.setText("")
    }

    private fun addComics() {
        comics.add(Comic(binding.etComics.text.toString(), 0))
        Snackbar.make(
            binding.root,
            resources.getString(R.string.confirmarComic),
            Snackbar.LENGTH_SHORT
        ).show()
        binding.etComics.setText("")
    }

    fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance { _, year, month, day ->
            val dayFormat = day.twoDigits()
            val monthFormat = (month + 1).twoDigits()
            val selectDate =
                dayFormat + resources.getString(R.string.barra) + monthFormat + resources.getString(
                    R.string.barra
                ) + year
            binding.tvDate.text = selectDate
            formatter = DateTimeFormatter.ofPattern(resources.getString(R.string.formato))
            date = LocalDate.parse(binding.tvDate.text.toString(), formatter)

        }

        newFragment.show(supportFragmentManager, resources.getString(R.string.datepicker))

    }

    fun Int.twoDigits() =
        if (this <= 9) "${resources.getString(R.string.cero)}$this" else this.toString()
}

