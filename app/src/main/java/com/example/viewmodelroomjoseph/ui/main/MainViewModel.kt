package com.example.viewmodelroomjoseph.ui.main

import androidx.lifecycle.*
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.usecases.*
import kotlinx.coroutines.launch

class MainViewModel (private val getHeroes: GetHeroes,
                     private val insertHero: InsertHero,
                     private val updateHero: UpdateHero,
                     private val deleteHero: DeleteHero): ViewModel(){

    private val _heroes = MutableLiveData<List<Hero>>()
    val heroes: LiveData<List<Hero>> get() = _heroes


    fun getHeroes(){
        viewModelScope.launch {
            _heroes.value = getHeroes.invoke()
        }
    }

    fun insertHero(hero: Hero){
        viewModelScope.launch{
            insertHero.invoke(hero)
        }
    }
}

class MainViewModelFactory(private val getHeroes: GetHeroes,
                           private val insertHero: InsertHero,
                           private val updateHero: UpdateHero,
                           private val deleteHero: DeleteHero,)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(getHeroes,insertHero,updateHero,deleteHero) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}