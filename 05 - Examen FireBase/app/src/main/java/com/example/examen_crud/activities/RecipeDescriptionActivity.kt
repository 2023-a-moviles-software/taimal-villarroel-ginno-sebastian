package com.example.examen_crud.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.R
import com.example.examen_crud.adapters.ingredient.IngredientAdapter

class RecipeDescriptionActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var ingredientAdapter: IngredientAdapter


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
        //database = DatabaseSQLite(this)

        // Configurar los datos en los elementos del diseño
        val recipeNameTextView = findViewById<TextView>(R.id.tvRecipeName)

        val recipeDescriptionTextView = findViewById<TextView>(R.id.tvRecipeDescription)

        val preparationTimeTextView =
            findViewById<TextView>(R.id.RecipeDescription_tvRecipePreparationTime)

        val difficultyTextView = findViewById<TextView>(R.id.RecipeDescription_tvRecipeDifficulty)

        val servingsTextView = findViewById<TextView>(R.id.RecipeDescription_tvRecipeServings)

        // Crear y configurar el adaptador para los ingredientes
        // Obtener la receta seleccionada
//        val selectedRecipe = intent.getSerializableExtra("recipe") as Recipe
//        recipeNameTextView.text = selectedRecipe.recipeName
//        recipeDescriptionTextView.text = selectedRecipe.recipeDescription
//        preparationTimeTextView.text = selectedRecipe.preparationTime.toString()
//        difficultyTextView.text = selectedRecipe.difficulty
//        servingsTextView.text = selectedRecipe.servings.toString()
//        ingredientAdapter =
//            IngredientAdapter(database.getIngredientsForRecipe(selectedRecipe.recipeId))
//        recyclerView.adapter = ingredientAdapter
    }
}
