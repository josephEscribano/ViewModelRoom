package com.example.viewmodelroomjoseph.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.databinding.ViewDataBinding
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.ui.main.adapter.ComicAndSeriesAdapter
import com.example.viewmodelroomjoseph.ui.main.viewmodel.DataViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DataScreen : AppCompatActivity() {
    private lateinit var binding: ViewDataBinding
    private lateinit var formatter: DateTimeFormatter
    private lateinit var adapter: ComicAndSeriesAdapter
    private lateinit var adapterSeries: ComicAndSeriesAdapter
    private lateinit var hero: Hero
    private val viewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ViewDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ComicAndSeriesAdapter()
        adapterSeries = ComicAndSeriesAdapter()
        formatter = DateTimeFormatter.ofPattern(resources.getString(R.string.formato))
        hero = intent.getSerializableExtra(resources.getString(R.string.heroe)) as Hero
        with(binding) {
            etName.setText(hero.name)
            etDescription.setText(hero.description)
            tvDate.text = formatter.format(hero.date).toString()
            hero.comics?.let {
                rvComics.adapter = adapter
                adapter.submitList(it.map { it.name })
            }

            hero.series?.let {
                rvSeries.adapter = adapterSeries
                adapterSeries.submitList(it.map { it.name })
            }

            dateButton.setOnClickListener {
                showDatePickerDialog()
            }

            updateButton.setOnClickListener {
                updateHero()


            }

        }


    }

    private fun updateHero() {
        with(binding) {
            if (etName.text?.isNotEmpty() != false && etDescription.text?.isNotEmpty() != false
                && !tvDate.text.equals(resources.getString(R.string.dateField))
            ) {
                with(binding) {
                    hero.name = etName.text.toString()
                    hero.description = etDescription.text.toString()
                    hero.date = LocalDate.parse(tvDate.text, formatter)
                }
                viewModel.updateHero(hero)
                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.actualizado),
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.aviso),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
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

        }

        newFragment.show(supportFragmentManager, resources.getString(R.string.datepicker))


    }

    fun Int.twoDigits() =
        if (this <= 9) "${resources.getString(R.string.cero)}$this" else this.toString()
}
