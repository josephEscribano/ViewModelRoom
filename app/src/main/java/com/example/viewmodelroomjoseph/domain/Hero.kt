package com.example.viewmodelroomjoseph.domain


import java.io.Serializable
import java.time.LocalDate

data class Hero(
    val id: Int,
    var description: String,
    var date: LocalDate,
    var name: String,
    var series: List<Serie>?,
    var comics: List<Comic>?
) : Serializable