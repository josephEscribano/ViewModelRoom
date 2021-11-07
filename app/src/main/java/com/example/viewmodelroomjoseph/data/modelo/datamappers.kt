package com.example.viewmodelroomjoseph.data.modelo

import com.example.viewmodelroomjoseph.domain.Comic
import com.example.viewmodelroomjoseph.domain.Elements
import com.example.viewmodelroomjoseph.domain.Hero
import com.example.viewmodelroomjoseph.domain.Serie



fun HeroWithElements.toHero(): Hero{
    return  Hero(this.hero.heroId,this.hero.description,this.hero.date,this.hero.heroName,
            this.elements?.map {it.toElements()})
}
fun HeroEntity.toHero() : Hero = Hero(this.heroId,this.description,this.date,this.heroName,null)

fun ElementsEntity.toElements(): Elements = Elements(this.elementId,this.comic.toComic(),this.serie.toSerie())

fun ComicEntity.toComic(): Comic = Comic(this.comicName,this.comicId)

fun SerieEntity.toSerie(): Serie = Serie(this.serieName,this.serieid)

fun Hero.toHeroWithElements(): HeroWithElements = HeroWithElements(this.toHeroEntity(),this.elements?.map { it.toElementEntity() })

fun Hero.toHeroEntity(): HeroEntity = HeroEntity(this.id,this.description,this.date,this.name)

fun Elements.toElementEntity(heroId:Int = 0): ElementsEntity = ElementsEntity(this.id,heroId,this.comic.toComicEntity(),this.serie.toSerieEntity())

fun Comic.toComicEntity(elementId: Int = 0): ComicEntity = ComicEntity(this.name,elementId,this.id)

fun Serie.toSerieEntity(elementId: Int = 0): SerieEntity = SerieEntity(this.name,elementId,this.id)