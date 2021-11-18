package com.example.viewmodelroomjoseph.data.modelo

import com.example.viewmodelroomjoseph.domain.Comic
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.domain.Serie


fun HeroWithSeriesAndComics.toHero(): Hero {
    return Hero(this.heroWithComics.hero.heroId,
        this.heroWithComics.hero.description,
        this.heroWithComics.hero.date,
        this.heroWithComics.hero.heroName,
        this.series?.map { it.toSerie() },
        this.heroWithComics.comics?.map { it.toComic() })
}

fun ComicEntity.toComic(): Comic = Comic(this.comicName, this.comicId)

fun SerieEntity.toSerie(): Serie = Serie(this.serieName, this.serieid)

fun Hero.toHeroWithComics(): HeroWithComics =
    HeroWithComics(this.toHeroEntity(), this.comics?.map { it.toComicEntity() })

fun Hero.toHeroWithSeriesAndComics(): HeroWithSeriesAndComics =
    HeroWithSeriesAndComics(this.toHeroWithComics(), this.series?.map { it.toSerieEntity() })

fun Hero.toHeroEntity(): HeroEntity = HeroEntity(this.id, this.description, this.date, this.name)

fun Comic.toComicEntity(elementId: Int = 0): ComicEntity =
    ComicEntity(this.name, elementId, this.id)

fun Serie.toSerieEntity(elementId: Int = 0): SerieEntity =
    SerieEntity(this.name, elementId, this.id)