package com.alvarohidalgo.heroesmvvm.domain.model


data class Heroe(
    val id: String,
    val name: String,
    val photoUrl: String,
    val description: String,
    val comics: List<Comic>
)