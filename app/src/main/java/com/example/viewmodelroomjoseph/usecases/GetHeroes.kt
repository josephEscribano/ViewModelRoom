package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.modelo.toHero
import javax.inject.Inject

class GetHeroes @Inject constructor(private val heroesRepository: HeroRepository) {
    suspend fun invoke() = heroesRepository.getHeroes().map { it.toHero() }
}
