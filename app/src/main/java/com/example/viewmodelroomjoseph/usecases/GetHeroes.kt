package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.modelo.toHero

data class GetHeroes(private val heroesRepository: HeroRepository){
    suspend fun invoke() = heroesRepository.getHeroes().map { it.toHero() }
}
