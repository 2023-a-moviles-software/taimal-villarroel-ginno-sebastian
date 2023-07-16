package com.example.examen_crud.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen_crud.R
import com.example.examen_crud.database.DatabaseSQLite
import com.example.examen_crud.models.Recipe

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var database: DatabaseSQLite

    private lateinit var addRecipeButton: Button

    private lateinit var recipeName: EditText
    private lateinit var recipeDescription: EditText
    private lateinit var recipePreparationTime: EditText
    private lateinit var recipeDifficulty: EditText
    private lateinit var recipeServings: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        database = DatabaseSQLite(this)

        addRecipeButton = findViewById<Button>(R.id.AddRecipe_btnAdd)

        addRecipeButton.setOnClickListener {
            initContent()
            addRecipe()
        }
    }

    private fun initContent() {
        recipeName = findViewById<EditText>(R.id.AddRecipe_etRecipeName)
        recipeDescription = findViewById<EditText>(R.id.AddRecipe_etRecipeDescription)
        recipePreparationTime = findViewById<EditText>(R.id.AddRecipe_etPreparationTime)
        recipeDifficulty = findViewById<EditText>(R.id.AddRecipe_etDifficulty)
        recipeServings = findViewById<EditText>(R.id.AddRecipe_etServings)
    }

    private fun addRecipe() {
        val name = recipeName.text.toString()
        val description = recipeDescription.text.toString()
        val time = recipePreparationTime.text.toString()
        val difficulty = recipeDifficulty.text.toString()
        val servings = recipeServings.text.toString()

        if (
            name.isEmpty() ||
            description.isEmpty() ||
            time.isEmpty() ||
            difficulty.isEmpty() ||
            servings.isEmpty()
        ) {
            Toast.makeText(this, "Please enter required field", Toast.LENGTH_SHORT).show()
        } else {
            val recipe = Recipe(
                generateUniqueRecipeId(),
                name,
                description,
                time.toInt(),
                difficulty,
                servings.toInt()
            )
            val status = database.addRecipe(recipe)
            // Check if the recipe was successfully
            if (status > -1) {
                Toast.makeText(this, "Recipe Added...", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else {
                Toast.makeText(this, "Record not saved...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateUniqueRecipeId(): Int {
        return java.util.UUID.randomUUID().hashCode()
    }

    private fun clearEditText() {
        recipeName.setText("")
        recipeDescription.setText("")
        recipePreparationTime.setText("")
        recipeDifficulty.setText("")
        recipeServings.setText("")
    }
}