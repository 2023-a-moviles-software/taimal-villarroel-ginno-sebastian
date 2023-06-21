package service

import model.Ingrediente
import repository.IngredienteRepository

class IngredienteService(private val ingredienteRepository: IngredienteRepository) {
    fun crearIngrediente(
        idIngrediente: Int, nombreIngrediente: String,
        cantidadIngrediente: Double, unidadMedida: String
    ) {
        val ingrediente = Ingrediente(
            idIngrediente, nombreIngrediente,
            cantidadIngrediente, unidadMedida
        )
        ingredienteRepository.save(ingrediente)
    }

    fun listarIngredientes(): List<Ingrediente> {
        return ingredienteRepository.findAll()
    }

    fun obtenerIngrediente(id: Int): Ingrediente? {
        return ingredienteRepository.findById(id)
    }

    fun actualizarIngrediente(
        idIngrediente: Int, nombreIngrediente: String,
        cantidadIngrediente: Double, unidadMedida: String
    ) {
        val ingrediente = Ingrediente(
            idIngrediente, nombreIngrediente,
            cantidadIngrediente, unidadMedida
        )
        ingredienteRepository.update(ingrediente)
    }

    fun eliminarIngrediente(id: Int) {
        ingredienteRepository.delete(id)
    }
}