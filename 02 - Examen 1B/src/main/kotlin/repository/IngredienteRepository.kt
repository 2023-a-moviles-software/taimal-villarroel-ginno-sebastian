package repository

import model.Ingrediente

class IngredienteRepository {
    private val ingredientes: MutableList<Ingrediente> = mutableListOf()

    fun save(ingrediente: Ingrediente) {
        ingredientes.add(ingrediente)
    }

    fun findAll(): List<Ingrediente> {
        return ingredientes.toList()
    }

    fun findById(id: Int): Ingrediente? {
        return ingredientes.find { it.idIngrediente == id }
    }

    fun update(ingrediente: Ingrediente) {
        val index = ingredientes.indexOfFirst { it.idIngrediente == ingrediente.idIngrediente }
        if (index != -1) {
            ingredientes[index] = ingrediente
        }
    }

    fun delete(id: Int) {
        ingredientes.removeIf { it.idIngrediente == id }
    }
}
