package com.example.viewmodelroomjoseph.ui.main.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.ui.main.viewmodel.utils.ConstantesViewModel
import com.example.viewmodelroomjoseph.usecases.DeleteHero
import com.example.viewmodelroomjoseph.usecases.GetHeroes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecyclerViewModel @Inject constructor(
    private val getHeroes: GetHeroes,
    private val deleteHero: DeleteHero
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

    fun deleteHero(hero: Hero) {
        viewModelScope.launch {
            try {
                deleteHero.invoke(hero)
                getHeroes()
            } catch (e: Exception) {
                Log.e(ConstantesViewModel.ERROR_DELETE, e.message, e)
            }

        }
    }
}

