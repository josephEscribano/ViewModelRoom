package com.example.viewmodelroomjoseph.domain


import java.time.LocalDate
data class Hero(
    val id: Int,
    val description: String,
    val date: LocalDate,
    val name: String,
    val elements: List<Elements>?
)
