package com.example.examen_crud.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int,
    var recipeName: String,
    var recipeDescription: String,
    var preparationTime: Int,
    var difficulty: String,
    var servings: Int
): Serializable