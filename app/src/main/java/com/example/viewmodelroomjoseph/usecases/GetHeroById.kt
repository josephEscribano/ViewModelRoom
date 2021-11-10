package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository
import com.example.viewmodelroomjoseph.data.modelo.toHero
import com.example.viewmodelroomjoseph.domain.Hero

data class GetHeroById(val repository: HeroRepository){
    suspend fun invoke(id: Int): Hero = repository.getHeroById(id).toHero()
}
