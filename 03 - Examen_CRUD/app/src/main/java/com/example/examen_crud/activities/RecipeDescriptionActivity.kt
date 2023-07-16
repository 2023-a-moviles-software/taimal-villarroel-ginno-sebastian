package com.example.examen_crud.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.R
import com.example.examen_crud.adapters.ingredient.IngredientAdapter
import com.example.examen_crud.database.DatabaseSQLite
import com.example.examen_crud.models.Recipe

class RecipeDescriptionActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var ingredientAdapter: IngredientAdapter

    private lateinit var database: DatabaseSQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_description)

        initComponent()
    }

    private fun initComponent() {
        // Obtener referencia al RecyclerView
        recyclerView = findViewById(R.id.RecipeDescription_rvIngredient)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Database
        database = DatabaseSQLite(this)

        // Obtener la receta seleccionada
        val selectedRecipe = intent.getSerializableExtra("recipe") as Recipe

        // Configurar los datos en los elementos del diseño
        val recipeNameTextView = findViewById<TextView>(R.id.tvRecipeName)
        recipeNameTextView.text = selectedRecipe.recipeName

        val recipeDescriptionTextView = findViewById<TextView>(R.id.tvRecipeDescription)
        recipeDescriptionTextView.text = selectedRecipe.recipeDescription

        val preparationTimeTextView =
            findViewById<TextView>(R.id.RecipeDescription_tvRecipePreparationTime)
        preparationTimeTextView.text = selectedRecipe.preparationTime.toString()

        val difficultyTextView = findViewById<TextView>(R.id.RecipeDescription_tvRecipeDifficulty)
        difficultyTextView.text = selectedRecipe.difficulty

        val servingsTextView = findViewById<TextView>(R.id.RecipeDescription_tvRecipeServings)
        servingsTextView.text = selectedRecipe.servings.toString()

        // Crear y configurar el adaptador para los ingredientes
        ingredientAdapter =
            IngredientAdapter(database.getIngredientsForRecipe(selectedRecipe.recipeId))
        recyclerView.adapter = ingredientAdapter
    }
}
