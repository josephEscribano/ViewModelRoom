package com.example.viewmodelroomjoseph.usecases

import com.example.viewmodelroomjoseph.data.HeroRepository

data class GetComics(val heroRepository: HeroRepository){

    suspend fun invoke() =  heroRepository.getComics()
}
