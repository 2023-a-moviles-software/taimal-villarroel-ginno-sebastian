package com.example.indriveclone.models

data class RequestCityToCity (
    val title: String,
    val paymentType: String,
    val tripType: String,
    val date: String,
    val hour: String,
    val origin: String,
    val destiny: String,
    val requestTime: String,
    val nameUser: String
)