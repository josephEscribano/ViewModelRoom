package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository

data class GetSeries(val heroRepository: HeroRepository){
    suspend fun getSeries() = heroRepository.getSeries()
}

