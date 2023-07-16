package com.example.examen_crud.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    val ingredientId: Int,
    val ingredientName: String,
    val ingredientQuantity: Double,
    val measurementUnit: String
): Serializable