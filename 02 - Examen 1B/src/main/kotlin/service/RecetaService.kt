package service

import model.Ingrediente
import model.Receta
import repository.RecetaRepository

class RecetaService(private val recetaRepository: RecetaRepository) {
    fun crearReceta(
        idReceta: Int, nombreReceta: String, descripcionReceta: String,
        tiempoPreparacion: Int, dificultad: String, porciones: Int,
        ingredientes: List<Ingrediente>
    ) {
        val receta = Receta(
            idReceta, nombreReceta, descripcionReceta, tiempoPreparacion,
            dificultad, porciones, ingredientes
        )
        recetaRepository.save(receta)
    }

    fun listarRecetas(): List<Receta> {
        return recetaRepository.findAll()
    }

    fun obtenerReceta(id: Int): Receta? {
        return recetaRepository.findById(id)
    }

    fun actualizarReceta(
        idReceta: Int, nombreReceta: String, descripcionReceta: String,
        tiempoPreparacion: Int, dificultad: String, porciones: Int,
        ingredientes: List<Ingrediente>
    ) {
        val receta = Receta(
            idReceta, nombreReceta, descripcionReceta, tiempoPreparacion,
            dificultad, porciones, ingredientes
        )
        recetaRepository.update(receta)
    }

    fun eliminarReceta(id: Int) {
        recetaRepository.delete(id)
    }
}