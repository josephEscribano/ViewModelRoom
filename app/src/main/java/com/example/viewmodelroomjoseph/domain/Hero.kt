package com.example.viewmodelroomjoseph.domain


import java.io.Serializable
import java.time.LocalDate
data class Hero(
    val id: Int,
    val description: String,
    val date: LocalDate,
    val name: String,
    val series: List<Serie>?,
    val comics: List<Comic>?
): Serializable
