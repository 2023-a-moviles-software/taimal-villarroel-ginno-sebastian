package com.example.examen_crud.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_crud.R
import com.example.examen_crud.adapters.recipe.RecipeAdapter
import com.example.examen_crud.database.DatabaseSQLite
import com.example.examen_crud.models.Recipe

class EditRecipeActivity : AppCompatActivity() {

    private lateinit var recipeName: EditText
    private lateinit var recipeDescription: EditText
    private lateinit var recipePreparationTime: EditText
    private lateinit var recipeDifficulty: EditText
    private lateinit var recipeServings: EditText

    private lateinit var database: DatabaseSQLite
    private lateinit var recipe: Recipe

    private lateinit var updateRecipeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_recipe)

        recipeName = findViewById(R.id.EditRecipe_etRecipeName)
        recipeDescription = findViewById(R.id.EditRecipe_etRecipeDescription)
        recipePreparationTime = findViewById(R.id.EditRecipe_etPreparationTime)
        recipeDifficulty = findViewById(R.id.EditRecipe_etDifficulty)
        recipeServings = findViewById(R.id.EditRecipe_etServings)
        updateRecipeButton = findViewById(R.id.EditRecipe_btnUpdate)

        database = DatabaseSQLite(this)

        // Obtener la receta seleccionada
        recipe = intent.getSerializableExtra("recipe") as Recipe

        // Configurar los valores iniciales en los campos de edición
        recipeName.setText(recipe.recipeName)
        recipeDescription.setText(recipe.recipeDescription)
        recipePreparationTime.setText(recipe.preparationTime.toString())
        recipeDifficulty.setText(recipe.difficulty)
        recipeServings.setText(recipe.servings.toString())

        // Configurar el evento de clic del botón de actualización
        updateRecipeButton.setOnClickListener {
            updateRecipe()
        }
    }

    private fun updateRecipe() {
        val name = recipeName.text.toString()
        val description = recipeDescription.text.toString()
        val time = recipePreparationTime.text.toString().toIntOrNull()
        val difficulty = recipeDifficulty.text.toString()
        val servings = recipeServings.text.toString().toIntOrNull()

        if (name == recipe.recipeName &&
            description == recipe.recipeDescription &&
            time == recipe.preparationTime &&
            difficulty == recipe.difficulty &&
            servings == recipe.servings) {
            Toast.makeText(this, "No changes were made", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedRecipe =
            Recipe(recipe.recipeId, name, description, time!!, difficulty, servings!!)
        val status = database.updateRecipe(updatedRecipe)

        if (status > -1) {
            Toast.makeText(this, "Recipe updated", Toast.LENGTH_SHORT).show()
            clearEditText()
            database.getAllRecipes()
        } else {
            Toast.makeText(this, "Failed to update recipe", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearEditText() {
        recipeName.setText("")
        recipeDescription.setText("")
        recipePreparationTime.setText("")
        recipeDifficulty.setText("")
        recipeServings.setText("")
    }
}
