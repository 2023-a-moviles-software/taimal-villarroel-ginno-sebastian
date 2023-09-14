package com.example.examen_crud.models

import java.io.Serializable

data class Recipe(
    val recipeId: String? = null,
    var recipeName: String? = null,
    var recipeDescription: String? = null,
    var preparationTime: Int? = null,
    var difficulty: String? = null,
    var servings: Int? = null
) : Serializable