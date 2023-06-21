import com.google.gson.Gson
import com.google.gson.GsonBuilder
import model.Ingrediente
import model.Receta
import repository.IngredienteRepository
import repository.RecetaRepository
import service.IngredienteService
import service.RecetaService
import java.io.File
import java.util.*

val recetasGuardadas = mutableListOf<Receta>()
val ingredientesGuardados = mutableListOf<Ingrediente>()
val recetaRepository = RecetaRepository()
val ingredienteRepository = IngredienteRepository()
val recetaService = RecetaService(recetaRepository)
val ingredienteService = IngredienteService(ingredienteRepository)

fun main() {
    val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    var opcion: Int
    do {
        mostrarMenu()
        println()
        opcion = readlnOrNull()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                val receta = crearRecetaDesdeInput(ingredientesGuardados)
                recetaService.crearReceta(
                    receta.idReceta, receta.nombreReceta, receta.descripcionReceta,
                    receta.tiempoPreparacion, receta.dificultad, receta.porciones, receta.ingredientes
                )
                recetasGuardadas.add(receta)
                println("La receta ha sido creada exitosamente.")
            }

            2 -> {
                val recetas = recetaService.listarRecetas()
                if (recetas.isNotEmpty()) {
                    println("Listado de recetas:")
                    recetas.forEach { println(it) }
                } else {
                    println("No hay recetas registradas.")
                }
            }

            3 -> {
                val id = obtenerIdDesdeInput()
                val receta = recetaService.obtenerReceta(id)
                if (receta != null) {
                    println(receta)
                } else {
                    println("No se encontró ninguna receta con el ID proporcionado.")
                }
            }

            4 -> {
                val receta = crearRecetaDesdeInput(ingredientesGuardados)
                recetaService.actualizarReceta(
                    receta.idReceta, receta.nombreReceta, receta.descripcionReceta,
                    receta.tiempoPreparacion, receta.dificultad, receta.porciones, receta.ingredientes
                )
                recetasGuardadas.add(receta)
                println("La receta ha sido actualizada exitosamente.")
            }

            5 -> {
                val id = obtenerIdDesdeInput()
                recetaService.eliminarReceta(id)
                println("La receta ha sido eliminada exitosamente.")
            }
            // Ingrediente
            6 -> {
                val ingrediente = crearIngredienteDesdeInput()
                ingredienteService.crearIngrediente(
                    ingrediente.idIngrediente, ingrediente.nombreIngrediente,
                    ingrediente.cantidadIngrediente, ingrediente.unidadMedida
                )
                ingredientesGuardados.add(ingrediente)
                println("El ingrediente ha sido creado exitosamente.")
            }

            7 -> {
                val ingrediente = ingredienteService.listarIngredientes()
                if (ingrediente.isNotEmpty()) {
                    println("Listado de ingredientes:")
                    ingrediente.forEach { println(it) }
                } else {
                    println("No hay ingredientes registrados.")
                }
            }

            8 -> {
                val id = obtenerIdDesdeInput()
                val ingrediente = ingredienteService.obtenerIngrediente(id)
                if (ingrediente != null) {
                    println(ingrediente)
                } else {
                    println("No se encontró ningún ingrediente con el ID proporcionado.")
                }
            }

            9 -> {
                val ingrediente = crearIngredienteDesdeInput()
                ingredienteService.actualizarIngrediente(
                    ingrediente.idIngrediente, ingrediente.nombreIngrediente,
                    ingrediente.cantidadIngrediente, ingrediente.unidadMedida
                )
                ingredientesGuardados.add(ingrediente)
                println("El ingrediente ha sido actualizado exitosamente.")
            }

            10 -> {
                val id = obtenerIdDesdeInput()
                ingredienteService.eliminarIngrediente(id)
                println("El ingrediente ha sido eliminado exitosamente.")
            }

            11 -> {
                // Guardar cambios en un archivo .json
                val recetasJson = gson.toJson(recetasGuardadas)
                val ingredientesJson = gson.toJson(ingredientesGuardados)

                File("recetas.json").writeText(recetasJson)
                File("ingredientes.json").writeText(ingredientesJson)

                println("Los cambios han sido guardados exitosamente.")
            }

            12 -> {
                println("Hasta luego!")
            }

            else -> println("Opción no válida")
        }
    } while (opcion != 12)
}


fun mostrarMenu() {
    println("***** Menú *****")
    // Receta
    println("1. Crear receta")
    println("2. Listar recetas")
    println("3. Obtener receta por ID")
    println("4. Actualizar receta")
    println("5. Eliminar receta")
    // Ingrediente
    println("6. Crear ingrediente")
    println("7. Listar ingredientes")
    println("8. Obtener ingrediente por ID")
    println("9. Actualizar ingrediente")
    println("10. Eliminar ingrediente")
    println("11. Guardar cambios")
    println("12. Salir")
    print("Ingrese el número de la opción deseada: ")
}

fun obtenerIdDesdeInput(): Int {
    val scanner = Scanner(System.`in`)
    print("Ingrese el ID: ")
    return scanner.nextInt()
}

fun crearRecetaDesdeInput(ingredientesAlmacenados: List<Ingrediente>): Receta {
    val scanner = Scanner(System.`in`)
    println("***** Crear receta *****")
    print("Ingrese el ID de la receta: ")
    val idReceta = scanner.nextInt()
    scanner.nextLine() // Limpiar el buffer
    print("Ingrese el nombre de la receta: ")
    val nombreReceta = scanner.nextLine()
    print("Ingrese la descripción de la receta: ")
    val descripcionReceta = scanner.nextLine()
    print("Ingrese el tiempo de preparación (en minutos): ")
    val tiempoPreparacion = scanner.nextInt()
    scanner.nextLine() // Limpiar el buffer
    print("Ingrese la dificultad de la receta: ")
    val dificultad = scanner.nextLine()
    print("Ingrese el número de porciones: ")
    val porciones = scanner.nextInt()
    scanner.nextLine() // Limpiar el buffer

    val ingredientes = mutableListOf<Ingrediente>()
    var opcion: Int
    do {
        println("Seleccione un ingrediente existente o cree uno nuevo:")
        println("***** Ingredientes disponibles *****")
        ingredientesAlmacenados.forEach { println(it) }
        println("***********************************")
        println("1. Seleccionar ingrediente existente")
        println("2. Crear nuevo ingrediente")
        print("Ingrese el número de opción: ")
        opcion = scanner.nextInt()

        when (opcion) {
            1 -> {
                print("Ingrese el ID del ingrediente existente: ")
                val idIngrediente = scanner.nextInt()
                val ingredienteExistente = ingredientesAlmacenados.find { it.idIngrediente == idIngrediente }
                if (ingredienteExistente != null) {
                    ingredientes.add(ingredienteExistente)
                    println("Ingrediente existente agregado a la receta.")
                } else {
                    println("No se encontró ningún ingrediente existente con el ID proporcionado.")
                }
            }

            2 -> {
                val ingrediente = crearIngredienteDesdeInput()
                ingredientes.add(ingrediente)
                ingredienteService.crearIngrediente(
                    ingrediente.idIngrediente, ingrediente.nombreIngrediente,
                    ingrediente.cantidadIngrediente, ingrediente.unidadMedida
                )
                ingredientesGuardados.add(ingrediente)
                println("Ingrediente creado y agregado a la receta.")
            }

            else -> {
                println("Opción inválida. Por favor, selecciona una opción válida.")
            }
        }

        println("¿Desea agregar otro ingrediente a la receta? (1. Sí / 2. No)")
        opcion = scanner.nextInt()
    } while (opcion == 1)

    return Receta(idReceta, nombreReceta, descripcionReceta, tiempoPreparacion, dificultad, porciones, ingredientes)
}


fun crearIngredienteDesdeInput(): Ingrediente {
    val scanner = Scanner(System.`in`)
    println("***** Crear ingrediente *****")
    print("Ingrese el ID del ingrediente: ")
    val idIngrediente = scanner.nextInt()
    scanner.nextLine() // Limpiar el buffer
    print("Ingrese el nombre del ingrediente: ")
    val nombreIngrediente = scanner.nextLine()
    print("Ingrese la cantidad del ingrediente: ")
    val cantidadIngrediente = scanner.nextDouble()
    scanner.nextLine() // Limpiar el buffer
    print("Ingrese la unidad de medida del ingrediente: ")
    val unidadMedida = scanner.nextLine()
    return Ingrediente(idIngrediente, nombreIngrediente, cantidadIngrediente, unidadMedida)
}
