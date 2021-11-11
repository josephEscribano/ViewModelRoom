package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository

data class GetSeries(private val heroRepository: HeroRepository){
    suspend fun getSeries() = heroRepository.getSeries()
}

