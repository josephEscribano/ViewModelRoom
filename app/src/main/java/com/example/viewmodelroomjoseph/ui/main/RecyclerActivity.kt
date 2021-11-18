package com.example.viewmodelroomjoseph.ui.main


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.viewmodelroomjoseph.R
import com.example.viewmodelroomjoseph.databinding.RecyclerActivityBinding
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.ui.main.adapter.HeroAdapter
import com.example.viewmodelroomjoseph.ui.main.viewmodel.RecyclerViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecyclerActivity : AppCompatActivity() {
    private lateinit var binding: RecyclerActivityBinding
    private lateinit var heroAdapter: HeroAdapter
    private val viewModel: RecyclerViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.getHeroes()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecyclerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        heroAdapter = HeroAdapter(::deleteHero, ::showData)
        binding.rvHeroes.adapter = heroAdapter

        viewModel.heroes.observe(this, { heroes ->
            heroAdapter.submitList(heroes)

        })


    }

    fun showData(hero: Hero) {
        val intent = Intent(this, DataScreen::class.java)
        val bundle = Bundle()
        bundle.putSerializable(resources.getString(R.string.heroe), hero)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun deleteHero(hero: Hero) {
        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.confirmacion))
            .setMessage(resources.getString(R.string.pregunta))
            .setNegativeButton(resources.getString(R.string.no)) { view, _ ->
                view.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.yes)) { view, _ ->
                viewModel.deleteHero(hero)

            }
            .setCancelable(false)
            .create()

        dialog.show()

    }
}