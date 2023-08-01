package com.example.indriveclone.models

data class RequestCity(
    // User
    val name: String,
    val score: Double,
    val trips: String,
    val requestTime: Int,
    // Trip Detail
    val origin: String,
    val parishOrigin: String,
    val destiny: String,
    val parishDestiny: String,
    val money: Double,
    val distance: Double,
)