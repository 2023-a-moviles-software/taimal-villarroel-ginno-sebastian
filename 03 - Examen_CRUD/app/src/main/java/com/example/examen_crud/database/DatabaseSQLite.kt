package com.example.examen_crud.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examen_crud.models.Ingredient
import com.example.examen_crud.models.Recipe

class DatabaseSQLite(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "recipe_database.db"
        private const val DATABASE_VERSION = 1

        // Tabla Recipe
        private const val TABLE_RECIPE = "recipes"
        private const val COLUMN_RECIPE_ID = "recipe_id"
        private const val COLUMN_RECIPE_NAME = "recipe_name"
        private const val COLUMN_RECIPE_DESCRIPTION = "recipe_description"
        private const val COLUMN_PREPARATION_TIME = "preparation_time"
        private const val COLUMN_DIFFICULTY = "difficulty"
        private const val COLUMN_SERVINGS = "servings"

        // Tabla Ingredient
        private const val TABLE_INGREDIENT = "ingredients"
        private const val COLUMN_INGREDIENT_ID = "ingredient_id"
        private const val COLUMN_INGREDIENT_NAME = "ingredient_name"
        private const val COLUMN_INGREDIENT_QUANTITY = "ingredient_quantity"
        private const val COLUMN_MEASUREMENT_UNIT = "measurement_unit"

        // Relación entre Recipe e Ingredient
        private const val TABLE_RECIPE_INGREDIENT = "recipe_ingredient"
        private const val COLUMN_RECIPE_INGREDIENT_ID = "recipe_ingredient_id"
        private const val COLUMN_RECIPE_ID_FK = "recipe_id_fk"
        private const val COLUMN_INGREDIENT_ID_FK = "ingredient_id_fk"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear tabla Recipe
        val createRecipeTable = """
            CREATE TABLE $TABLE_RECIPE (
                $COLUMN_RECIPE_ID INTEGER PRIMARY KEY,
                $COLUMN_RECIPE_NAME TEXT,
                $COLUMN_RECIPE_DESCRIPTION TEXT,
                $COLUMN_PREPARATION_TIME INTEGER,
                $COLUMN_DIFFICULTY TEXT,
                $COLUMN_SERVINGS INTEGER
            )
        """
        db?.execSQL(createRecipeTable)

        // Crear tabla Ingredient
        val createIngredientTable = """
            CREATE TABLE $TABLE_INGREDIENT (
                $COLUMN_INGREDIENT_ID INTEGER PRIMARY KEY,
                $COLUMN_INGREDIENT_NAME TEXT,
                $COLUMN_INGREDIENT_QUANTITY REAL,
                $COLUMN_MEASUREMENT_UNIT TEXT
            )
        """
        db?.execSQL(createIngredientTable)

        // Crear tabla de relación Recipe-Ingredient
        val createRecipeIngredientTable = """
            CREATE TABLE $TABLE_RECIPE_INGREDIENT (
                $COLUMN_RECIPE_INGREDIENT_ID INTEGER PRIMARY KEY,
                $COLUMN_RECIPE_ID_FK INTEGER,
                $COLUMN_INGREDIENT_ID_FK INTEGER,
                FOREIGN KEY ($COLUMN_RECIPE_ID_FK) REFERENCES $TABLE_RECIPE($COLUMN_RECIPE_ID),
                FOREIGN KEY ($COLUMN_INGREDIENT_ID_FK) REFERENCES $TABLE_INGREDIENT($COLUMN_INGREDIENT_ID)
            )
        """
        db?.execSQL(createRecipeIngredientTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Eliminar tablas existentes si es necesario
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_RECIPE")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_INGREDIENT")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_RECIPE_INGREDIENT")

        // Crear tablas actualizadas
        onCreate(db)
    }

    // Operaciones CRUD para la entidad Recipe

    fun addRecipe(recipe: Recipe): Long {
        val db = writableDatabase

        val recipeValues = ContentValues().apply {
            put(COLUMN_RECIPE_ID, recipe.recipeId)
            put(COLUMN_RECIPE_NAME, recipe.recipeName)
            put(COLUMN_RECIPE_DESCRIPTION, recipe.recipeDescription)
            put(COLUMN_PREPARATION_TIME, recipe.preparationTime)
            put(COLUMN_DIFFICULTY, recipe.difficulty)
            put(COLUMN_SERVINGS, recipe.servings)
        }

        val success = db.insert(TABLE_RECIPE, null, recipeValues)
        db.close()

        return success
    }

    fun getRecipe(recipeId: Int): Recipe? {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_RECIPE WHERE $COLUMN_RECIPE_ID = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(recipeId.toString()))

        if (cursor.moveToFirst()) {
            val recipe = Recipe(
                cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPE_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_NAME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_DESCRIPTION)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_PREPARATION_TIME)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DIFFICULTY)),
                cursor.getInt(cursor.getColumnIndex(COLUMN_SERVINGS))
            )
            cursor.close()
            db.close()
            return recipe
        }

        cursor.close()
        db.close()

        return null
    }

    fun getAllRecipes(): List<Recipe> {
        val recipeList: ArrayList<Recipe> = ArrayList()
        val query = "SELECT * FROM $TABLE_RECIPE"
        val db = readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(query, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(query)
            return ArrayList()
        }

        if (cursor.moveToFirst()) {
            do {
                val recipe = Recipe(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_RECIPE_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_RECIPE_DESCRIPTION)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PREPARATION_TIME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DIFFICULTY)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_SERVINGS))
                )
                recipeList.add(recipe)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return recipeList
    }

    fun updateRecipe(recipe: Recipe): Int {
        val db = this.writableDatabase

        val recipeValues = ContentValues()
        recipeValues.put(COLUMN_RECIPE_NAME, recipe.recipeName)
        recipeValues.put(COLUMN_RECIPE_DESCRIPTION, recipe.recipeDescription)
        recipeValues.put(COLUMN_PREPARATION_TIME, recipe.preparationTime)
        recipeValues.put(COLUMN_DIFFICULTY, recipe.difficulty)
        recipeValues.put(COLUMN_SERVINGS, recipe.servings)


        val success = db.update(
            TABLE_RECIPE,
            recipeValues,
            "$COLUMN_RECIPE_ID = ?",
            arrayOf(recipe.recipeId.toString())
        )

        db.close()

        return success
    }

    fun deleteRecipe(recipeId: Int): Int {
        val db = writableDatabase

        val success = db.delete(
            TABLE_RECIPE,
            "$COLUMN_RECIPE_ID = ?",
            arrayOf(recipeId.toString())
        )

        db.close()

        return success
    }

    // Operaciones CRUD para la entidad Ingredient

    fun addIngredient(ingredient: Ingredient): Long {
        val db = writableDatabase

        val ingredientValues = ContentValues().apply {
            put(COLUMN_INGREDIENT_NAME, ingredient.ingredientName)
            put(COLUMN_INGREDIENT_QUANTITY, ingredient.ingredientQuantity)
            put(COLUMN_MEASUREMENT_UNIT, ingredient.measurementUnit)
        }

        val ingredientId = db.insert(TABLE_INGREDIENT, null, ingredientValues)
        db.close()

        return ingredientId
    }

    fun getIngredient(ingredientId: Int): Ingredient? {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_INGREDIENT WHERE $COLUMN_INGREDIENT_ID = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(ingredientId.toString()))

        if (cursor.moveToFirst()) {
            val ingredient = Ingredient(
                cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENT_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)),
                cursor.getDouble(cursor.getColumnIndex(COLUMN_INGREDIENT_QUANTITY)),
                cursor.getString(cursor.getColumnIndex(COLUMN_MEASUREMENT_UNIT))
            )
            cursor.close()
            db.close()
            return ingredient
        }

        cursor.close()
        db.close()

        return null
    }

    fun getAllIngredients(): List<Ingredient> {
        val db = readableDatabase
        val ingredientList = mutableListOf<Ingredient>()

        val query = "SELECT * FROM $TABLE_INGREDIENT"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val ingredient = Ingredient(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENT_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_INGREDIENT_QUANTITY)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MEASUREMENT_UNIT))
                )
                ingredientList.add(ingredient)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return ingredientList
    }

    fun updateIngredient(ingredient: Ingredient): Boolean {
        val db = writableDatabase

        val ingredientValues = ContentValues().apply {
            put(COLUMN_INGREDIENT_NAME, ingredient.ingredientName)
            put(COLUMN_INGREDIENT_QUANTITY, ingredient.ingredientQuantity)
            put(COLUMN_MEASUREMENT_UNIT, ingredient.measurementUnit)
        }

        val affectedRows = db.update(
            TABLE_INGREDIENT,
            ingredientValues,
            "$COLUMN_INGREDIENT_ID = ?",
            arrayOf(ingredient.ingredientId.toString())
        )

        db.close()

        return affectedRows > 0
    }

    fun deleteIngredient(ingredientId: Int): Boolean {
        val db = writableDatabase

        val affectedRows = db.delete(
            TABLE_INGREDIENT,
            "$COLUMN_INGREDIENT_ID = ?",
            arrayOf(ingredientId.toString())
        )

        db.close()

        return affectedRows > 0
    }

    // Operaciones para la relación Recipe-Ingredient

    fun addIngredientToRecipe(recipeId: Int, ingredientId: Int) {
        val db = writableDatabase

        val relationValues = ContentValues().apply {
            put(COLUMN_RECIPE_ID_FK, recipeId)
            put(COLUMN_INGREDIENT_ID_FK, ingredientId)
        }

        db.insert(TABLE_RECIPE_INGREDIENT, null, relationValues)
        db.close()
    }

    fun getIngredientsForRecipe(recipeId: Int): List<Ingredient> {
        val db = readableDatabase
        val ingredientList = mutableListOf<Ingredient>()

        val query = """
            SELECT * FROM $TABLE_INGREDIENT
            INNER JOIN $TABLE_RECIPE_INGREDIENT ON $TABLE_INGREDIENT.$COLUMN_INGREDIENT_ID = $TABLE_RECIPE_INGREDIENT.$COLUMN_INGREDIENT_ID_FK
            WHERE $TABLE_RECIPE_INGREDIENT.$COLUMN_RECIPE_ID_FK = ?
        """

        val cursor: Cursor = db.rawQuery(query, arrayOf(recipeId.toString()))

        if (cursor.moveToFirst()) {
            do {
                val ingredient = Ingredient(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_INGREDIENT_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_INGREDIENT_NAME)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_INGREDIENT_QUANTITY)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MEASUREMENT_UNIT))
                )
                ingredientList.add(ingredient)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return ingredientList
    }

    fun insertSampleRecipes() {
        val recipe1 = Recipe(
            1,
            "Tarta de Manzana",
            "Deliciosa tarta de manzana casera",
            60,
            "Media",
            8
        )

        val recipe2 = Recipe(
            2,
            "Spaghetti Bolognese",
            "Clásica pasta con salsa bolognesa",
            45,
            "Fácil",
            4
        )

        val recipe3 = Recipe(
            3,
            "Ensalada César",
            "Ensalada clásica con aderezo César",
            15,
            "Fácil",
            2
        )

        addRecipe(recipe1)
        addRecipe(recipe2)
        addRecipe(recipe3)

        // Agregar ingredientes a las recetas
        val ingredient1 = Ingredient(
            1,
            "Harina",
            500.00,
            "gramos"
        )
        val ingredient2 = Ingredient(
            2,
            "Azucar",
            200.00,
            "gramos"
        )
        val ingredient3 = Ingredient(
            3,
            "Leche",
            250.00,
            "mililitros"
        )
        val ingredient4 = Ingredient(
            4,
            "Mantequilla",
            100.00,
            "gramos"
        )
        val ingredient5 = Ingredient(
            5,
            "Huevos",
            4.0,
            "unidades"
        )

        val ingredient6 = Ingredient(
            6,
            "Sal",
            10.00,
            "gramos"
        )
        val ingredient7 = Ingredient(
            7,
            "Levadura",
            7.00,
            "gramos"
        )
        val ingredient8 = Ingredient(
            8,
            "Vainilla",
            2.0,
            "cucharaditas"
        )
        val ingredient9 = Ingredient(
            9,
            "Chocolate",
            300.00,
            "gramos"
        )
        val ingredient10 = Ingredient(
            10,
            "Aceite",
            50.0,
            "mililitros"
        )

        val ingredient11 = Ingredient(
            11,
            "Lechuga",
            200.00,
            "gramos"
        )
        val ingredient12 = Ingredient(
            12,
            "Pollo",
            150.00,
            "gramos"
        )
        val ingredient13 = Ingredient(
            13,
            "Crutones",
            50.00,
            "gramos"
        )
        val ingredient14 = Ingredient(
            14,
            "Queso parmesano",
            30.00,
            "gramos"
        )
        val ingredient15 = Ingredient(
            15,
            "Aderezo César",
            30.00,
            "mililitros"
        )

        addIngredient(ingredient1)
        addIngredient(ingredient2)
        addIngredient(ingredient3)
        addIngredient(ingredient4)
        addIngredient(ingredient5)
        addIngredient(ingredient6)
        addIngredient(ingredient7)
        addIngredient(ingredient8)
        addIngredient(ingredient9)
        addIngredient(ingredient10)
        addIngredient(ingredient11)
        addIngredient(ingredient12)
        addIngredient(ingredient13)
        addIngredient(ingredient14)
        addIngredient(ingredient15)

        addIngredientToRecipe(recipe1.recipeId, ingredient1.ingredientId)
        addIngredientToRecipe(recipe1.recipeId, ingredient2.ingredientId)
        addIngredientToRecipe(recipe1.recipeId, ingredient3.ingredientId)
        addIngredientToRecipe(recipe1.recipeId, ingredient4.ingredientId)
        addIngredientToRecipe(recipe1.recipeId, ingredient5.ingredientId)

//        addIngredientToRecipe(recipe2.recipeId, ingredient6.ingredientId)
//        addIngredientToRecipe(recipe2.recipeId, ingredient7.ingredientId)
//        addIngredientToRecipe(recipe2.recipeId, ingredient8.ingredientId)
//        addIngredientToRecipe(recipe2.recipeId, ingredient9.ingredientId)
//        addIngredientToRecipe(recipe2.recipeId, ingredient10.ingredientId)
//
//        addIngredientToRecipe(recipe3.recipeId, ingredient11.ingredientId)
//        addIngredientToRecipe(recipe3.recipeId, ingredient12.ingredientId)
//        addIngredientToRecipe(recipe3.recipeId, ingredient13.ingredientId)
//        addIngredientToRecipe(recipe3.recipeId, ingredient14.ingredientId)
//        addIngredientToRecipe(recipe3.recipeId, ingredient15.ingredientId)
    }


}
