package repository

import model.Receta

class RecetaRepository {
    private val recetas: MutableList<Receta> = mutableListOf()

    fun save(receta: Receta) {
        recetas.add(receta)
    }

    fun findAll(): List<Receta> {
        return recetas.toList()
    }

    fun findById(id: Int): Receta? {
        return recetas.find { it.idReceta == id }
    }

    fun update(receta: Receta) {
        val index = recetas.indexOfFirst { it.idReceta == receta.idReceta }
        if (index != -1) {
            recetas[index] = receta
        }
    }

    fun delete(id: Int) {
        recetas.removeIf { it.idReceta == id }
    }


}
