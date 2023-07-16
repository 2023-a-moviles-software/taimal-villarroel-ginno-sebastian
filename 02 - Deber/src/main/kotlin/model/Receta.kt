package model

data class Receta(
    val idReceta: Int,
    val nombreReceta: String,
    val descripcionReceta: String,
    val tiempoPreparacion: Int,
    val dificultad: String,
    val porciones: Int,
    val ingredientes: List<Ingrediente>
)
