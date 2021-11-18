package com.example.viewmodelroomjoseph.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.ui.main.viewmodel.utils.ConstantesViewModel
import com.example.viewmodelroomjoseph.usecases.GetHeroes
import com.example.viewmodelroomjoseph.usecases.InsertHeroWithSeriesAndComics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getHeroes: GetHeroes,
    private val insertHeroWithSeriesAndComics: InsertHeroWithSeriesAndComics,
) : ViewModel() {

    private val _heroes = MutableLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>> get() = _heroes


    fun getHeroes() {
        viewModelScope.launch {
            try {
                _heroes.value = getHeroes.invoke()
            }catch (e:Exception){
                Log.e(ConstantesViewModel.ERROR_SELECT, e.message, e)
            }

        }

    }

    fun insertHeroWithSeruesAndComics(hero: Hero) {
        viewModelScope.launch {
            try {
                insertHeroWithSeriesAndComics.invoke(hero)
                getHeroes()
            } catch (e: Exception) {
                Log.e(ConstantesViewModel.ERROR_INSERTAR, e.message, e)
            }
        }

    }

}


