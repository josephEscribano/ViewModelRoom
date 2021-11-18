package com.example.viewmodelroomjoseph.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.ui.main.viewmodel.utils.ConstantesViewModel
import com.example.viewmodelroomjoseph.usecases.GetHeroes
import com.example.viewmodelroomjoseph.usecases.UpdateHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val getHeroes: GetHeroes,
    private val updateHero: UpdateHero,
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

    fun updateHero(hero: Hero) {
        viewModelScope.launch {
            try {
                updateHero.invoke(hero)
                getHeroes()
            } catch (e: Exception) {
                Log.e(ConstantesViewModel.ERROR_UPDATE, e.message, e)
            }

        }
    }
}


